/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl.util;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
public class SinglePot {
    List<Map<Player, MoneyAction>> bets;
    private int purse;
    
    public SinglePot(){
        bets = new ArrayList();
        purse = 0;
    }
    
    public void addAction(MoneyAction action){
        if(Objects.isNull(action)){
            return;
        }
        purse += action.getAmount();
    }
    
    public void payWinners(WinnerContainer wc){
        if(Objects.isNull(wc) && !isPotSolvent()){
            return;
        }
        
    }
    
    private boolean isPotSolvent(){
        int total = 0;
        for(Map<Player, MoneyAction> acts : bets){
            for(MoneyAction action : acts.values()){
                total += action.getAmount();
            }
        }
        return total > 0;
    }
    
    private double getPlayerPotRatio(Player p){
        if(Objects.isNull(p)){
            return 0;
        }
        int pMax = getPlayerMaxWinAmt(p);
        return purse > 0 ? (pMax / (double)purse) : 0;
    }
    
    private int getPlayerMaxWinAmt(Player p){
        if(Objects.isNull(p)){
            return 0;
        }
        int pMax = 0;
        for(int i = bets.size() - 1; i >= 0; i--){
            Map<Player, MoneyAction> pActs = bets.get(i);
            MoneyAction ma = pActs.get(p);
            if(Objects.isNull(ma)){
                continue;
            }
            int pAmt = ma.getAmount();
            
            for(Entry<Player, MoneyAction> entry : pActs.entrySet()){
                if(entry.getKey() == p){
                    continue;
                }
                int amt = entry.getValue().getAmount();
                if(amt < pAmt){
                    pMax += amt;
                }else{
                    pMax += pAmt;
                }
            }
        }
        return pMax;
    }
    
    
}
