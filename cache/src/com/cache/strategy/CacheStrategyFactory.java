package com.cache.strategy;

import com.cache.strategy.impl.LRUStrategy;

public final class CacheStrategyFactory {
    public static CacheStrategy getCacheStrategy(Type strategy) {
        return switch(strategy) {
            case LRU -> new LRUStrategy();
            default -> throw new RuntimeException(strategy + " is not valid");
        };
    }
}
