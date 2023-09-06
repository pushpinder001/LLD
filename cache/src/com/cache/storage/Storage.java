package com.cache.storage;

import com.cache.exception.StorageFullException;

public interface Storage<K, V> {
    V get(K key);

    boolean put(K key, V val) throws StorageFullException;

    boolean remove(K key);

    int size();
}
