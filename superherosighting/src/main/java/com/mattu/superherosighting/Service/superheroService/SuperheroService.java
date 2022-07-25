package com.mattu.superherosighting.Service.superheroService;

import com.mattu.superherosighting.Model.Orgnization;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superheromember;

import java.util.List;

public interface SuperheroService {
    List<Superhero> getAllHeroes();

    Superhero getHeroById(int id);

    Orgnization getHeroOrgnizationById(int id);

    Superhero addSuperhero(Superhero hero,int heroOrg);

    Superhero updateSuperhero(Superhero hero);

    int deleteSuperheroById(int id);
}
