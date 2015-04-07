/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.DealOrder;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.impl.SimpleDeck;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author MacDerson
 */
public class DealOrderImpl implements DealOrder, Comparator<Card> {

    private boolean isOrdered;
    private List<Player> orderedPlayers;
    private int pointer;

    public DealOrderImpl(List<Player> players) {
        pointer = -1;
        isOrdered = false;
        orderPlayers(players);
    }

    private void orderPlayers(List<Player> players) {
        if (!isOrdered) {
            Map<Card, Player> dealOrder = new TreeMap(this);
            Deck d = new SimpleDeck();
            d.validateDeck();
            d.shuffleDeck();
            for (Player p : players) {
                dealOrder.put(d.drawNextCard(), p);
            }
            List<Player> plyrs = new ArrayList(dealOrder.values());
            Collections.reverse(plyrs);
            orderedPlayers = new ArrayList(plyrs.size());

            int playerNumber = 1;
            for (Player p : plyrs) {
                p.setPlayerNumber(playerNumber++);
                orderedPlayers.add(p);
            }
        }
    }

    @Override
    public List<Player> getDealOrder() {
        resetDealerAndBlinds();
        
        movePointer();
        orderedPlayers.get(pointer).setIsDealer(true);
        movePointer();
        orderedPlayers.get(pointer).setIsSmallBlind(true);
        movePointer();
        orderedPlayers.get(pointer).setIsBigBlind(true);
        isOrdered = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

    private void movePointer() {
        if (Objects.isNull(orderedPlayers)) {
            pointer = -1;
            return;
        }
        pointer = (pointer + 1) < orderedPlayers.size() ? pointer++ : 0;
    }
    
    private void resetDealerAndBlinds(){
        for(Player p : orderedPlayers){
            p.setIsDealer(false);
            p.setIsSmallBlind(false);
            p.setIsBigBlind(false);
        }
    }
}
