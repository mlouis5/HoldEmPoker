/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import java.util.List;

/**
 *
 * @author MacDerson
 */
public interface PotDistributor {
    void distributePot(Pot pot, List<Player> players);
}
