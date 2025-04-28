package com.datastruct;

class DataItem<K extends Comparable<K>, V> {
   private K key;
   private V value;

   public DataItem(K key, V value) {
      this.key = key;
      this.value = value;
   }

   public K getKey() {
      return key;
   }

   public void setKey(K key) {
      this.key = key;
   }

   public V getValue() {
      return value;
   }

   public void setValue(V value) {
      this.value = value;
   }

   public void displayItem() {
      System.out.print("/" + key + ":" + value);
   }
}

class nmNode<K extends Comparable<K>, V> {
   private static final int ORDER = 4;
   private int numItems;
   private nmNode<K, V> parent;
   private nmNode<K, V>[] childArray = new nmNode[ORDER];
   private DataItem<K, V>[] itemArray = new DataItem[ORDER - 1];

   public void connectChild(int childNum, nmNode<K, V> child) {
      childArray[childNum] = child;
      if (child != null)
         child.parent = this;
   }

   public nmNode<K, V> disconnectChild(int childNum) {
      nmNode<K, V> tempNode = childArray[childNum];
      childArray[childNum] = null;
      return tempNode;
   }

   public nmNode<K, V> getChild(int childNum) {
      return childArray[childNum];
   }

   public nmNode<K, V> getParent() {
      return parent;
   }

   public boolean isLeaf() {
      return (childArray[0] == null);
   }

   public int getNumItems() {
      return numItems;
   }

   public DataItem<K, V> getItem(int index) {
      return itemArray[index];
   }

   public boolean isFull() {
      return (numItems == ORDER - 1);
   }

   public int findItem(K key) {
      for (int j = 0; j < ORDER - 1; j++) {
         if (itemArray[j] == null)
            break;
         else if (itemArray[j].getKey().compareTo(key) == 0)
            return j;
      }
      return -1;
   }

   public int insertItem(DataItem<K, V> newItem) {
      numItems++;
      K newKey = newItem.getKey();

      for (int j = ORDER - 2; j >= 0; j--) {
         if (itemArray[j] == null)
            continue;
         else {
            K itsKey = itemArray[j].getKey();
            if (newKey.compareTo(itsKey) < 0)
               itemArray[j + 1] = itemArray[j];
            else {
               itemArray[j + 1] = newItem;
               return j + 1;
            }
         }
      }
      itemArray[0] = newItem;
      return 0;
   }

   public DataItem<K, V> removeItem() {
      DataItem<K, V> temp = itemArray[numItems - 1];
      itemArray[numItems - 1] = null;
      numItems--;
      return temp;
   }

   public void displaynmNode() {
      for (int j = 0; j < numItems; j++)
         itemArray[j].displayItem();
      System.out.println("/");
   }
}

public class nmTree<K extends Comparable<K>, V> {
   private nmNode<K, V> root = new nmNode<>();

   public int find(K key) {
      nmNode<K, V> curNode = root;
      int childNumber;
      while (true) {
         if ((childNumber = curNode.findItem(key)) != -1)
            return childNumber;
         else if (curNode.isLeaf())
            return -1;
         else
            curNode = getNextChild(curNode, key);
      }
   }

   public void insert(K key, V value) {
      nmNode<K, V> curNode = root;
      DataItem<K, V> tempItem = new DataItem<>(key, value);

      while (true) {
         if (curNode.isLeaf())
            break;
         else
            curNode = getNextChild(curNode, key);
      }
      curNode.insertItem(tempItem);
      if (curNode.isFull()) {
         split(curNode);
         curNode = curNode.getParent();
         while (curNode.isFull()) {
            split(curNode);
            curNode = curNode.getParent();
         }
      }
   }

   public void split(nmNode<K, V> thisNode) {
      DataItem<K, V> itemB, itemC;
      nmNode<K, V> parent, child2, child3;
      int itemIndex;

      itemC = thisNode.removeItem();
      itemB = thisNode.removeItem();
      child2 = thisNode.disconnectChild(2);
      child3 = thisNode.disconnectChild(3);

      nmNode<K, V> newRight = new nmNode<>();

      if (thisNode == root) {
         root = new nmNode<>();
         parent = root;
         root.connectChild(0, thisNode);
      } else
         parent = thisNode.getParent();

      itemIndex = parent.insertItem(itemB);
      int n = parent.getNumItems();

      for (int j = n - 1; j > itemIndex; j--) {
         nmNode<K, V> temp = parent.disconnectChild(j);
         parent.connectChild(j + 1, temp);
      }
      parent.connectChild(itemIndex + 1, newRight);
      newRight.insertItem(itemC);
      newRight.connectChild(0, child2);
      newRight.connectChild(1, child3);
   }

   public nmNode<K, V> getNextChild(nmNode<K, V> theNode, K theValue) {
      int j;
      int numItems = theNode.getNumItems();
      for (j = 0; j < numItems; j++) {
         if (theValue.compareTo(theNode.getItem(j).getKey()) < 0)
            return theNode.getChild(j);
      }
      return theNode.getChild(j);
   }

   public void displayTree() {
      recDisplayTree(root, 0, 0);
   }

   private void recDisplayTree(nmNode<K, V> thisNode, int level, int childNumber) {
      System.out.print("level=" + level + " child=" + childNumber + " ");
      thisNode.displaynmNode();

      int numItems = thisNode.getNumItems();
      for (int j = 0; j < numItems + 1; j++) {
         nmNode<K, V> nextNode = thisNode.getChild(j);
         if (nextNode != null)
            recDisplayTree(nextNode, level + 1, j);
         else
            return;
      }
   }
}