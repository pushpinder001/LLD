package com.project.app.service.impl;

import com.project.app.service.ILockService;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LockServiceInMemoryMapImpl implements ILockService {
    Map<String, Map<String, String>> map;

    public LockServiceInMemoryMapImpl() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public boolean getLock(String keyPrefix, String key, String value, int TTL_IN_SEC) {
        map.putIfAbsent(keyPrefix, new ConcurrentHashMap<>());

        var mapKeyPrefix = map.get(keyPrefix);

        synchronized (mapKeyPrefix) {
            if(!mapKeyPrefix.containsKey(key) || (isValueValid(mapKeyPrefix.get(key)))) {
                mapKeyPrefix.put(key, getValueWithTime(value, TTL_IN_SEC));
                return true;
            }

            return false;
        }
    }

    @Override
    public boolean isLockAcquiredWithValue(String keyPrefix, String key, String value, int TTL_IN_SEC) {
        map.putIfAbsent(keyPrefix, new ConcurrentHashMap<>());

        var mapKeyPrefix = map.get(keyPrefix);
        synchronized (mapKeyPrefix) {
            if(mapKeyPrefix.containsKey(key) && (isValueValid(mapKeyPrefix.get(key)) &&
                isValueEquals(value))) {
                mapKeyPrefix.put(key, getValueWithTime(value, TTL_IN_SEC));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getLockedKeysWithKeyPrefix(String keyPrefix) {
        map.putIfAbsent(keyPrefix, new ConcurrentHashMap<>());

        var mapKeyPrefix = map.getOrDefault(keyPrefix, Map.of());
        return mapKeyPrefix.entrySet().stream().filter(e -> isValueValid(e.getValue()))
                .map(Map.Entry::getKey).toList();
    }

    private String getValueWithTime(String val, int TTL_IN_SEC) {
        return val + "::" + (Instant.now().getEpochSecond() + TTL_IN_SEC);
    }

    private boolean isValueValid(String value) {
        return Integer.parseInt(value.split("::")[1]) > Instant.now().getEpochSecond();
    }

    private boolean isValueEquals(String value) {
        return value.split("::")[0].equals(value);
    }

    private String extractValue(String val) {
        return val.split("::")[0];
    }
}
