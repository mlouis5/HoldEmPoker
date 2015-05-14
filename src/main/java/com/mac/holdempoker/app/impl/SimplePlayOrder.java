/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.PlayOrder;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimplePlayOrder implements PlayOrder, Comparator<Card> {

    private Player smallBlind;
    private Player bigBlind;
    private Player button;
    
    private List<Player> orderedPlayers;
    private int playOrder;
    private int dealOrder;
    private boolean isFirstOrder;

    public SimplePlayOrder() {
        isFirstOrder = true;
    }
    
    public SimplePlayOrder(List<Player> players) {
        this();
        orderPlayers(players);
    }
    
    @Override
    public Player getButton() {
        return this.button;
    }

    @Override
    public Player getBigBlind() {
        return this.bigBlind;
    }

    @Override
    public Player getSmallBlind() {
        return this.smallBlind;
    }

    @Override
    public void clear() {
        this.bigBlind = null;
        this.smallBlind = null;
        this.button = null;
    }

    @Override
    public Player getNextToAct() {
        Player p = orderedPlayers.get(getPlayerOrder());
        p.addStatus(Status.ACTOR);
        return p;
    }

    @Override
    public void dealToNext(Card card) {
        orderedPlayers.get(getDealOrder()).haveHoleCard(card);
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList(orderedPlayers);
    }

    private void orderPlayers(List<Player> players) {
        Map<Card, Player> initDealOrder = new TreeMap(this);
        Deck d = new SimpleDeck();
        d.buildDeck();
        d.shuffleDeck();
        players.stream().forEach((p) -> {
            initDealOrder.put(d.drawNextCard(), p);
        });

        List<Player> plyrs = new ArrayList(initDealOrder.values());
        orderedPlayers = new ArrayList(plyrs.size());
        orderedPlayers.add(plyrs.remove(plyrs.size() - 1));
        Collections.reverse(plyrs);

        for (Player p : plyrs) {
            orderedPlayers.add(0, p);
        }
        isFirstOrder = false;
    }

    @Override
    public void order(List<Player> players) {
        resetPointers();
        clear();
        if(isFirstOrder){
            orderPlayers(players);
        }else{
            Collections.rotate(orderedPlayers, 1);
        }
        //head to head, dealer must be small blind
        if (orderedPlayers.size() == 2) {
            orderedPlayers.get(getPlayerOrder()).addStatus(Status.BUTTON);
            orderedPlayers.get(getPlayerOrder()).addStatus(Status.BIG_BLIND);
            orderedPlayers.get(getPlayerOrder()).addStatus(Status.SMALL_BLIND);
            getPlayerOrder();
        } else {
            orderedPlayers.get(getPlayerOrder()).addStatus(Status.BUTTON);
            orderedPlayers.get(getPlayerOrder()).addStatus(Status.SMALL_BLIND);
            orderedPlayers.get(getPlayerOrder()).addStatus(Status.BIG_BLIND);
        }
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

    private int getPlayerOrder() {
        if (Objects.isNull(orderedPlayers)) {
            throw new NullPointerException("orderedPlayers is null");
        }
        return playOrder = (playOrder + 1) < orderedPlayers.size() ? playOrder++ : 0;
    }

    private int getDealOrder() {
        if (Objects.isNull(orderedPlayers)) {
            throw new NullPointerException("orderedPlayers is null");
        }
        return dealOrder = (dealOrder + 1) < orderedPlayers.size() ? ++dealOrder : 0;
    }

    private void resetPointers() {
        playOrder = 0;
        dealOrder = 0;
    }

    @Override
    public boolean canPlayerAct(Player p) {
        return orderedPlayers.indexOf(p) == playOrder;
    }
}
