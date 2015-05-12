/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl.util;

import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.RoundType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author MacDerson
 */
public class SinglePot {

    private final List<Map<Player, MoneyAction>> bets;
    private int purse;
    private RoundType rt;

    public SinglePot(RoundType rt) {
        this.rt = rt;
        bets = new ArrayList();
        purse = 0;
    }

    public void addAction(MoneyAction action) {
        if (Objects.isNull(action)) {
            return;
        }
        purse += action.getAmount();
        boolean inserted = false;

        Player p = action.getActingPlayer();
        for (Map<Player, MoneyAction> actions : bets) {
            MoneyAction ma = actions.get(p);
            if (Objects.isNull(ma)) {
                actions.put(p, action);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            Map<Player, MoneyAction> main = new HashMap();
            main.put(p, action);
            bets.add(main);
        }
    }

    public void payWinners(WinnerContainer wc) {
        if (Objects.isNull(wc) && !isPotSolvent()) {
            return;
        }
        Iterator<List<Player>> iter = wc.iterator();

        while (iter.hasNext() && isPotSolvent()) {
            List<Player> plyrs = iter.next();
            plyrs.stream().forEach((p) -> {
                payPlayer(p, plyrs.size());
            });
        }
        bets.clear();
    }

    public void paySinglePlayer(Player p) {
        this.payPlayer(p, 1);
        bets.clear();
    }

    public int getPurse() {
        return purse;
    }

    private boolean isPotSolvent() {
        return purse > 0;
    }

    private void payPlayer(Player p, int numPlyrs) {
        if (!isPotSolvent()) {
            return;
        }
        int pMax = getPlayerMaxWinAmt(p);
        int payOut = Math.floorDiv(pMax, numPlyrs);
        p.increaseStack(payOut);
        purse -= payOut;
    }

    private boolean isPlayerCoverPurse(Player p) {
        return getPlayerMaxWinAmt(p) == purse;
    }

    private int getPlayerMaxWinAmt(Player p) {
        if (Objects.isNull(p)) {
            return 0;
        }
        int pMax = 0;
        for (int i = bets.size() - 1; i >= 0; i--) {
            Map<Player, MoneyAction> pActs = bets.get(i);
            MoneyAction ma = pActs.get(p);
            if (Objects.isNull(ma)) {
                continue;
            }
            int pAmt = ma.getAmount();
            System.out.println("pAmt: " + pAmt);
            for (Entry<Player, MoneyAction> entry : pActs.entrySet()) {
                int amt = entry.getValue().getAmount();
                System.out.println("amt: " + amt);
                if (amt <= pAmt) {
                    pMax += amt;
                } else {
                    pMax += pAmt;
                }
            }
        }
        return pMax;
    }

    public Set<Player> getPlayersToBePaid(Iterator<List<Player>> iter) {
        Set<Player> toBePaid = new TreeSet(new PayOrderComparator());

        whileLoop:
        while (iter.hasNext()) {
            List<Player> plyrs = iter.next();
            for (Player p : plyrs) {
                if (isPlayerCoverPurse(p)) {
                    toBePaid.addAll(plyrs);
                    break whileLoop;
                } else {
                    toBePaid.addAll(plyrs);
                }
            }
        }
        return toBePaid;
    }

    private class PayOrderComparator implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return getPlayerMaxWinAmt(o1) - getPlayerMaxWinAmt(o2);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.rt);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SinglePot other = (SinglePot) obj;
        if (this.rt != other.rt) {
            return false;
        }
        return true;
    }

}
