/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.util.BoardObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class SimpleBoard implements Board {

    private Card[] flop;
    private Card turn;
    private Card river;
    
    public SimpleBoard(){
        flop = new Card[3];
        turn = null;
        river = null;
    }
    
    @Override
    public Card[] getFlop() {
        return flop;
    }

    @Override
    public Card getTurn() {
        return turn;
    }

    @Override
    public Card getRiver() {
        return river;
    }

    @Override
    public void setFlop(Card[] flop) {
        if(Objects.nonNull(flop) && flop.length == 3){
            this.flop = flop;
        }
    }

    @Override
    public void setTurn(Card turnCard) {
        this.turn = turnCard;
    }

    @Override
    public void setRiver(Card riverCard) {
        this.river = riverCard;
    }

    @Override
    public Card[] getCommunityCards() {
        List<Card> community = new ArrayList();
        community.addAll(Arrays.asList(flop));
        community.add(turn);
        community.add(river);
        return community.toArray(new Card[5]);
    }

    @Override
    public void add(BoardObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
