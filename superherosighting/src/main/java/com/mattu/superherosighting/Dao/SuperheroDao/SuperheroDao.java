package com.mattu.superherosighting.Dao.SuperheroDao;

import com.mattu.superherosighting.Model.Superhero;

import java.util.List;

public interface SuperheroDao {
    Superhero getSuperheroById(int id);

    List<Superhero> getAllSuperhero();

    Superhero addSuperhero(Superhero hero);

    Superhero updateSuperhero(Superhero hero);

    int deleteSuperheroById(int id);
}
