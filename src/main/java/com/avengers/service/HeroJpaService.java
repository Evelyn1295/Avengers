package com.avengers.service;

import com.avengers.exceptions.HeroNotFoundException;
import com.avengers.model.Hero;
import com.avengers.model.Power;
import com.avengers.repository.HeroRepository;
import com.avengers.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("jpa")
public class HeroJpaService implements HeroService{

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerRepository powerRepository;

    @Override
    public List<Hero> findAll() {
        return this.heroRepository.findAll();
    }

    @Override
    public Hero findHeroById(int id) {
        return this.heroRepository.findById(id).orElse(null);
    }

    @Override
    public Hero addHero(Hero hero) {
        return this.heroRepository.save(hero);
    }

    @Override
    public void deleteHero(int id) {
        Hero hero = this.heroRepository.findById(id).orElse(null);
        if(hero != null){
            this.heroRepository.deleteById(id);
        }
        throw new HeroNotFoundException("Este heroe con el id " + id + "no se encuentra en la bbdd");
    }

    @Override
    public List<Power> findAllPowersByHeroId(int heroId) {
        Hero hero = this.heroRepository.findById(heroId).orElse(null);
        if(hero!= null){
            hero.getPowerList();
        }
        return null;
    }

    @Override
    public Power findPowerById(int heroId, int powerId) {
        Hero hero = this.heroRepository.findById(heroId).orElse(null);
        if(hero!= null && hero.getPowerList() != null && !hero.getPowerList().isEmpty()){
            return hero.getPowerList().stream().filter(power -> power.getId() == powerId).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public Power addPower(int heroId, Power power) {
        Hero hero = this.heroRepository.findById(heroId).orElse(null);
        if(hero!= null){
           power.setHero(hero);
           return powerRepository.save(power);
        }
        return null;
    }

    @Override
    public void deletePower(int heroId, int powerId) {
        Power power
                 = findPowerById(heroId,powerId);
        if(power != null){
            this.powerRepository.delete(power);
        }

    }


}
