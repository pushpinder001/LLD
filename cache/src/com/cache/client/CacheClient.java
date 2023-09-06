package com.cache.client;

public interface CacheClient <V> {
    V get(String key);

    boolean put(String key, V val);

    int size();
}
