/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import com.mac.holdempoker.app.actions.Bet;
import com.mac.holdempoker.app.enums.PotName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    private PotName potName;
    private Map<Player, List<MoneyAction>> actions;
    private int ante;

    public SimplePot(int ante) {
        this.ante = ante;
    }

    @Override
    public void increasePot(MoneyAction action) {
        List<MoneyAction> actionList = actions.get(action.getActingPlayer());
        if (Objects.isNull(actionList)) {
            actionList = new ArrayList(1);
            actionList.add(action);
        } else {
            actionList.add(action);
        }
    }

    @Override
    public int getPotAmount() {
        int amt = 0;
        Collection<List<MoneyAction>> allActs = actions.values();
        for (Iterator<List<MoneyAction>> it = allActs.iterator(); it.hasNext();) {
            List<MoneyAction> acts = it.next();
            amt = acts.stream().map((ma) -> ma.getAmount()).reduce(amt, Integer::sum);
        }
        return amt;
    }

    @Override
    public void clearPot() {
        actions.clear();
    }

    @Override
    public void setPotName(PotName potName) {
        this.potName = potName;
    }

    @Override
    public PotName getPotName() {
        return potName;
    }

    @Override
    public int getMinBetAmount() {
        int min = ante * 2;
        Collection<List<MoneyAction>> allActs = actions.values();
        for (List<MoneyAction> acts : allActs) {
            for (MoneyAction act : acts) {
                if (act.getClass() == Bet.class) {
                    if (act.getAmount() > min) {
                        min = act.getAmount();
                    }
                }
            }
        }
        return min;
    }

    @Override
    public int getMinRaiseAmount() {
        int min = ante * 2;
        Collection<List<MoneyAction>> allActs = actions.values();
        for (List<MoneyAction> acts : allActs) {
            for (MoneyAction act : acts) {
                if (act.getAmount() > min) {
                    min = act.getAmount();
                }
            }
        }
        return min + (ante * 2);
    }

    @Override
    public int getPlayerPotCommitment(Player p) {
        List<MoneyAction> mActs = actions.get(p);
        int amt = 0;
        if (Objects.nonNull(mActs)) {
            amt = mActs.stream().map((ma) -> ma.getAmount()).reduce(amt, Integer::sum);
        }
        return amt;
    }

}
