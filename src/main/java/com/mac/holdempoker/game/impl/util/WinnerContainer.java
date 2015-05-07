/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl.util;

import com.mac.holdempoker.app.Player;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author MacDerson
 */
public class WinnerContainer implements Comparator<Integer>, Iterable<List<Player>> {

    private final Map<Integer, List<Player>> pScores;
    
    public WinnerContainer(){
        pScores = new TreeMap(this);
    }
    
    public void addPlayerScore(Player p, int score){
        if(Objects.isNull(p) || score < 1){
            return;
        }
        List<Player> players = pScores.get(score);
        if(Objects.nonNull(players)){
            if(!players.contains(p)){
                players.add(p);
            }
        }else{
            List<Player> plyrs = new ArrayList();
            plyrs.add(p);
            pScores.put(score, plyrs);
        }
    }
    
    /**
     * Orders from Greatest to lowest score
     * @param o1
     * @param o2
     * @return 
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return (o1 - 02) * -1;
    }

    @Override
    public Iterator<List<Player>> iterator() {
        return pScores.values().iterator();
    }
    
}
