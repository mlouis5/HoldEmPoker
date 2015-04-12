/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.PlayerOrder;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.impl.SimpleDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author MacDerson
 */
public class PlayerOrderImpl implements PlayerOrder, Comparator<Card> {

    private boolean isOrdered;
    private List<Player> orderedPlayers;
    private int pointer;
    private int nextToAct;

    public PlayerOrderImpl(List<Player> players) {
        pointer = 0;
        isOrdered = false;
        nextToAct = -1;
        orderPlayers(players);
    }

    private void orderPlayers(List<Player> players) {
        if (!isOrdered) {
            Map<Card, Player> dealOrder = new TreeMap(this);
            Deck d = new SimpleDeck();
            d.buildDeck();
            d.shuffleDeck();
            for (Player p : players) {
                dealOrder.put(d.drawNextCard(), p);
            }

            List<Player> plyrs = new ArrayList(dealOrder.values());
            orderedPlayers = new ArrayList(plyrs.size());
            int playerNumber = 1;
            orderedPlayers.add(plyrs.remove(plyrs.size() - 1));
            orderedPlayers.get(0).setPlayerNumber(playerNumber++);
            Collections.reverse(plyrs);
            
            for (Player p : plyrs) {
                p.setPlayerNumber(playerNumber++);
                orderedPlayers.add(0, p);
            }
            nextToAct = 0;
            isOrdered = true;
        }
    }

    @Override
    public List<Player> getDealOrder() {
        resetDealerAndBlinds();
        Collections.rotate(orderedPlayers, 1);
        orderedPlayers.get(getPointer()).setIsDealer(true);
        orderedPlayers.get(getPointer()).setIsSmallBlind(true);
        orderedPlayers.get(getPointer()).setIsBigBlind(true);
        
        int point = getPointer();
        nextToAct = point;
        
        for(int i = 0; i < orderedPlayers.size(); i++){
            orderedPlayers.get(point).setActionOrder((i+1));
            point = getPointer();
        }
        return new ArrayList(orderedPlayers);
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

    private int getPointer() {
        if (Objects.isNull(orderedPlayers)) {
            throw new NullPointerException("orderedPlayers is null");
        }
        return pointer = (pointer + 1) < orderedPlayers.size() ? pointer++ : 0;
    }
    
    private int nextToAct(){
        if(Objects.isNull(orderedPlayers)){
            throw new NullPointerException("orderedPlayers is null");
        }
        return nextToAct = (nextToAct + 1) < orderedPlayers.size() ? nextToAct++ : 0;
    }

    private void resetDealerAndBlinds() {
        pointer = 0;
        for (Player p : orderedPlayers) {
            p.setIsDealer(false);
            p.setIsSmallBlind(false);
            p.setIsBigBlind(false);
        }
    }

    @Override
    public Player getNextToAct() {
        return orderedPlayers.get(nextToAct());
    }
}
