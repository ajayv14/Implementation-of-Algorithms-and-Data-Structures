package com.app.models;

// Just to make coding easy. Ideally should have all properties in private
public class Pair<K,V> {

    public K key;
    public V value;

    public Pair(K k, V v){
        this.key = k;
        this.value = v;
    }
}
