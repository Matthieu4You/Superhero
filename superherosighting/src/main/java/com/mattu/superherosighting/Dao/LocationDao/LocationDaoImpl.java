package com.mattu.superherosighting.Dao.LocationDao;

import com.mattu.superherosighting.Model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao{
    private JdbcTemplate jdbc;

    @Autowired
    public LocationDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLid(rs.getInt("Lid"));
            location.setLname(rs.getString("Lname"));
            location.setLdescription(rs.getString("Ldescription"));
            location.setLaddress(rs.getString("Laddress"));
            location.setLlatitude(rs.getBigDecimal("Llatitude").setScale(5, RoundingMode.DOWN));
            location.setLlongtitude(rs.getBigDecimal("Llongtitude").setScale(5, RoundingMode.DOWN));

            return location;
        }
    }

    @Override
    public Location getLocationById(int id) {
        final String GET_LOCATION_BY_ID = "select * from location where lid = ?";
        try{
            return jdbc.queryForObject(GET_LOCATION_BY_ID,new LocationMapper(),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Location> getAllLocation() {
        final String GET_ALL_LOCATION = "select * from location";
        try{
            return jdbc.query(GET_ALL_LOCATION, new LocationMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "insert into location(lname,ldescription,Laddress,Llatitude,Llongtitude) values(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION
                ,location.getLname()
                ,location.getLdescription()
                ,location.getLaddress()
                ,location.getLlatitude().setScale(5, RoundingMode.DOWN)
                ,location.getLlongtitude().setScale(5, RoundingMode.DOWN)
        );
        location.setLid(getLastIncrementIndex());
        return location;
    }

    private int getLastIncrementIndex(){
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX,Integer.class);
    }

    @Override
    public Location updateLocation(Location location) {
        final String UPDATE_LOCATION = "update location set lname =? , ldescription =? , Laddress =? , Llatitude =? , Llongtitude =? where lid =?";
        jdbc.update(UPDATE_LOCATION
                ,location.getLname()
                ,location.getLdescription()
                ,location.getLaddress()
                ,location.getLlatitude().setScale(5, RoundingMode.DOWN)
                ,location.getLlongtitude().setScale(5, RoundingMode.DOWN)
                ,location.getLid()
        );
        location.setLid(getLastIncrementIndex());
        return location;
    }

//    @Override
//    public Location deleteLocation(Location location) {
//        return null;
//    }

    @Override
    public int deleteLocationById(int id) {
        final String DELETE_LOCATION = "delete from location where lid =?";
        jdbc.update(DELETE_LOCATION, id);
        return id;
    }
}
