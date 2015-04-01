/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.Histogram;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class BoatHistogram implements Histogram{
    
    private final Map<Rank, Integer> histo;
    
    public BoatHistogram(){
        histo = new HashMap();
    }
    @Override
    public void haveCard(Card card) {
        if(Objects.nonNull(card)){
            Integer count = histo.get(card.getRank());
            if(Objects.isNull(count)){
                histo.put(card.getRank(), 1);
            }else{
                histo.put(card.getRank(), ++count);
            }
        }
    }
    
    public boolean isBoat(){
        return Objects.nonNull(getQuad()) && Objects.nonNull(getSingle());
    }

    public Rank getQuad() {
        for(Map.Entry<Rank, Integer> entry : histo.entrySet()){
            if(entry.getValue() == 4){
                return entry.getKey();
            }
        }
        return null;
    }
    
    public Rank getSingle(){
        Rank highestRank = null;
        for(Map.Entry<Rank, Integer> entry : histo.entrySet()){
            if(entry.getValue() == 1){
                if(Objects.isNull(highestRank)){
                    highestRank = entry.getKey();
                }else if(entry.getKey().value() > highestRank.value()){
                    highestRank = entry.getKey();
                }
            }
        }
        return highestRank;
    }
}
