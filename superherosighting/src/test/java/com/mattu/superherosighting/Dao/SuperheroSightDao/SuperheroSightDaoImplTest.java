package com.mattu.superherosighting.Dao.SuperheroSightDao;


import com.mattu.superherosighting.Dao.LocationDao.LocationDao;
import com.mattu.superherosighting.Dao.SuperheroDao.SuperheroDao;
import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superherosight;
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
class SuperheroSightDaoImplTest {

    @Autowired
    SuperheroSightDao ssDao;

    @Autowired
    SuperheroDao sDao;

    @Autowired
    LocationDao lDao;

    @BeforeEach
    public void setUp(){
        List<Superhero> heroes = sDao.getAllSuperhero();
        for(Superhero s: heroes){
            sDao.deleteSuperheroById(s.getSid());
        }

        List<Location> locations = lDao.getAllLocation();
        for(Location location : locations){
            lDao.deleteLocationById(location.getLid());
        }

        List<Superherosight> ss = ssDao.getAllSuperherosight();
        for (Superherosight s : ss) {
            ssDao.deleteSuperherosightById(s.getSSid());
        }

    }

    @Test
    void addAndGetSuperherosight() {
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Superherosight ss = new Superherosight();
        ss.setSSdate("123");
        ss.setSuperhero(hero);
        ss.setLocation(l);
        ss = ssDao.addSuperherosight(ss);

        Superherosight fromDao = ssDao.getSuperherosightById(ss.getSSid());

        assertEquals(ss,fromDao);

    }

//    @Test
//    void test1(){
//        Superhero hero = new Superhero();
//        hero.setSname("Test1");
//        hero.setSdescription("Test1");
//        hero.setSsp("Test1");
//        hero.setSvillian(0);
//
//        Location l = new Location();
//        l.setLname("test");
//        l.setLdescription("test");
//        l.setLaddress("test");
//        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
//        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
//
//        Superherosight ss = new Superherosight();
//        ss.setSSdate("123");
//        ss.setSuperhero(hero);
//        ss.setLocation(l);
//        ss = ssDao.addSuperherosight(ss);
//
//        Superherosight fromDao = ssDao.getSuperherosightById(ss.getSSid());
//    }

    @Test
    void getAllSuperherosight(){
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Superherosight ss = new Superherosight();
        ss.setSSdate("123");
        ss.setSuperhero(hero);
        ss.setLocation(l);
        ss = ssDao.addSuperherosight(ss);

        Superherosight ss2 = new Superherosight();
        ss2.setSSdate("234");
        ss2.setSuperhero(hero);
        ss2.setLocation(l);
        ss2 = ssDao.addSuperherosight(ss2);

        List<Superherosight> list = ssDao.getAllSuperherosight();

        assertEquals(2,list.size());
        assertTrue(list.contains(ss));
        assertTrue(list.contains(ss2));
    }

    @Test
    void updateSuperherosight() {
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Superherosight ss = new Superherosight();
        ss.setSSdate("123");
        ss.setSuperhero(hero);
        ss.setLocation(l);
        ss = ssDao.addSuperherosight(ss);

        Superherosight fromDao = ssDao.getSuperherosightById(ss.getSSid());

        assertEquals(ss,fromDao);



        Superhero hero2 = new Superhero();
        hero2.setSname("Test2");
        hero2.setSdescription("Test2");
        hero2.setSsp("Test2");
        hero2.setSvillian(0);
        hero2 = sDao.addSuperhero(hero2);

        ss.setSuperhero(hero2);
        ss=ssDao.updateSuperherosight(ss);

        fromDao = ssDao.getSuperherosightById(ss.getSSid());

        assertEquals(ss, fromDao);


        Location l2 = new Location();
        l2.setLname("test2");
        l2.setLdescription("test2");
        l2.setLaddress("test2");
        l2.setLlatitude(new BigDecimal(2).setScale(5, RoundingMode.HALF_DOWN));
        l2.setLlongtitude(new BigDecimal(2).setScale(5, RoundingMode.HALF_DOWN));
        l2 = lDao.addLocation(l2);

        ss.setLocation(l2);
        ss=ssDao.updateSuperherosight(ss);

        fromDao = ssDao.getSuperherosightById(ss.getSSid());

        assertEquals(ss, fromDao);

    }

    @Test
    void deleteSuperheroSight(){
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Location l = new Location();
        l.setLname("test");
        l.setLdescription("test");
        l.setLaddress("test");
        l.setLlatitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l.setLlongtitude(new BigDecimal(1).setScale(5, RoundingMode.HALF_DOWN));
        l = lDao.addLocation(l);

        Superherosight ss = new Superherosight();
        ss.setSSdate("123");
        ss.setSuperhero(hero);
        ss.setLocation(l);
        ss = ssDao.addSuperherosight(ss);

        ssDao.deleteSuperherosightById(ss.getSSid());
        Superherosight temp = ssDao.getSuperherosightById(ss.getSSid());

        assertNull(temp);

    }

}