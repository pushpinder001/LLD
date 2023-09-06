package com.cache.strategy.impl;

import com.algorithm.DoublyLinkedList;
import com.algorithm.DoublyLinkedListImpl;
import com.algorithm.DoublyLinkedListNode;
import com.cache.strategy.CacheStrategy;

import java.util.HashMap;
import java.util.Map;

public class LRUStrategy<K> implements CacheStrategy {
    private Map<K, DoublyLinkedListNode> keyToNodeMapping;
    private DoublyLinkedList list;

    public LRUStrategy() {
        this.keyToNodeMapping = new HashMap<>();
        this.list = new DoublyLinkedListImpl();
    }

    @Override
    public boolean keyAccessed(Object key) {
        K k = (K)key;
        if(keyToNodeMapping.containsKey(k)) {
            list.removeNode(keyToNodeMapping.get(k));
            list.addAtLast(keyToNodeMapping.get(k));
            return true;
        }

        list.addNewNode(key);
        return true;
    }

    @Override
    public K evictKey() {
        DoublyLinkedListNode node = list.getFirst();

        if(node == null) {
            throw null;
        }

        keyToNodeMapping.remove(node.getVal());
        list.removeNode(node);

        return (K)node.getVal();
    }
}
