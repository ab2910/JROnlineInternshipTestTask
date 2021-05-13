package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Parameters;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    PlayerRepository repo;

    @Autowired
    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }



    /* returns list of players filtered by parameters, sorted and paged */
    /* sorting and paging being processed here; filtering implemented in repo */
    public List<Player> getPlayers(Parameters parameters) {

        PlayerOrder order = parameters.getOrder();
        if (order == null) order = PlayerOrder.ID;
        Integer pageNumber = parameters.getPageNumber();
        pageNumber = pageNumber == null || pageNumber < 0 ? 0 : pageNumber;
        Integer pageSize = parameters.getPageSize();
        pageSize = pageSize == null || pageSize < 1 ? 3 : pageSize;

        return repo.getPlayersFiltered(parameters, PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName().toLowerCase())));
    }


    public Integer countPlayers(Parameters parameters) {
        return repo.countPlayersFiltered(parameters);
    }


    public Player getPlayerById(Long id) {
        return repo.findById(id).orElse(null);
    }


    /* aux to add and update methods */
    private Player addLevelAndUntilNextLevel(Player player, Integer experience) {
        Integer level = PlayerLevelUtil.calculateLevel(experience);
        player.setLevel(level);
        player.setUntilNextLevel(PlayerLevelUtil.calculateUntilNextLevel(level, experience));
        return player;
    }


    public Player addNewPlayer(Player player) {
        player = addLevelAndUntilNextLevel(player, player.getExperience());
        return repo.save(player);
    }


    public Player updatePlayer(Long id, Player player) {
        Player playerToUpdate = repo.findById(id).orElse(null);
        if (playerToUpdate == null) return null;

        if (player.getName() != null) playerToUpdate.setName(player.getName());
        if (player.getTitle() != null) playerToUpdate.setTitle(player.getTitle());
        if (player.getRace() != null) playerToUpdate.setRace(player.getRace());
        if (player.getProfession() != null) playerToUpdate.setProfession(player.getProfession());
        if (player.getBirthday() != null) playerToUpdate.setBirthday(player.getBirthday());
        if (player.getBanned() != null) playerToUpdate.setBanned(player.getBanned());
        if (player.getExperience() != null) playerToUpdate.setExperience(player.getExperience());

        playerToUpdate = addLevelAndUntilNextLevel(playerToUpdate, playerToUpdate.getExperience());
        return repo.save(playerToUpdate);
    }


    public boolean deletePlayer(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}