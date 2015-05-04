/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker;

import com.mac.abstractrepository.DataSourceConfiguration;
import com.mac.holdempoker.socket.HoldemEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//import com.mac.holdempoker.socket.PokerEndPoint;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Mac
 */
@Configuration
@Import({ DataSourceConfiguration.class })
@ComponentScan({ "com.mac.holdempoker.app", "com.mac.holdempoker.socket", "com.mac.holdempoker.game" })
public class Application {
    
    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        HoldemEndpoint server = ctx.getBean(HoldemEndpoint.class);
        server.start();
    }
}
