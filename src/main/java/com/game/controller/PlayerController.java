package com.game.controller;

import com.game.entity.Parameters;
import com.game.entity.Player;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PlayerController {

    final Date lowerBdLimit = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();
    final Date upperBdLimit = new GregorianCalendar(3000, Calendar.JANUARY, 1).getTime();
    final private PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }



    @GetMapping("/players")
    public List<Player> getPlayers(@ModelAttribute("parameters") Parameters parameters) {
        //System.out.println("\n*** GET :: " + new Date() + " ***\n" + parameters);
        return service.getPlayers(parameters);
    }


    @GetMapping("/players/count")
    public Integer getPlayersCount(@ModelAttribute("parameters") Parameters parameters) {
        return service.countPlayers(parameters);
    }


    @PostMapping(value="/players")
    public Player addNewPlayer(@RequestBody Player player) {
        //System.out.println("\n*** POST create :: " + new Date() + " ***\n" + player);
        if (player == null
                || player.getName() == null || player.getName().length() == 0 || player.getName().length() > 12
                || player.getTitle() == null || player.getTitle().length() > 30
                || player.getExperience() == null || player.getExperience() < 0 || player.getExperience() > 10000000L
                || player.getBirthday() == null || player.getBirthday().before(lowerBdLimit) || player.getBirthday().after(upperBdLimit)
                || player.getRace() == null || player.getProfession() == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }
        if (player.getBanned() == null) player.setBanned(false);
        return service.addNewPlayer(player);
    }


    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable("id") long id) {
        if (id < 1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        Player player = service.getPlayerById(id);
        if (player == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
        return player;
    }


    @PostMapping(value="/players/{id}")
    public Player editPlayer(@PathVariable("id") long id, @RequestBody Player player) {
        System.out.println("\n*** POST create :: " + new Date() + " ***\n" + player);
        if (id < 1 || player == null
                || (player.getName() != null && (player.getName().length() == 0 || player.getName().length() > 12))
                || (player.getTitle() != null && player.getTitle().length() > 30)
                || (player.getExperience() != null && (player.getExperience() < 0 || player.getExperience() > 10000000L))
                || (player.getBirthday() != null && (player.getBirthday().before(lowerBdLimit) || player.getBirthday().after(upperBdLimit))) ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }
        if (player.getName() == null && player.getTitle() == null
                && player.getRace() == null && player.getProfession() == null
                && player.getBirthday() == null && player.getBanned() == null && player.getExperience() == null
                && player.getLevel() == null && player.getExperience() == null) {
            player = service.getPlayerById(id);
        } else {
            player = service.updatePlayer(id, player);
        }
        if (player == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
        return player;
    }


    @DeleteMapping ("/players/{id}")
    public void deletePlayer(@PathVariable("id") long id) {
        if (id < 1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        if (!service.deletePlayer(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
    }
}