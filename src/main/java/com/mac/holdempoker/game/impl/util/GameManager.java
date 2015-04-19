/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl.util;

import com.mac.abstractrepository.db.gamerepo.GameRepo;
import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.game.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
public class GameManager {
    
    @Autowired
    private GameRepo gameRepo;
    
    private List<Game> games;
    
    public GameManager(){
        games = new ArrayList();        
    }
    
    public void addGame(Game game){
        this.games.add(game);
        GameSetting settings = game.getGameSetting();
        if(Objects.nonNull(settings)){
            gameRepo.create(settings);
        }
    }
    
    public Game getGame(String gameId){
        Game g = null;
        for(Game game : games){
            if(game.getGameId().equals(gameId)){
                g = game;
                break;
            }
        }
        return g;
    }
}
