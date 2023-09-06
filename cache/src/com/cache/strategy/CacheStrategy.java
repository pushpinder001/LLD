package com.cache.strategy;

public interface CacheStrategy<K> {
    boolean keyAccessed(K key);

    K evictKey();
}
