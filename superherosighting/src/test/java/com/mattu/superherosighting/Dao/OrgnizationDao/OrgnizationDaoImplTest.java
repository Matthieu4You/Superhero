package com.mattu.superherosighting.Dao.OrgnizationDao;


import com.mattu.superherosighting.Model.Orgnization;
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
class OrgnizationDaoImplTest {

    @Autowired
    OrgnizationDao oDao;

    @BeforeEach
    public void setUp(){
        List<Orgnization> ls = oDao.getAllOrgnization();
        for(Orgnization o: ls){
            oDao.deleteOrgnizationById(o.getOid());
        }

    }

    @Test
    void addAndGetOrgnization() {
        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Orgnization fromDao = oDao.getOrgnizationById(o.getOid());

        assertEquals(o,fromDao);

    }


    @Test
    void getAllOrgnization(){
        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Orgnization o2 = new Orgnization();
        o2.setOname("test2");
        o2.setOdescription("test2");
        o2.setOcontact("test2");
        o2.setLid(2);
        o2 = oDao.addOrgnization(o2);

        List<Orgnization> list = oDao.getAllOrgnization();

        assertEquals(2,list.size());
        assertTrue(list.contains(o));
        assertTrue(list.contains(o2));
    }

    @Test
    void updateOrgnization() {
        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Orgnization fromDao = oDao.getOrgnizationById(o.getOid());

        assertEquals(o,fromDao);


        o.setOname("newTest");
        o = oDao.updateOrgnization(o);

        fromDao = oDao.getOrgnizationById(o.getOid());

        assertEquals(o,fromDao);

    }

    @Test
    void deleteOrgnizationById() {
        Orgnization o = new Orgnization();
        o.setOname("test");
        o.setOdescription("test");
        o.setOcontact("test");
        o.setLid(1);
        o = oDao.addOrgnization(o);

        Orgnization fromDao = oDao.getOrgnizationById(o.getOid());

        assertEquals(o,fromDao);

        oDao.deleteOrgnizationById(o.getOid());
        Orgnization temp = oDao.getOrgnizationById(o.getOid());


        assertNull(temp);

    }

}