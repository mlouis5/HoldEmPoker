/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.PlayOrder;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import com.mac.holdempoker.app.enums.ActionName;
import com.mac.holdempoker.app.enums.RoundType;
import com.mac.holdempoker.app.enums.Status;
import com.mac.holdempoker.game.impl.util.RoundObservable;
import com.mac.holdempoker.game.impl.util.RoundObserver;
import com.mac.holdempoker.game.impl.util.WinnerContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleRound implements RoundObservable{// implements Round{

    private final List<RoundObserver> observers;
    private List<Player> players;
    private SimplePot pot;
    private final PlayOrder playOrder;

    public SimpleRound() {
        this.players = new ArrayList();
        pot = new SimplePot();
        observers = new ArrayList();
        playOrder = new SimplePlayOrder();
    }
    
    public SimpleRound(List<Player> players) {
        this.players = new ArrayList();
//        this.players.addAll(players);
        pot = new SimplePot();
        observers = new ArrayList();
        playOrder = new SimplePlayOrder(this.players);
    }

    public void actionPerformed(Action action) {
        if (Objects.isNull(action)) {
            return;
        }
        int indexOf = players.indexOf(action.getActingPlayer());
        if (indexOf >= 0) {
            Player p = players.get(indexOf);
            if (action instanceof MoneyAction) {
                MoneyAction ma = (MoneyAction) action;
                p.decreaseStack(ma.getAmount());
                pot.increasePot(ma);
                if(p.getStack() == 0){
                    p.addStatus(Status.ALL_IN);
                }
            }else{
                if(action.getActionName() == ActionName.FOLD){                    
                    players.remove(action.getActingPlayer());
                    if(players.size() == 1){
                        pot.singlePlayerWon(players.get(0));
                        notifyObserver();
                    }
                }
            }
        }
    }
    
    public void set(Set<Player> players){
        this.players = new ArrayList();
        this.players.addAll(players);
        for(Player p : this.players){
            p.clearStatus();
        }
        pot = new SimplePot();
        playOrder.order(this.players);        
    }

    public void setRound(RoundType rt) {
        pot.newRound(rt);
    }
    
    public void deal(Card card){
        playOrder.dealToNext(card);
    }

    public Player[] showDown(Board board) throws Exception {
        WinnerContainer wc = new WinnerContainer();
        SimpleHandAggregator sha = new SimpleHandAggregator();
        for (Player p : players) {
            Hand sh = new SimpleHand(p, board);           
            wc.addPlayerScore(p, sha.scoreHand(sh));                    
        }
        Set<Player> winners = pot.getPlayersToBePaid(wc);
        pot.multiplePlayersWon(wc);
        return winners.toArray(new Player[winners.size()]);
    }

    public Pot getPot() {
        return pot;
    }
    
    @Override
    public void addRoundObserver(RoundObserver ro) {
        if(Objects.nonNull(ro)){
            observers.add(ro);
        }
    }

    @Override
    public void clearObservers() {
        observers.clear();
    }

    @Override
    public void removeObserver(RoundObserver ro) {
        if(Objects.nonNull(ro)){
            observers.remove(ro);
        }
    }
    
    @Override
    public void notifyObserver() {
        observers.stream().filter(o -> Objects.nonNull(o)).forEach(o -> {
            o.winningPlayers(players.toArray(new Player[players.size()]));
        });
    }
    
}
