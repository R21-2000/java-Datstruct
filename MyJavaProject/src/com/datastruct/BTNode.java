package com.datastruct;

public class BTNode<K extends Comparable<? super K>, V> {
    private K key;
    private V data;

    public BTNode(K key, V data) {
        this.key = key;
        this.data = data;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[" + key + ", " + data + "]";
    }
}
