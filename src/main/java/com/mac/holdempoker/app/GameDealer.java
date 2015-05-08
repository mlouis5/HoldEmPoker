/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.exceptions.InvalidBoardException;
import com.mac.holdempoker.game.impl.GameState;
import java.util.List;

/**
 *
 * @author MacDerson
 */
public interface GameDealer {
    
    void setBoard(Board board);
    
    Board getBoard();
    
    void setPlayers(List<Player> players);
    
    List<Player> getPlayers();
    
    PlayOrder getPlayOrder();
    
    GameState getGameState();
    
    void dealAround();
    
    void dealBoard() throws InvalidBoardException;
    
    void actionPerformed(Action action);
}
