/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.util;

import com.mac.holdempoker.app.Card;

/**
 *
 * @author Mac
 */
public interface CommunityObserver {
    
    void dealt(Card... card);
}
