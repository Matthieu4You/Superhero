package com.mattu.superherosighting.Dao.OrgnizationDao;

import com.mattu.superherosighting.Model.Orgnization;

import java.util.List;

public interface OrgnizationDao {
    Orgnization getOrgnizationById(int id);

    List<Orgnization> getAllOrgnization();

    Orgnization addOrgnization(Orgnization org);

    Orgnization updateOrgnization(Orgnization org);

    int deleteOrgnizationById(int id);
}
