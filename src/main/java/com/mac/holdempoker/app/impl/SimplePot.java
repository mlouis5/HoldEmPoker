/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import com.mac.holdempoker.app.enums.RoundType;
import com.mac.holdempoker.game.impl.util.SinglePot;
import com.mac.holdempoker.game.impl.util.WinnerContainer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class SimplePot implements Pot {

    private Map<RoundType, SinglePot> pot;
    
    private SinglePot currentPot;
    
    public SimplePot(){
        set();
    }
    
    @Override
    public void newRound(RoundType roundType){
        SinglePot sp = pot.get(roundType);
        if(Objects.isNull(sp)){
            SinglePot newPot = new SinglePot(roundType);
            pot.put(roundType, newPot);
            currentPot = newPot;
        }
    }
    
    @Override
    public void increasePot(MoneyAction action){
        currentPot.addAction(action);
    }
    
    @Override
    public void multiplePlayersWon(WinnerContainer wc){
        pot.values().stream().forEach((singlePot) -> {
            singlePot.payWinners(wc);
        });
        set();
    }
    
    @Override
    public void singlePlayerWon(Player p){
        pot.values().stream().forEach((singlePot) -> {
            singlePot.paySinglePlayer(p);
        });
        set();
    }
    
    public Set<Player> getPlayersToBePaid(WinnerContainer wc){
        Iterator<List<Player>> iter = wc.iterator();
        Set<Player> toBePaid = new HashSet();
        pot.values().stream().forEach((singlePot) -> {
            toBePaid.addAll(singlePot.getPlayersToBePaid(iter));
        });
        return toBePaid;
    }
    
    @Override
    public int getPurse(){
        return pot.values().stream().mapToInt(p -> p.getPurse()).sum();
    }
    
    private void set(){
        pot = null;
        pot = new HashMap();
        currentPot = new SinglePot(RoundType.PRE_FLOP);
        pot.put(RoundType.PRE_FLOP, currentPot);
    }
    
}
