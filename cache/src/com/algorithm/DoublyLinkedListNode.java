package com.algorithm;

public class DoublyLinkedListNode<E> {
    E val;

    DoublyLinkedListNode prev;

    DoublyLinkedListNode next;

    public DoublyLinkedListNode(E val, DoublyLinkedListNode prev, DoublyLinkedListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

    public E getVal() {
        return val;
    }
}
