package com.mattu.superherosighting.Service.superheromemberService;

import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superheromember;

import java.util.List;

public interface SuperheromemberService {
    Superheromember getSuperheromemberBySid(int sid);

    Superheromember updateMemberBySid(int sid, int oid);

    List<Superhero> getSuperheromemberByOid(int oid);

    String getHeroStringByOid(int oid);

    int updateSuperheromember(int oid,List<Integer> sid);
}
