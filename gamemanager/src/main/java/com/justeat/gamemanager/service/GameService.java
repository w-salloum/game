package com.justeat.gamemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.justeat.gamemanager.config.GameManager;
import com.justeat.gamemanager.dto.Game;
import com.justeat.gamemanager.dto.GameStatus;
import com.justeat.gamemanager.dto.Move;
import com.justeat.gamemanager.dto.Player;
import com.justeat.gamemanager.repository.GameRepository;


public interface GameService {

	

	public Player[] inviteTowPlayers(Game game) ;

	public Player inviteOnePlayer(Game game) ;
	
	public boolean askForInvitation() ;

	public List<Game> getAll() ;

}
