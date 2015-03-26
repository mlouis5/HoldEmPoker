/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Community;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class CommunityImpl implements Community {

    private Card[] flop;
    private Card turn;
    private Card river;
    
    public CommunityImpl(){
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
    
}
