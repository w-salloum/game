package com.justeat.gamemanager.repository;

import java.util.List;

import com.justeat.gamemanager.dto.Game;


public interface  GameRepository {
		
	
	public void archive(Game game) ;
	
	public List<Game> getAll() ;

}
