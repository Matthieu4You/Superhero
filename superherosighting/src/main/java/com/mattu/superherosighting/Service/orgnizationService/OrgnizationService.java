package com.mattu.superherosighting.Service.orgnizationService;

import com.mattu.superherosighting.Model.Orgnization;

import java.util.List;

public interface OrgnizationService {
    List<Orgnization> getAllOrgnizations();

    Orgnization getOrgnizationById(int id);

    Orgnization updateOrgnization(Orgnization org);

    Orgnization addOrgnization(Orgnization org);

    int deleteOrgnizationById(int id);
}
