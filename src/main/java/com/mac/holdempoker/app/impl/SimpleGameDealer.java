/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.GameDealer;
import com.mac.holdempoker.app.PlayOrder;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.Deal;
import com.mac.holdempoker.app.enums.RoundType;
import com.mac.holdempoker.app.enums.Status;
import com.mac.holdempoker.app.exceptions.InvalidBoardException;
import com.mac.holdempoker.game.impl.GameState;
import com.mac.holdempoker.game.impl.util.RoundObserver;
import java.util.ArrayList;
import java.util.HashSet;
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
public class SimpleGameDealer implements GameDealer, RoundObserver {

    private static final int DEAL_ROUNDS = 2;
    private final Board board;
    private Set<Player> players;
    private final List<Player> eliminated;
    private PlayOrder playOrder;
    private final Deck deck;
    private Deal currentDeal;
//    private final GameSetting settings;
    private SimpleRound simpleRound;
    
    
    public SimpleGameDealer(){
        this.deck = new SimpleDeck();
        this.board = new SimpleBoard();
        currentDeal = Deal.FLOP;
        eliminated = new ArrayList(1);
        players = new HashSet(4);
        simpleRound = new SimpleRound();
    }

    @Override
    public void dealAround() {
        simpleRound.set(removeEliminated());
        deck.buildDeck();
        deck.shuffleDeck();
        board.resetBoard();
        
        for (int i = 0; i < (this.players.size() * DEAL_ROUNDS); i++) {
            simpleRound.deal(deck.drawNextCard());            
        }
        board.dealToBoard(deck.drawNumCards(3));
    }

    @Override
    public void dealBoard() throws InvalidBoardException {
        validateBoard();
        deck.burnCard();
        Card[] card = null;
        
        switch(currentDeal){
            case FLOP:{
                card = deck.drawNumCards(3);
                currentDeal = Deal.TURN;
                simpleRound.setRound(RoundType.PRE_TURN);
                break;
            }
            case TURN:{
                 card = deck.drawNumCards(1);
                 currentDeal = Deal.RIVER;
                 simpleRound.setRound(RoundType.PRE_RIVER);
                 break;
            }
            case RIVER:{
                card = deck.drawNumCards(1);
                currentDeal = Deal.FLOP;
                simpleRound.setRound(RoundType.POST_RIVER);
            }
        }
        board.dealToBoard(card); 
    }

    private void validateBoard() throws InvalidBoardException{
        if(Objects.isNull(board)){
            throw new InvalidBoardException(board);
        }
    }

    @Override
    public void actionPerformed(Action action) {
        if(Objects.isNull(action)){
            return;
        }
        simpleRound.actionPerformed(action);
        
//        if(action instanceof MoneyAction){
//            pot.increasePot((MoneyAction) action);
//            System.out.println("ADD IMPLEMENTATION FOR BETTING/RAISING");
//        }else{
//            if(action.getActionName() == ActionName.FOLD){
//                System.out.println("ADD IMPLEMENTATION FOR FOLDING");
//            }else if(action.getActionName() == ActionName.CHECK){
//                System.out.println("ADD IMPLEMENTATION FOR CHECKING");
//            }
//        }
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void addPlayer(Player player) {
        if(Objects.nonNull(player) && this.players.size() < 4){
            this.players.add(player);
        }
        if(this.players.size() == 2){
            dealAround();
        }
    }

    @Override
    public Set<Player> getPlayers() {
        return players;
    }

    @Override
    public PlayOrder getPlayOrder() {
        return playOrder;
    }

    @Override
    public GameState getGameState() {
        GameState gs = new GameState();
        gs.setBoard(board);
        gs.setPlayers(players);
        gs.setPot(simpleRound.getPot());
        return gs;
    }

    @Override
    public void winningPlayers(Player... plyrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Set<Player> removeEliminated(){        
        players.stream().forEach((p) -> {
            Set<Status> status = p.getStatus();
            if (status.contains(Status.ELIMINATED)) {
                eliminated.add(p);
            }
        });
        eliminated.stream().forEach((elim) -> {
            players.remove(elim);
        });
        return players;
    }
}
