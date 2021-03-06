/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.exceptions.InvalidBoardException;
import com.mac.holdempoker.game.impl.GameState;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MacDerson
 */
public interface GameDealer {
    
    Board getBoard();
    
    void addPlayer(Player player);
    
    Set<Player> getPlayers();
    
    PlayOrder getPlayOrder();
    
    GameState getGameState();
    
    void dealAround();
    
    void dealBoard() throws InvalidBoardException;
    
    void actionPerformed(Action action);
}
