package com.justeat.gamemanager.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.justeat.gamemanager.service.GameService;


@RestController
@RequestMapping("/game/api")
public class GameAPIController {
	
	@Autowired
	private GameService gameService; 
	
	@RequestMapping("/askforinvitation")
	public ResponseEntity<Boolean> play(@RequestParam(name = "playerId") String playerId) {
		//System.out.println("Player "+playerId + " ask for invitation");
		Boolean sendInvitation = this.gameService.askForInvitation();
		return new ResponseEntity<Boolean>(sendInvitation, HttpStatus.OK);
	}
	

}
