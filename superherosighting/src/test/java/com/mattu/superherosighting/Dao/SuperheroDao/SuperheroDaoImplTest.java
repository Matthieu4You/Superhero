package com.mattu.superherosighting.Dao.SuperheroDao;


import com.mattu.superherosighting.Model.Superhero;
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
class SuperheroDaoImplTest {

    @Autowired
    SuperheroDao sDao;

    @BeforeEach
    public void setUp(){
        List<Superhero> heroes = sDao.getAllSuperhero();
        for(Superhero s: heroes){
            sDao.deleteSuperheroById(s.getSid());
        }

    }

    @Test
    void addAndGetSuperhero() {
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Superhero fromDao = sDao.getSuperheroById(hero.getSid());

        assertEquals(hero,fromDao);

    }


    @Test
    void getAllHeroes(){
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Superhero hero2 = new Superhero();
        hero2.setSname("Test2");
        hero2.setSdescription("Test2");
        hero2.setSsp("Test2");
        hero2.setSvillian(0);
        hero2 = sDao.addSuperhero(hero2);

        List<Superhero> heroes = sDao.getAllSuperhero();

        assertEquals(2,heroes.size());
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));
    }

    @Test
    void updateSuperhero() {
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Superhero fromDao = sDao.getSuperheroById(hero.getSid());
        assertEquals(hero,fromDao);


        hero.setSname("NewTest1");
        hero = sDao.updateSuperhero(hero);

        fromDao = sDao.getSuperheroById(hero.getSid());

        assertEquals(hero,fromDao);

    }

    @Test
    void deleteSuperheroById() {
        Superhero hero = new Superhero();
        hero.setSname("Test1");
        hero.setSdescription("Test1");
        hero.setSsp("Test1");
        hero.setSvillian(0);
        hero = sDao.addSuperhero(hero);

        Superhero fromDao = sDao.getSuperheroById(hero.getSid());
        assertEquals(hero,fromDao);

        sDao.deleteSuperheroById(hero.getSid());
        Superhero temp = sDao.getSuperheroById(hero.getSid());

        assertNull(temp);

    }
}