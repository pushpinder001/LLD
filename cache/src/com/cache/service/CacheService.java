package com.cache.service;

import com.cache.client.CacheClient;
import com.cache.exception.StorageFullException;
import com.cache.storage.Storage;
import com.cache.storage.impl.HashMapStorage;
import com.cache.strategy.CacheStrategy;
import com.cache.strategy.CacheStrategyFactory;
import com.cache.strategy.Type;

import java.util.Optional;

public class CacheService <K, V> implements CacheClient {
    private CacheStrategy cacheStrategy;

    Storage dataStore;

    int capacity;

    public CacheService(Type type, Integer capacity, Storage storage) {
        this.cacheStrategy = CacheStrategyFactory.getCacheStrategy(type);
        this.dataStore = storage;
        this.capacity = capacity;
    }

    @Override
    public Optional<V> get(String key) {
        Object val = dataStore.get(key);
        if(val == null) {
            return Optional.of(null);
        }

        this.cacheStrategy.keyAccessed(key);
        return Optional.of((V)val);
    }

    @Override
    public boolean put(String key, Object val) {
        try {
            dataStore.put(key, (V) val);
            cacheStrategy.keyAccessed(key);
        } catch (StorageFullException e) {
            Object evictedKey = this.cacheStrategy.evictKey();
            if(evictedKey != null) {
                dataStore.remove(evictedKey);
            }

            put(key, val);
        }
        return true;
    }

    @Override
    public int size() {
        return this.dataStore.size();
    }
}
