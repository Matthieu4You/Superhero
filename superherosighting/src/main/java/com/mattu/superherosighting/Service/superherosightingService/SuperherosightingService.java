package com.mattu.superherosighting.Service.superherosightingService;

import com.mattu.superherosighting.Model.Superherosight;

import java.util.List;

public interface SuperherosightingService {
    List<Superherosight> getRecentSightings();

    List<Superherosight> getAllSightings();

    Superherosight getSuperherosightById(int id);

    Superherosight addSuperherosight(Superherosight ss);

    Superherosight updateSuperherosight(Superherosight ss);

    int deleteSuperherosight(int id);
}
