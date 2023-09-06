package com.cache.strategy.impl;

import com.cache.strategy.CacheStrategy;

public class LRUStrategy implements CacheStrategy {
    @Override
    public boolean notifyAccess(String key) {
        return false;
    }

    @Override
    public String evict() {
        return null;
    }
}
