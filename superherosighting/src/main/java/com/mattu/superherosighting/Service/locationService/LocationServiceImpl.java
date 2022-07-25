package com.mattu.superherosighting.Service.locationService;

import com.mattu.superherosighting.Dao.LocationDao.LocationDao;
import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Orgnization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationDao lDao;

    @Override
    public List<Location> getAllLocations() {
        return lDao.getAllLocation();
    }

    @Override
    public Location getLocationById(int id) {
        return lDao.getLocationById(id);
    }

    @Override
    public Location addLocation(Location location) {
        return lDao.addLocation(location);
    }

    @Override
    public Location updateLocation(Location location) {
        return lDao.updateLocation(location);
    }

    @Override
    public String getAddressByOid(Orgnization org) {
        return lDao.getLocationById(org.getLid()).getLaddress();
    }

    @Override
    public int deleteLocationById(int id) {
        return lDao.deleteLocationById(id);
    }


}
