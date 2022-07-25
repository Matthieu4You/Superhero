package com.mattu.superherosighting.Dao.SuperheromemberDao;

import com.mattu.superherosighting.Model.Orgnization;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superheromember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SuperheromemberDaoImpl implements SuperheromemberDao {

    private JdbcTemplate jdbc;

    @Autowired
    public SuperheromemberDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public final class SuperheromemberMapper implements RowMapper<Superheromember> {

        @Override
        public Superheromember mapRow(ResultSet rs, int index) throws SQLException {
            Superheromember sm = new Superheromember();
            sm.setSMid(rs.getInt("SMid"));
            sm.setHero(
                    new Superhero(
                            rs.getInt("Sid"),
                            rs.getString("Sname"),
                            rs.getString("Sdescription"),
                            rs.getString("Ssp"),
                            rs.getInt("Svillian"))

            );

            sm.setOrg(
                    new Orgnization(
                            rs.getInt("Oid"),
                            rs.getString("Oname"),
                            rs.getString("Odescription"),
                            rs.getString("Ocontact"),
                            rs.getInt("Lid"))
            );

            return sm;
        }
    }

    @Override
    public Superheromember getSuperheromemberById(int id) {
        // by smid
        final String GET_HEROM_BY_ID = "SELECT *"
                + " FROM superheromember sm"
                + " JOIN superhero s"
                + " ON (sm.`Sid`=s.`Sid`) AND s.`Sid`= ?"
                + " JOIN orgnization o"
                + " ON (sm.`Oid`=o.`Oid`)"
                + " WHERE smid = ?";
        try {
            return jdbc.queryForObject(GET_HEROM_BY_ID, new SuperheromemberMapper(), id);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public Superheromember getSuperheromemberBySid(int sid) {
        // by sid
        final String GET_HEROM_BY_ID = "SELECT *"
                + " FROM superheromember sm"
                + " JOIN superhero s"
                + " ON (sm.`Sid`=s.`Sid`) AND s.`Sid`= ?"
                + " JOIN orgnization o"
                + " ON (sm.`Oid`=o.`Oid`)";
        try {
            return jdbc.queryForObject(GET_HEROM_BY_ID, new SuperheromemberMapper(), sid);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public List<Superheromember> getAllSuperheromember() {
        final String GET_ALL_HEROM = "SELECT *"
                + " FROM superheromember sm"
                + " JOIN superhero s"
                + " ON (sm.`Sid`=s.`Sid`)"
                + " JOIN orgnization o"
                + " ON (sm.`Oid`=o.`Oid`)";
        try {
            return jdbc.query(GET_ALL_HEROM, new SuperheromemberMapper());
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public List<Superheromember> getAllSuperheroByOid(int oid) {
        final String GET_ALL_HERO_BY_OID = "SELECT *"
                + " FROM superheromember sm"
                + " JOIN superhero s"
                + " ON (sm.`Sid`=s.`Sid`)"
                + " JOIN orgnization o"
                + " ON (sm.`Oid`=o.`Oid`) AND o.`Oid` =?";
        try {
            return jdbc.query(GET_ALL_HERO_BY_OID, new SuperheromemberMapper(),oid);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public Superheromember addSuperheromember(Superheromember sm) {
        final String ADD_HEROM = "insert into superheromember(sid,oid) values(?,?)";
        try {
            jdbc.update(ADD_HEROM
                    , sm.getHero().getSid()
                    , sm.getOrg().getOid());
            sm.setSMid(getLastIncrementIndex());
            return sm;
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    private int getLastIncrementIndex() {
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX, Integer.class);
    }

    @Override
    public Superheromember updateSuperheromember(Superheromember sm) {

        final String UPDATE_HEROM = "update superheromember set sid=?,oid=? where smid=?";
        try {
            jdbc.update(UPDATE_HEROM
                    , sm.getHero().getSid()
                    , sm.getOrg().getOid()
                    , sm.getSMid());
            return sm;
        } catch (DataIntegrityViolationException e) {
            return null;
        }


    }

    @Override
    public int deleteSuperheromemberById(int id) {
        final String DELETE_HEROM = "delete from superheromember where smid=?";
        jdbc.update(DELETE_HEROM,id);
        return id;
    }
}
