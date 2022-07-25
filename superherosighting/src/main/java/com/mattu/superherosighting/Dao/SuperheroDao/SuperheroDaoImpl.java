package com.mattu.superherosighting.Dao.SuperheroDao;

import com.mattu.superherosighting.Model.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SuperheroDaoImpl implements SuperheroDao{
    private JdbcTemplate jdbc;

    @Autowired
    public SuperheroDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public static final class SuperheroMapper implements RowMapper<Superhero> {

        @Override
        public Superhero mapRow(ResultSet rs, int index) throws SQLException {
            Superhero hero = new Superhero();
            hero.setSid(rs.getInt("Sid"));
            hero.setSname(rs.getString("Sname"));
            hero.setSdescription(rs.getString("Sdescription"));
            hero.setSsp(rs.getString("Ssp"));
            hero.setSvillian(rs.getInt("Svillian"));
            return hero;
        }
    }

    @Override
    public Superhero getSuperheroById(int id) {

        final String GET_HERO_BY_ID = "select * from superhero where sid = ?";
        try{
            return jdbc.queryForObject(GET_HERO_BY_ID,new SuperheroMapper(),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public List<Superhero> getAllSuperhero() {
        final String GET_ALL_HERO = "select * from superhero";
        try{
            return jdbc.query(GET_ALL_HERO, new SuperheroMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Superhero addSuperhero(Superhero hero) {
        final String INSERT_HERO = "INSERT INTO superhero(sname,sdescription,ssp,svillian) VALUES(?,?,?,?) ";
        jdbc.update(INSERT_HERO
                    ,hero.getSname()
                    ,hero.getSdescription()
                    ,hero.getSsp()
                    ,hero.getSvillian());
        hero.setSid(getLastIncrementIndex());
        return hero;
    }

    private int getLastIncrementIndex(){
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX,Integer.class);
    }

    @Override
    public Superhero updateSuperhero(Superhero hero) {
        final String UPDATE_HERO = "update superhero set sname =?, sdescription =?, ssp =?, svillian =? where sid =?";
        jdbc.update(UPDATE_HERO
                    ,hero.getSname()
                    ,hero.getSdescription()
                    ,hero.getSsp()
                    ,hero.getSvillian()
                    ,hero.getSid());
        return hero;
    }

    @Override
    public int deleteSuperheroById(int id) {
        final String DELETE_HERO = "delete from superhero where sid = ?";
        jdbc.update(DELETE_HERO, id);
        return id;
    }
//
//    @Override
//    public Superhero deleteSuperhero(Superhero hero) {
//        final String DELETE_HERO = "delete from superhero where sid = ?";
//        jdbc.update(DELETE_HERO, hero.getSid());
//        return hero;
//    }


}
