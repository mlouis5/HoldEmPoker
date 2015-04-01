/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Histogram;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class Histo implements Histogram {

    private Map<Rank, Integer> rankHistogram;
    private Map<Suit, Integer> suitHistogram;

    public Histo() {
        this.rankHistogram = new HashMap();
        this.suitHistogram = new HashMap();
    }

    
    public void addToRankCount(Rank rank, int num) {
        Integer number = this.rankHistogram.get(rank);
        if (Objects.nonNull(number)) {
            number += num;
            this.rankHistogram.put(rank, number);
        } else {
            this.rankHistogram.put(rank, num);
        }
    }

    public void addToSuitCount(Suit suit, int num) {
        Integer number = this.suitHistogram.get(suit);
        if (Objects.nonNull(number)) {
            number += num;
            this.suitHistogram.put(suit, number);
        } else {
            this.suitHistogram.put(suit, num);
        }
    }

    public Integer getRankCount(Rank rank) {
        return Objects.isNull(rank) ? null : this.rankHistogram.get(rank);
    }

    public Integer getSuitCount(Suit suit) {
        return Objects.isNull(suit) ? null : this.suitHistogram.get(suit);
    }

    public boolean hasQuads() {
        return Objects.nonNull(getQuadRank());
    }

    public Rank getQuadRank() {
        for (Map.Entry<Rank, Integer> entry : this.rankHistogram.entrySet()) {
            if (entry.getValue() == 4) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public boolean hasBoat(){
        Rank high = getHighBoatRank();
        Rank low = getLowBoatRank();        
        return Objects.nonNull(high) && Objects.nonNull(low) && (high != low);
    }
    
    public Rank getHighBoatRank(){
        for (Map.Entry<Rank, Integer> entry : this.rankHistogram.entrySet()) {
            if (entry.getValue() == 3) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public Rank getLowBoatRank(){
        for (Map.Entry<Rank, Integer> entry : this.rankHistogram.entrySet()) {
            if (entry.getValue() == 2) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean hasFlush() {
        return Objects.nonNull(getFlushSuit());
    }

    public Suit getFlushSuit() {
        for (Map.Entry<Suit, Integer> entry : this.suitHistogram.entrySet()) {
            if (entry.getValue() == 5) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean hasTrips() {
        return Objects.nonNull(getTripsRank());
    }

    public Rank getTripsRank() {
        for (Map.Entry<Rank, Integer> entry : this.rankHistogram.entrySet()) {
            if (entry.getValue() == 3) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean hasTwoPair() {
        Rank highRank = getHighTwoPairRank();
        Rank lowRank = getLowTwoPairRank();
        return Objects.nonNull(highRank)
                && Objects.nonNull(lowRank) && highRank != lowRank;
    }

    public Rank getHighTwoPairRank() {
        Rank highRank = null;
        for (Map.Entry<Rank, Integer> entry : this.rankHistogram.entrySet()) {
            if (entry.getValue() == 2) {
                if (Objects.isNull(highRank)) {
                    highRank = entry.getKey();
                } else if (entry.getKey().value() > highRank.value()) {
                    highRank = entry.getKey();
                }
            }
        }
        return highRank;
    }

    public Rank getLowTwoPairRank() {
        Rank lowRank = null;
        for (Map.Entry<Rank, Integer> entry : this.rankHistogram.entrySet()) {
            if (entry.getValue() == 2) {
                if (Objects.isNull(lowRank)) {
                    lowRank = entry.getKey();
                } else if (entry.getKey().value() < lowRank.value()) {
                    lowRank = entry.getKey();
                }
            }
        }
        return lowRank;
    }
    
    public Rank getPairRank(){
        Rank highestPair = null;
        for (Map.Entry<Rank, Integer> entry : this.rankHistogram.entrySet()) {
            if (entry.getValue() == 2) {
                if(Objects.isNull(highestPair)){
                    highestPair = entry.getKey();
                }else if(entry.getKey().value() > highestPair.value()){
                    highestPair = entry.getKey();
                }
            }
        }
        return highestPair;
    }

    @Override
    public void haveCard(Card card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
