/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker;

import com.mac.abstractrepository.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mac.holdempoker.socket.PokerEndPoint;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Mac
 */
@Configuration
@Import({ DataSourceConfiguration.class })
@ComponentScan({ "com.mac.holdempoker.app" })
public class Application {
    
    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        PokerEndPoint server = ctx.getBean(PokerEndPoint.class);
        server.start();
    }
}
