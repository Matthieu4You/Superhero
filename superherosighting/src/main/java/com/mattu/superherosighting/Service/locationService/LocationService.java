package com.mattu.superherosighting.Service.locationService;

import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Orgnization;

import java.util.List;
public interface LocationService {
    List<Location> getAllLocations();

    Location getLocationById(int id);

    Location addLocation(Location location);

    Location updateLocation(Location location);

    String getAddressByOid(Orgnization org);

    int deleteLocationById(int id);
}
