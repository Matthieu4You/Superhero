package com.mattu.superherosighting.Dao.LocationDao;

import com.mattu.superherosighting.Model.Location;

import java.util.List;

public interface LocationDao {
    Location getLocationById(int id);

    List<Location> getAllLocation();

    Location addLocation(Location location);

    Location updateLocation(Location location);

    int deleteLocationById(int id);
}
