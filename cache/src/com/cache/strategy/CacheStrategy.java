package com.cache.strategy;

public interface CacheStrategy {
    boolean notifyAccess(String key);

    String evict();
}
