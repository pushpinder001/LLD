package com.cache.storage.impl;

import com.cache.exception.StorageFullException;
import com.cache.storage.Storage;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<K,V> implements Storage {
    private Map<K, V> map;
    private int capacity;

    public HashMapStorage(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public Object get(Object key) {
        return this.map.get(key);
    }

    @Override
    public boolean put(Object key, Object val) throws StorageFullException {
        if(map.size() == capacity) {
            throw new StorageFullException("Storage is full");
        }

        map.put((K)key, (V)val);
        return true;
    }

    @Override
    public boolean remove(Object key) {
        this.map.remove(key);
        return true;
    }

    @Override
    public int size() {
        return this.map.size();
    }
}
