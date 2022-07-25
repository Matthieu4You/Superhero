package com.mattu.superherosighting.Dao.SuperheromemberDao;

import com.mattu.superherosighting.Dao.OrgnizationDao.OrgnizationDao;
import com.mattu.superherosighting.Dao.SuperheroDao.SuperheroDao;
import com.mattu.superherosighting.Model.Orgnization;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superheromember;
import com.mattu.superherosighting.TestApplicationConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class SuperheromemberDaoImplTest {

    @Autowired
    SuperheromemberDao smDao;

    @Autowired
    SuperheroDao sDao;

    @Autowired
    OrgnizationDao oDao;

    @BeforeEach
    public void setUp(){
        List<Superhero> heroes = sDao.getAllSuperhero();
        for(Superhero s: heroes){
            sDao.deleteSuperheroById(s.getSid());
        }

        List<Orgnization> os = oDao.getAllOrgnization();
        for(Orgnization o: os){
            oDao.deleteOrgnizationById(o.getOid());
        }

        List<Superheromember> sm = smDao.getAllSuperheromember();
        for (Superheromember s : sm) {
            smDao.deleteSuperheromemberById(s.getSMid());
        }

    }

    @Test
    void addAndGetSuperheromember(){
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Superheromember sm = new Superheromember();
        sm.setOrg(o);
        sm.setHero(hero);
        sm = smDao.addSuperheromember(sm);

        Superheromember fromDao = smDao.getSuperheromemberById(sm.getSMid());

        assertEquals(sm,fromDao);
    }

    @Test
    void getAllSuperheromember(){
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Superheromember sm = new Superheromember();
        sm.setOrg(o);
        sm.setHero(hero);
        sm = smDao.addSuperheromember(sm);

        Superheromember sm2 = new Superheromember();
        sm2.setOrg(o);
        sm2.setHero(hero);
        sm2 = smDao.addSuperheromember(sm2);

        List<Superheromember> list = smDao.getAllSuperheromember();

        assertEquals(2,list.size());
        assertTrue(list.contains(sm));
        assertTrue(list.contains(sm2));
    }

    @Test
    void updateSuperheromember(){
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Superheromember sm = new Superheromember();
        sm.setOrg(o);
        sm.setHero(hero);
        sm = smDao.addSuperheromember(sm);

        Superheromember fromDao = smDao.getSuperheromemberById(sm.getSMid());
        assertEquals(sm,fromDao);

        Superhero hero2 = new Superhero();
        hero2.setSname("Test2");
        hero2.setSdescription("Test2");
        hero2.setSsp("Test2");
        hero2.setSvillian(0);
        hero2 = sDao.addSuperhero(hero2);

        sm.setHero(hero2);
        sm = smDao.updateSuperheromember(sm);

        fromDao = smDao.getSuperheromemberById(sm.getSMid());
        assertEquals(sm,fromDao);
    }

    @Test
    void deleteSuperheromember(){
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Superheromember sm = new Superheromember();
        sm.setOrg(o);
        sm.setHero(hero);
        sm = smDao.addSuperheromember(sm);

        Superheromember fromDao = smDao.getSuperheromemberById(sm.getSMid());
        assertEquals(sm,fromDao);

        smDao.deleteSuperheromemberById(sm.getSMid());
        Superheromember temp = smDao.getSuperheromemberById(sm.getSMid());

        assertNull(temp);
    }
}