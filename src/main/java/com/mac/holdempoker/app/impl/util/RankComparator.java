/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.enums.Rank;
import java.util.Comparator;

/**
 *
 * @author Mac
 */
public class RankComparator implements Comparator<Rank>{

    @Override
    public int compare(Rank o1, Rank o2) {
        return o1.compareTo(o2);
    }
    
}
