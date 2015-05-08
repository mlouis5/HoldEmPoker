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

    private int ante;

    private List<Player> orderedPlayers;
    private int playOrder;
    private int dealOrder;

    public SimplePlayOrder(int ante, List<Player> players) {
        orderPlayers(players);
        this.ante = ante;
    }
    
    public SimplePlayOrder(List<Player> players) {
        orderPlayers(players);
        this.ante = 0;
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
    public int getBigAmt() {
        return ante * 2;
    }

    @Override
    public int getSmallAmt() {
        return ante;
    }

    @Override
    public void clear() {
        this.bigBlind = null;
        this.smallBlind = null;
        this.button = null;
    }

    @Override
    public Player getNextToAct() {
        return orderedPlayers.get(getPlayerOrder());
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
        int playerNumber = 1;
        orderedPlayers.add(plyrs.remove(plyrs.size() - 1));
        orderedPlayers.get(0).setPlayerNumber(playerNumber++);
        Collections.reverse(plyrs);

        for (Player p : plyrs) {
            p.setPlayerNumber(playerNumber++);
            orderedPlayers.add(0, p);
        }
    }

    @Override
    public void order() {
        resetPointers();
        clear();
        Collections.rotate(orderedPlayers, 1);

        //head to head, dealer must be small blind
        if (orderedPlayers.size() == 2) {
            this.button = orderedPlayers.get(getPlayerOrder());
            this.bigBlind = orderedPlayers.get(getPlayerOrder());
            this.smallBlind = orderedPlayers.get(getPlayerOrder());
            getPlayerOrder();
        } else {
            this.button = orderedPlayers.get(getPlayerOrder());
            this.smallBlind = orderedPlayers.get(getPlayerOrder());
            this.bigBlind = orderedPlayers.get(getPlayerOrder());
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
    public void increaseAnte() {
        this.ante++;
    }

    @Override
    public boolean canPlayerAct(Player p) {
        return orderedPlayers.indexOf(p) == playOrder;
    }
}
