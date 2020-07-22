package com.avengers.controller;


import com.avengers.exceptions.HeroNotFoundException;
import com.avengers.model.Hero;
import com.avengers.model.Power;
import com.avengers.service.HeroDaoService;
import com.avengers.service.HeroJpaService;
import com.avengers.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class HeroController {

    @Autowired
    @Qualifier("jpa")
    private HeroService heroService;

    @GetMapping(value = "/hero")
    public List<Hero> findAll(){
        return this.heroService.findAll();
    }

    @GetMapping(value = "/hero/{id}")
    public Hero findHeroById(@PathVariable int id){
        Hero result =  this.heroService.findHeroById(id);
        if(result  == null){
            throw new HeroNotFoundException("El héroe con id " + id +" no existe");
        }
        return result;
    }

    //@DeleteMapping(value = "/hero/{id}")
    //public void deleteHeroById(@PathVariable int id){
    //    Hero result =  this.heroService.deleteHero(id);
    //    if(!result){
    //        throw new HeroNotFoundException("El héroe con id " + id +" no existe");
    //    }
//
    //}

    @DeleteMapping(value = "/hero/{id}")
    public void deleteHeroById(@PathVariable int id){
        this.heroService.deleteHero(id);
    }

    @PostMapping(value = "/hero")
    public ResponseEntity<Object> addHero(@RequestBody @Valid Hero hero){
        Hero addedHero =  this.heroService.addHero(hero);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedHero.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/hero/{heroId}/power")
    public List<Power> findAllPowersByHeroId(@PathVariable int heroId ){
        return this.heroService.findAllPowersByHeroId(heroId);
    }

    @GetMapping(value = "/hero/{heroId}/power/{powerId}")
    public Power findPowerById(@PathVariable int heroId ,@PathVariable int powerId){
        return this.heroService.findPowerById(heroId,powerId);
    }

    @PostMapping(value = "/hero/{heroId}/power")
    public ResponseEntity<Object> addHero(@PathVariable int heroId, @RequestBody @Valid Power power){
       Power powerInserted = this.heroService.addPower(heroId,power);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{powerId}")
                .buildAndExpand(powerInserted.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/hero/{heroId}/power/{powerId}")
    public void deleteHeroById(@PathVariable int heroId, @PathVariable int powerId){
        this.heroService.deletePower(heroId,powerId);
    }

}
