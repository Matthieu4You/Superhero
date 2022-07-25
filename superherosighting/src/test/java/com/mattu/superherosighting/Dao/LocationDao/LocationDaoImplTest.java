package com.mattu.superherosighting.Dao.LocationDao;


import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.TestApplicationConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class LocationDaoImplTest {
    @Autowired
    LocationDao lDao;

    @BeforeEach
    public void setUp(){
        List<Location> ls = lDao.getAllLocation();
        for(Location l: ls){
            lDao.deleteLocationById(l.getLid());
        }

    }

    @Test
    void addAndGetLocation() {
        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Location fromDao = lDao.getLocationById(l.getLid());

        assertEquals(l,fromDao);

    }


    @Test
    void getAllLocations(){
        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Location l2 = new Location();
        l2.setLname("test2");
        l2.setLdescription("test2");
        l2.setLaddress("test2");
        l2.setLlatitude(new BigDecimal(2).setScale(5, RoundingMode.HALF_DOWN));
        l2.setLlongtitude(new BigDecimal(2).setScale(5, RoundingMode.HALF_DOWN));
        l2 = lDao.addLocation(l2);

        List<Location> list = lDao.getAllLocation();

        assertEquals(2,list.size());
        assertTrue(list.contains(l));
        assertTrue(list.contains(l2));
    }

    @Test
    void updateLocation() {
        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Location fromDao = lDao.getLocationById(l.getLid());

        assertEquals(l,fromDao);


        l.setLname("newTest");
        l = lDao.updateLocation(l);

        fromDao = lDao.getLocationById(l.getLid());

        assertEquals(l,fromDao);

    }

    @Test
    void deleteLocationById() {
        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Location fromDao = lDao.getLocationById(l.getLid());

        assertEquals(l,fromDao);

        lDao.deleteLocationById(l.getLid());
        Location temp = lDao.getLocationById(l.getLid());


        assertNull(temp);

    }
}