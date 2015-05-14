/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.Status;
import com.mac.holdempoker.socket.SignIn;
import java.nio.charset.Charset;
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
public class SimplePlayer implements Player {

    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String playerId;
    private final List<Card> holeCards;
    private int chipStack;
    private final Set<Status> status;
    private Action[] availableActions;

    public SimplePlayer() {
        this.chipStack = 0;
        this.holeCards = new ArrayList();
        status = new HashSet();
    }
    
    public SimplePlayer(SignIn signIn) {
        this.setPlayerFirstName(signIn.getfName());
        this.setPlayerLastName(signIn.getlName());
        this.setPlayerEmail(signIn.getEmail());
        setPid(signIn.getfName(), signIn.getlName(), signIn.getEmail());
        this.chipStack = 0;
        this.holeCards = new ArrayList();
        status = new HashSet();
    }

    @Override
    public String getPlayerName() {
        return playerFirstName + " " + playerLastName;
    }

    @Override
    public void decreaseStack(int amount) {
        if (chipStack - amount < 0) {
            chipStack = 0;
        } else {
            chipStack -= amount;
        }
    }

    @Override
    public void increaseStack(int amount) {
        chipStack += amount;
    }

    @Override
    public void haveHoleCard(Card card) {
        if(holeCards.size() < 2){
            holeCards.add(card);
        }
    }

    @Override
    public void setPlayerId(String pId) {
        this.playerId = pId;
    }

    @Override
    public String getPlayerId() {
        return this.playerId;
    }

    @Override
    public final void setPlayerEmail(String pEmail) {
        this.playerEmail = pEmail;
    }

    @Override
    public String getPlayerEmail() {
        return this.playerEmail;
    }

    @Override
    public final void setPlayerFirstName(String fName) {
        this.playerFirstName = fName;
    }

    @Override
    public String getPlayerFirstName() {
        return this.playerFirstName;
    }

    @Override
    public final void setPlayerLastName(String lName) {
        this.playerLastName = lName;
    }

    @Override
    public String getPlayerLastName() {
        return this.playerLastName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.playerFirstName);
        hash = 83 * hash + Objects.hashCode(this.playerLastName);
        hash = 83 * hash + Objects.hashCode(this.playerEmail);
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
        final SimplePlayer other = (SimplePlayer) obj;
        if (!Objects.equals(this.playerFirstName, other.playerFirstName)) {
            return false;
        }
        if (!Objects.equals(this.playerLastName, other.playerLastName)) {
            return false;
        }
        if (!Objects.equals(this.playerEmail, other.playerEmail)) {
            return false;
        }
        return true;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 89 * hash + Objects.hashCode(this.playerFirstName);
//        hash = 89 * hash + Objects.hashCode(this.playerLastName);
//        hash = 89 * hash + Objects.hashCode(this.playerEmail);
//        hash = 89 * hash + Objects.hashCode(this.playerId);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final SimplePlayer other = (SimplePlayer) obj;
//        return Objects.equals(this.playerFirstName, other.playerFirstName)
//                && Objects.equals(this.playerLastName, other.playerLastName)
//                && Objects.equals(this.playerEmail, other.playerEmail)
//                && Objects.equals(this.playerId, other.playerId);
//    }

    @Override
    public void setStack(int stack) {
        if (chipStack == -1 && stack > 0) {
            chipStack = stack;
        }
    }

    @Override
    public int getStack() {
        return chipStack;
    }

    @Override
    public void setAvailableActions(Action... possibleActions) {
        this.availableActions = possibleActions;
    }

    @Override
    public Action[] getAvailableActions() {
        return availableActions;
    }

    @Override
    public Card[] getHoleCards() {
        return holeCards.toArray(new Card[2]);
    }

    @Override
    public void clearHand() {
        this.holeCards.clear();
    }

    @Override
    public void addStatus(Status status) {
        this.status.add(status);
    }

    @Override
    public void removeStatus(Status status) {
        this.status.remove(status);
    }

    @Override
    public Set<Status> getStatus() {
        return this.status;
    }

    @Override
    public void clearStatus() {
        this.status.clear();
    }

    private void setPid(String...pidParts){
        HashFunction hf = Hashing.md5();
        Hasher hasher = hf.newHasher();
        for(String str : pidParts){
            hasher.putString(str, Charset.forName("UTF-8"));
        }
        HashCode hc = hasher.hash();
        this.setPlayerId(hc.toString());
    }
}
