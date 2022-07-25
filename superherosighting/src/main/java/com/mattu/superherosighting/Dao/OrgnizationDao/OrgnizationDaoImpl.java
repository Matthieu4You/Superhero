package com.mattu.superherosighting.Dao.OrgnizationDao;

import com.mattu.superherosighting.Model.Orgnization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrgnizationDaoImpl implements OrgnizationDao{

    private JdbcTemplate jdbc;

    @Autowired
    public OrgnizationDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public final class OrgnizationMapper implements RowMapper<Orgnization> {

        @Override
        public Orgnization mapRow(ResultSet rs, int index) throws SQLException {
            Orgnization org = new Orgnization();
            org.setOid(rs.getInt("Oid"));
            org.setOname(rs.getString("Oname"));
            org.setOdescription(rs.getString("Odescription"));
            org.setOcontact(rs.getString("Ocontact"));
            org.setLid(rs.getInt("Lid"));
            return org;
        }
    }

    @Override
    public Orgnization getOrgnizationById(int id) {
        final String GET_ORG_BY_ID = "select * from orgnization where oid = ?";
        try{
            return jdbc.queryForObject(GET_ORG_BY_ID,new OrgnizationMapper(),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Orgnization> getAllOrgnization() {
        final String GET_ALL_ORG = "select * from orgnization";
        try{
            return jdbc.query(GET_ALL_ORG, new OrgnizationMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Orgnization addOrgnization(Orgnization org) {
        final String INSERT_ORG = "INSERT INTO orgnization(Oname,Odescription,Ocontact,Lid) VALUES(?,?,?,?) ";
        jdbc.update(INSERT_ORG
                ,org.getOname()
                ,org.getOdescription()
                ,org.getOcontact()
                ,org.getLid());
        org.setOid(getLastIncrementIndex());
        return org;
    }

    private int getLastIncrementIndex(){
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX,Integer.class);
    }

    @Override
    public Orgnization updateOrgnization(Orgnization org) {
        final String UPDATE_ORG = "update orgnization set Oname=?,Odescription=?,Ocontact=?,Lid=? where Oid=?";
        jdbc.update(UPDATE_ORG
                ,org.getOname()
                ,org.getOdescription()
                ,org.getOcontact()
                ,org.getLid()
                ,org.getOid());
        return org;
    }

    @Override
    public int deleteOrgnizationById(int id) {
        final String DELETE_ORG = "delete from orgnization where oid = ?";
        jdbc.update(DELETE_ORG, id);
        return id;
    }
}
