package com.mattu.superherosighting.Service.orgnizationService;

import com.mattu.superherosighting.Dao.OrgnizationDao.OrgnizationDao;
import com.mattu.superherosighting.Model.Orgnization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgnizationServiceImpl implements OrgnizationService{

    @Autowired
    OrgnizationDao oDao;

    @Override
    public List<Orgnization> getAllOrgnizations() {
        return oDao.getAllOrgnization();
    }

    @Override
    public Orgnization getOrgnizationById(int id) {
        return oDao.getOrgnizationById(id);
    }

    @Override
    public Orgnization updateOrgnization(Orgnization org) {
        return oDao.updateOrgnization(org);
    }

    @Override
    public Orgnization addOrgnization(Orgnization org) {
        return oDao.addOrgnization(org);
    }

    @Override
    public int deleteOrgnizationById(int id) {
        return oDao.deleteOrgnizationById(id);
    }
}
