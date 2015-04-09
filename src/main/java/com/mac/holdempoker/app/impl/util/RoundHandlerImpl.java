/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Round;
import com.mac.holdempoker.app.RoundHandler;
import com.mac.holdempoker.app.exceptions.InvalidActionException;
import com.mac.holdempoker.app.impl.SimpleRound;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
public class RoundHandlerImpl implements RoundHandler {

    private final Map<Player, List<Action>> playerActions;
    private final List<Round> rounds;
    private List<Player> players;

    public RoundHandlerImpl(List<Player> players) {
        playerActions = new HashMap();
        rounds = new ArrayList();        
        this.players = players;
        Round r = new SimpleRound();
        r.setPlayersInRound(players);
        rounds.add(r);
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newRound() {
        if (rounds.size() < 4) {
            Round r = new SimpleRound();
            r.setPlayersInRound(players);
            rounds.add(new SimpleRound());
        }
    }

    @Override
    public void setAction(Action action) throws InvalidActionException {
        if (Objects.isNull(action) || Objects.isNull(action.getActingPlayer())) {
            throw new InvalidActionException("Acting player may not be null");
        }
        List<Action> pActions = playerActions.get(action.getActingPlayer());
        if (Objects.nonNull(pActions)) {
            pActions.add(action);
        }else{
            pActions = new ArrayList();
            pActions.add(action);
        }
    }

    @Override
    public void assessCurrentRound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
