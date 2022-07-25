package com.mattu.superherosighting.Dao.SuperheroSightDao;


import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superherosight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SuperheroSightDaoImpl implements SuperheroSightDao{
    private JdbcTemplate jdbc;

    @Autowired
    public SuperheroSightDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public final class SuperherosightMapper implements RowMapper<Superherosight>{

        @Override
        public Superherosight mapRow(ResultSet rs, int index) throws SQLException {
            Superherosight ss = new Superherosight();
            ss.setSSid(rs.getInt("SSid"));
            ss.setSSdate(rs.getString("SSdate"));

            ss.setSuperhero(
                new Superhero(
                    rs.getInt("Sid"),
                    rs.getString("Sname"),
                    rs.getString("Sdescription"),
                    rs.getString("Ssp"),
                    rs.getInt("Svillian"))
            );

            ss.setLocation(
                    new Location(
                            rs.getInt("Lid"),
                            rs.getString("Lname"),
                            rs.getString("Ldescription"),
                            rs.getString("Laddress"),
                            rs.getBigDecimal("Llatitude").setScale(5, RoundingMode.DOWN),
                            rs.getBigDecimal("Llongtitude").setScale(5, RoundingMode.DOWN))
            );
            return ss;
        }
    }
    @Override
    public Superherosight getSuperherosightById(int id) {
        final String GET_HEROS_BY_ID = "SELECT *"
                                        +" FROM superherosight ss"
                                        +" JOIN superhero s"
                                        +" ON (ss.`Sid`=s.`Sid`)"
                                        +" JOIN location l"
                                        +" ON (ss.`Lid`=l.`Lid`)"
                                        +" WHERE ssid = ?";
        try{
            return jdbc.queryForObject(GET_HEROS_BY_ID,new SuperherosightMapper(),id);
        }catch (EmptyResultDataAccessException | DataIntegrityViolationException e){
            // cannot read result | cannot find foreign key
            return null;
        }
    }

    @Override
    public List<Superherosight> getAllSuperherosight() {
        final String GET_ALL_HEROS = "SELECT *"
                                    +" FROM superherosight ss"
                                    +" JOIN superhero s"
                                    +" ON (ss.`Sid`=s.`Sid`)"
                                    +" JOIN location l"
                                    +" ON (ss.`Lid`=l.`Lid`)";
        try{
            return jdbc.query(GET_ALL_HEROS, new SuperherosightMapper());
        }catch (EmptyResultDataAccessException | DataIntegrityViolationException  e){
            return null;
        }
    }

    @Override
    public Superherosight addSuperherosight(Superherosight ss) {
        final String ADD_HEROS = "insert into superherosight(ssdate,sid,lid) values(?,?,?)";
        try{
            jdbc.update(ADD_HEROS
                    ,ss.getSSdate()
                    ,ss.getSuperhero().getSid()
                    ,ss.getLocation().getLid());
            ss.setSSid(getLastIncrementIndex());
            return ss;
        }catch(DataIntegrityViolationException e){
            return null;
        }

    }

    private int getLastIncrementIndex(){
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX,Integer.class);
    }

    @Override
    public Superherosight updateSuperherosight(Superherosight ss) {
        final String UPDATE_HEROS = "update superherosight set ssdate=?,sid=?,lid=? where ssid =?";
        try{
            jdbc.update(UPDATE_HEROS
                    ,ss.getSSdate()
                    ,ss.getSuperhero().getSid()
                    ,ss.getLocation().getLid()
                    ,ss.getSSid());
            return ss;
        }catch(DataIntegrityViolationException e){
            return null;
        }

    }

    @Override
    public int deleteSuperherosightById(int id) {
        final String DELETE_HEROS = "delete from superherosight where ssid=?";
        jdbc.update(DELETE_HEROS,id);
        return id;
    }
}
