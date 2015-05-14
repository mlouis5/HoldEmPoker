/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket;

import com.google.common.collect.MapMaker;
import com.google.common.collect.Queues;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import org.java_websocket.WebSocket;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
public class SocketManager {

    private final ConcurrentMap<String, WebSocket> connections;
    private final ConcurrentLinkedQueue<WebSocket> cache;

    public SocketManager() {
        connections = new MapMaker().makeMap();
        cache = Queues.newConcurrentLinkedQueue();
    }

    public boolean addConnection(String pid, WebSocket conn) {
        System.out.println("pid: " + pid);
        System.out.println("conn: " + conn);
        if (isValidString(pid) && isValidObject(conn)) {
            System.out.println("Adding socket");
            WebSocket old = connections.putIfAbsent(pid, conn);
            System.out.println("old socket: " + old);
            return Objects.isNull(old);
        }
        return false;
    }

    public WebSocket getConnection(String pid) {
        return isValidString(pid) ? connections.get(pid) : null;
    }

    public void removeConnection(String pid) {
        if (isValidString(pid)) {
            connections.remove(pid);
        }
    }

    public void removeConnection(WebSocket conn) {
        if (isValidObject(conn)) {
            connections.entrySet().stream().filter(e -> e.getValue().equals(conn)).forEach(e -> {
                if (e.getValue().equals(conn)) {
                    connections.remove(e.getKey());
                }
            });
        }
    }

    public void removeClosedConns() {
        connections.entrySet().stream()
                .filter(e -> e.getValue().isClosed())
                .forEach(e -> {
                    connections.remove(e.getKey());
                });
    }

    public void sendToAllOpenConns(String message) {
        if (isValidString(message)) {
            connections.entrySet().stream()
                    .filter(e -> e.getValue().isOpen())
                    .forEach(e -> {
                        e.getValue().send(message);
                    });
        }
    }

    public void sendToConns(String message, List<String> pids) {
        if (isValidString(message) && isValidList(pids)) {
            connections.entrySet().stream()
                    .filter(e -> pids.contains(e.getKey()))
                    .forEach(e -> {
                        e.getValue().send(message);
                    });
        }
    }

    public void sendToConn(String message, String pid) {
        if (isValidString(message) && isValidString(pid)) {
            connections.entrySet().stream()
                    .filter(e -> e.getKey().equalsIgnoreCase(pid))
                    .forEach(e -> {
                        e.getValue().send(message);
                    });
        }
    }

    private boolean isValidObject(Object obj) {
        return Objects.nonNull(obj);
    }

    private boolean isValidString(String str) {
        return Objects.nonNull(str) && !str.isEmpty();
    }

    private boolean isValidList(List<? extends Object> list) {
        return Objects.nonNull(list) && !list.isEmpty();
    }
}
