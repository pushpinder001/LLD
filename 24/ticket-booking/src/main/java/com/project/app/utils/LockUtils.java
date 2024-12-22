package com.project.app.utils;

public final class LockUtils {
    public String getKey(String key1, String key2) {
        return key1 + "::" + key2;
    }
}
