package com.mattu.superherosighting.Dao.SuperheromemberDao;


import com.mattu.superherosighting.Model.Superheromember;

import java.util.List;

public interface SuperheromemberDao {
    Superheromember getSuperheromemberById(int id);

    Superheromember getSuperheromemberBySid(int sid);

    List<Superheromember> getAllSuperheromember();

    List<Superheromember> getAllSuperheroByOid(int oid);

    Superheromember addSuperheromember(Superheromember sm);

    Superheromember updateSuperheromember(Superheromember sm);

    int deleteSuperheromemberById(int id);
}
