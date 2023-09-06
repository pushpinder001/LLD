package com.algorithm;

public interface DoublyLinkedList<E> {
    boolean removeNode(DoublyLinkedListNode node);

    boolean addAtLast(DoublyLinkedListNode node);

    DoublyLinkedListNode getFirst();

    DoublyLinkedListNode addNewNode(E e);

    int getSize();
}
