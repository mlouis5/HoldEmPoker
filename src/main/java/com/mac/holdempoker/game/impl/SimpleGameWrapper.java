/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl;

import com.google.common.collect.Maps;
import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.game.Game;
import com.mac.holdempoker.socket.utilities.JsonConverter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Map;
import java.util.Objects;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleGameWrapper implements Game {

    private final Map<String, WebSocket> activeConnections;
    private String gameId;
    private Temporal gameStartTime;
    private GameSetting setting;

    public SimpleGameWrapper(GameSetting setting) {
        this.setting = setting;             
        activeConnections = Maps.newConcurrentMap();
    }

    @Override
    public Map<String, WebSocket> getActiveConnections() {
        return activeConnections;
    }

    @Override
    public void addActiveConnection(String pId, WebSocket conn) {
        if (Objects.nonNull(pId) && Objects.nonNull(conn)
                && !pId.isEmpty() && !conn.isClosed()) {
            activeConnections.put(pId, conn);
        }
    }

    @Override
    public String getGameId() {
        return gameId;
    }

    @Override
    public void setGameId(String gameId) {
        if (Objects.isNull(this.gameId) || this.gameId.isEmpty()
                && Objects.nonNull(gameId) && !gameId.isEmpty()) {
            this.gameId = gameId;
        }
    }

    @Override
    public void setGameStartTime(LocalDateTime dateTime) {
        gameStartTime = Objects.isNull(gameStartTime)
                && Objects.nonNull(dateTime) ? dateTime : gameStartTime;
    }

    @Override
    public Temporal getGameStartTime() {
        return gameStartTime;
    }

    @Override
    public void removeConnection(WebSocket conn) {
        if (Objects.nonNull(conn)) {
            activeConnections.entrySet().stream().filter((entry)
                    -> (entry.getValue().equals(conn))).forEach((entry) -> {
                        activeConnections.remove(entry.getKey());
                    });
        }
    }

    @Override
    public void sendGameState(GameState gameState) throws IOException {
        if (Objects.nonNull(gameState)) {
            gameState.setSettings(setting);
            String gameStateStr = JsonConverter.toJsonString(gameState);
            for (WebSocket ws : activeConnections.values()) {
                ws.send(gameStateStr);
            }
        }
    }

    @Override
    public GameSetting getGameSetting() {
        return setting;
    }

}
