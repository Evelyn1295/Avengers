package com.avengers.service;


import com.avengers.model.Hero;
import com.avengers.model.Power;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Qualifier("dao")
public class HeroDaoService implements HeroService {

    private static List<Hero> heroes = new ArrayList<Hero>();
         static {
            heroes.add(new Hero(1,"Peter Parker","SpiderMan",new Date()));
            heroes.add(new Hero    (2,"Tony Stark","Ironman",new Date()));
            heroes.add(new Hero(3,"Bruce Banner","Hulk",new Date()));
         }


    private static int counter = 3;

    public List<Hero> findAll(){
        return heroes;
    }

    public Hero findHeroById(int id){
        Hero result = null;
        for(Hero hero : heroes){
            if(hero.getId() == id){
                result = hero;
            }
        }
        return result;
    }

    public Hero addHero(Hero hero){
        hero.setId(++counter);
        heroes.add(hero);
        return hero;
    }

    public void deleteHero(int id){
        Iterator<Hero> heroIterator = heroes.iterator();
        Hero heroToRemove = null;

        do{
            heroToRemove = heroIterator.next();
            if(heroToRemove.getId() == id){
                heroIterator.remove();

            }

        }while(heroIterator.hasNext());

    }

    @Override
    public List<Power> findAllPowersByHeroId(int heroId) {
        return null;
    }

    @Override
    public Power findPowerById(int heroId, int powerId) {
        return null;
    }

    @Override
    public Power addPower(int heroId, Power power) {
        return null;
    }

    @Override
    public void deletePower(int heroId, int powerId) {

    }
}
