package com.interceptor.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.WebSocket;

public class BufferMap {
    private static Map<String, String> mapChacheMsgItems;;

    private BufferMap() {
        mapChacheMsgItems = new ConcurrentHashMap<String, String>();
    }

    public Map<String, String> getChacheMsgItems() {
        return mapChacheMsgItems;
    }

    public String getCacheMsgItem(String key) {
        if (mapChacheMsgItems.containsKey(key)) {
            return mapChacheMsgItems.get(key);
        }
        return null;
    }

    public String putMapChacheMsgItems(String key, String msg) {
        return mapChacheMsgItems.put(key, msg);
    }

    public void removeCacheMsgItem(String key) {
        if (mapChacheMsgItems.containsKey(key)) {
            mapChacheMsgItems.remove(key);
        }
    }

    public void clearCache() {
        mapChacheMsgItems.clear();
    }
}
