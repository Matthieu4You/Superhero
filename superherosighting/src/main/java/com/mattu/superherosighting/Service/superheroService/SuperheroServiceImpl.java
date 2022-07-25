package com.mattu.superherosighting.Service.superheroService;

import com.mattu.superherosighting.Dao.LocationDao.LocationDao;
import com.mattu.superherosighting.Dao.OrgnizationDao.OrgnizationDao;
import com.mattu.superherosighting.Dao.SuperheroDao.SuperheroDao;
import com.mattu.superherosighting.Dao.SuperheromemberDao.SuperheromemberDao;
import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Orgnization;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superheromember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class SuperheroServiceImpl implements SuperheroService {
    @Autowired
    SuperheroDao sDao;

    @Autowired
    SuperheromemberDao smDao;

    @Autowired
    LocationDao lDao;

    @Autowired
    OrgnizationDao oDao;

    @Override
    public List<Superhero> getAllHeroes() {
        return sDao.getAllSuperhero();
    }

    @Override
    public Superhero getHeroById(int id) {
        return sDao.getSuperheroById(id);
    }

    @Override
    public Orgnization getHeroOrgnizationById(int id) {
        return smDao.getSuperheromemberBySid(id).getOrg();

    }

    @Override
    public Superhero addSuperhero(Superhero hero,int heroOrg) {

        hero = sDao.addSuperhero(hero);

        Orgnization org = new Orgnization();
        org.setOid(heroOrg);

        Superheromember sm = new Superheromember();
        sm.setOrg(org);
        sm.setHero(hero);

        smDao.addSuperheromember(sm);
        return hero;
    }

    @Override
    public Superhero updateSuperhero(Superhero hero) {
        return sDao.updateSuperhero(hero);
    }

    @Override
    public int deleteSuperheroById(int id) {
        return sDao.deleteSuperheroById(id);
    }
}
