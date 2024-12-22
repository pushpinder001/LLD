package com.project.app.service;

import java.util.List;

public interface ILockService {
    boolean getLock(String keyPrefix, String key, String value, int ttlSecs);

    List<String> getLockedKeysWithKeyPrefix(String keyPrefix);

    boolean isLockAcquiredWithValue(String keyPrefix, String key, String value, int TTL_IN_SEC);
}
