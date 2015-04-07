/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.actions;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.MonetaryAction;
import com.mac.holdempoker.app.Player;

/**
 *
 * @author MacDerson
 */
public class Bet implements MonetaryAction {

    private Player actingPlayer;
    private int amount;

    @Override
    public void setActingPlayer(Player p) {
        this.actingPlayer = p;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amt) {
        if (amt > 0) {
            this.amount = amt;
        }
    }

}
