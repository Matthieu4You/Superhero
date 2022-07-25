package com.mattu.superherosighting.Service.superherosightingService;

import com.mattu.superherosighting.Dao.LocationDao.LocationDao;
import com.mattu.superherosighting.Dao.OrgnizationDao.OrgnizationDao;
import com.mattu.superherosighting.Dao.SuperheroDao.SuperheroDao;
import com.mattu.superherosighting.Dao.SuperheroSightDao.SuperheroSightDao;
import com.mattu.superherosighting.Dao.SuperheromemberDao.SuperheromemberDao;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superherosight;
import com.mattu.superherosighting.Service.superherosightingService.SuperherosightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperherosightingServiceImpl implements SuperherosightingService {

    SuperheroDao sDao;
    LocationDao lDao;
    OrgnizationDao oDao;
    SuperheromemberDao smDao;
    SuperheroSightDao ssDao;

    @Autowired
    public SuperherosightingServiceImpl(SuperheroDao sDao, LocationDao lDao, OrgnizationDao oDao, SuperheromemberDao smDao, SuperheroSightDao ssDao) {
        this.sDao = sDao;
        this.lDao = lDao;
        this.oDao = oDao;
        this.smDao = smDao;
        this.ssDao = ssDao;
    }

    @Override
    public List<Superherosight> getRecentSightings() {
        return ssDao.getAllSuperherosight()
                .stream()
                .sorted((x,y)->x.getSSid()-y.getSSid())
                .collect(Collectors.toList());
    }

    @Override
    public List<Superherosight> getAllSightings() {
        return ssDao.getAllSuperherosight();
    }

    @Override
    public Superherosight getSuperherosightById(int id) {
        return ssDao.getSuperherosightById(id);
    }

    @Override
    public Superherosight addSuperherosight(Superherosight ss) {
        return ssDao.addSuperherosight(ss);
    }

    @Override
    public Superherosight updateSuperherosight(Superherosight ss) {
        return ssDao.updateSuperherosight(ss);
    }

    @Override
    public int deleteSuperherosight(int id) {
        return ssDao.deleteSuperherosightById(id);
    }

}
