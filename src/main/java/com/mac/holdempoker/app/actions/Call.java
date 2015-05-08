/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.actions;

import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.ActionName;

/**
 *
 * @author MacDerson
 */
public class Call implements MoneyAction{

    private Player actingPlayer;
    private int amt;
    
    @Override
    public int getAmount() {
        return this.amt;
    }

    @Override
    public void setAmount(int amt) {
        this.amt = amt;
    }

    @Override
    public void setActingPlayer(Player p) {
        this.actingPlayer = p;
    }

    @Override
    public ActionName getActionName() {
        return ActionName.CALL;
    }

    @Override
    public Player getActingPlayer() {
        return this.actingPlayer;
    }
    
}
