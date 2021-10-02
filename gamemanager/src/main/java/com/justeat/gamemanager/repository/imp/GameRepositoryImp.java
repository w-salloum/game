package com.justeat.gamemanager.repository.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.justeat.gamemanager.dto.Game;
import com.justeat.gamemanager.repository.GameRepository;

@Repository
public class GameRepositoryImp implements GameRepository {
	
	private final Map<String,Game> games = new HashMap<>();
	
	
	public void archive(Game game) {
		this.games.put(game.getGameId(), game);
	}
	
	public List<Game> getAll() {
		List<Game> list = new ArrayList<>();
		games.forEach((k,v)->{
			list.add(v);
		});
		return list;
	}

}
