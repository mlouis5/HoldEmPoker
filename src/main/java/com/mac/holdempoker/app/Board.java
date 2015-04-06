/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.util.Observable;

/**
 *
 * @author Mac
 */
public interface Board extends Observable{

    Card[] getFlop();

    Card getTurn();

    Card getRiver();

    void setFlop(Card[] flop);

    void setTurn(Card turnCard);

    void setRiver(Card riverCard);
    
    Card[] getCommunityCards();

}
