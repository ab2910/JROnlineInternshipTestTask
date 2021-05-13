package com.game.repository;

import com.game.entity.Parameters;
import com.game.entity.Player;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerExtendedRepository {

    List<Player> getPlayersFiltered(Parameters parameters, Pageable pageable);

    Integer countPlayersFiltered(Parameters parameters);

}
