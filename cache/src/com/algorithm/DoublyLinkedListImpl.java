package com.algorithm;

public class DoublyLinkedListImpl<E> implements DoublyLinkedList{
    private DoublyLinkedListNode head, tail;
    private int size;

    public DoublyLinkedListImpl() {
        this.head = new DoublyLinkedListNode(null, null, null);
        this.tail = new DoublyLinkedListNode(null, null, null);
    }

    private DoublyLinkedListNode createNode(E node) {
        return new DoublyLinkedListNode(node, null, null);
    }
    @Override
    public boolean removeNode(DoublyLinkedListNode node) {
        if (head.next == tail) {
            return false;
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return true;
    }

    @Override
    public boolean addAtLast(DoublyLinkedListNode node) {
        tail.prev.next = node;
        node.prev = tail.prev;

        tail.prev = node;
        node.next = tail;
        size++;
        return true;
    }

    @Override
    public DoublyLinkedListNode getFirst() {
        if(head.next == tail) {
            return null;
        }
        return head.next;
    }

    @Override
    public DoublyLinkedListNode addNewNode(Object o) {
        E e = (E)o;

        DoublyLinkedListNode node = createNode(e);
        addAtLast(node);
        return node;
    }

    @Override
    public int getSize() {
        return size;
    }
}
