/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.exceptions.InvalidBoardException;

/**
 *
 * @author MacDerson
 */
public interface GameDealer {
    
    void setBoard(Board board);
    void setPlayers(Player... players);
    
    void dealAround();
    
    void dealFlop() throws InvalidBoardException;
    void dealTurn() throws InvalidBoardException;
    void dealRiver() throws InvalidBoardException;
}
