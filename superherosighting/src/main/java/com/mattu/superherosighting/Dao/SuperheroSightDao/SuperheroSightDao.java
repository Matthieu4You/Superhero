package com.mattu.superherosighting.Dao.SuperheroSightDao;

import com.mattu.superherosighting.Model.Superherosight;

import java.util.List;

public interface SuperheroSightDao {
    Superherosight getSuperherosightById(int id);

    List<Superherosight> getAllSuperherosight();

    Superherosight addSuperherosight(Superherosight ss);

    Superherosight updateSuperherosight(Superherosight ss);

    int deleteSuperherosightById(int id);
}
