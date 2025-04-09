// Source code is decompiled from a .class file using FernFlower decompiler.
package com.datastruct;

public class BTNode<K, V> {
   public K key;
   public V data;
   private BTNode<K, V> llink;
   private BTNode<K, V> rlink;

   public BTNode(K k, V data) {
      this.key = k;
      this.data = data;
      this.llink = null;
      this.rlink = null;
   }

   public void setKey(K key) {
      this.key = key;
   }

   public K getKey() {
      return this.key;
   }

   public void setData(V data) {
      this.data = data;
   }

   public V getData() {
      return this.data;
   }

   public void setLlink(BTNode<K, V> llink) {
      this.llink = llink;
   }

   public BTNode<K, V> getLlink() {
      return this.llink;
   }

   public void setRlink(BTNode<K, V> rlink) {
      this.rlink = rlink;
   }

   public BTNode<K, V> getRlink() {
      return this.rlink;
   }

   @Override
    public String toString() {
        return "(" + key + ", " + data + ")";
    }
}
