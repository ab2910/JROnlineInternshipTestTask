package com.game.repository;

import com.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerExtendedRepository {

    // implements standard JPA methods:
    //      Player findById(Long id);
    //      Player save(Player player);
    //      boolean existsById(id);
    //      deleteById(Long id);

    // extends custom repo to implement custom methods
    //      List<Player> getPlayersFiltered(Parameters parameters, Pageable pageable);
    //      Integer countPlayersFiltered(Parameters parameters);

}