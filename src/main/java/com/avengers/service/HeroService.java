package com.avengers.service;


import com.avengers.model.Hero;
import com.avengers.model.Power;

import java.util.List;

public interface HeroService {

    List<Hero> findAll();
    Hero findHeroById(int id);
    Hero addHero(Hero hero);
    void deleteHero(int id);

    List<Power> findAllPowersByHeroId(int heroId);
    Power findPowerById(int heroId, int powerId);
    Power addPower(int heroId, Power power);
    void deletePower(int heroId, int powerId);


}
