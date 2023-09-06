package com.cache.service;

import com.cache.client.CacheClient;
import com.cache.strategy.CacheStrategy;
import com.cache.strategy.Type;
import com.cache.strategy.impl.LRUStrategy;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CacheService <V> implements CacheClient {

    private static CacheStrategy getCacheStrategy(Type strategy) {
        return switch(strategy) {
            case LRU -> new LRUStrategy();
            default -> throw new RuntimeException(strategy + " is not valid");
        };
    }

    private CacheStrategy cacheStrategy;

    Map<String, V> dataStore;

    int capacity;

    public CacheService(Type type, Integer capacity) {
        this.cacheStrategy = getCacheStrategy(type);
        this.dataStore = new ConcurrentHashMap<>();
        this.capacity = capacity;
    }

    @Override
    public Optional<V> get(String key) {
        V val = dataStore.get(key);
        this.cacheStrategy.notifyAccess(key);
        return Optional.of(val);
    }

    @Override
    public boolean put(String key, Object val) {
        dataStore.put(key, (V)val);
        if(dataStore.size() > capacity) {
            this.cacheStrategy.evict();
        } else {
            this.cacheStrategy.notifyAccess(key);
        }

        return true;
    }

    @Override
    public int size() {
        return this.dataStore.size();
    }
}
