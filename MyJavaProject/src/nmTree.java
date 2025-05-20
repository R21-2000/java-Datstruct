class DataItem<K, V> {
   private K key;
   private V data;

   public DataItem(K key, V data) {
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

   public void displayItem() {
       System.out.print("/" + key + ":" + data);
   }
}

class nmNode<K, V> {
   private static final int ORDER = 4;
   private int numItems;
   private nmNode<K, V> parent;
   private nmNode<K, V>[] childArray = new nmNode[ORDER];
   private DataItem<K, V>[] itemArray = new DataItem[ORDER - 1];

   public nmNode() {
       // Java tidak mengizinkan generic array secara langsung, jadi ini warning biasa.
       childArray = (nmNode<K, V>[]) new nmNode[ORDER];
       itemArray = (DataItem<K, V>[]) new DataItem[ORDER - 1];
   }

   public void connectChild(int childNum, nmNode<K, V> child) {
       childArray[childNum] = child;
       if (child != null)
           child.parent = this;
   }

   public nmNode<K, V> disconnectChild(int childNum) {
       nmNode<K, V> temp = childArray[childNum];
       childArray[childNum] = null;
       return temp;
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
           else if (itemArray[j].getKey().equals(key))
               return j;
       }
       return -1;
   }

   public void deleteItem(K key) {
       for (int j = 0; j < numItems; j++) {
           if (itemArray[j] == null)
               break;
           else if (itemArray[j].getKey().equals(key)) {
               for (int k = j; k < numItems - 1; k++) {
                   itemArray[k] = itemArray[k + 1];
               }
               itemArray[numItems - 1] = null;
               numItems--;
               break;
           }
       }
   }

   public int insertItem(DataItem<K, V> newItem) {
       numItems++;
       K newKey = newItem.getKey();

       for (int j = ORDER - 2; j >= 0; j--) {
           if (itemArray[j] == null)
               continue;
           else {
               K itsKey = itemArray[j].getKey();
               if (((Comparable<K>) newKey).compareTo(itsKey) < 0)
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
       nmNode<K, V> current = root;
       int childNumber;
       while (true) {
           if ((childNumber = current.findItem(key)) != -1)
               return childNumber;
           else if (current.isLeaf())
               return -1;
           else
               current = getNextChild(current, key);
       }
   }

   public void insert(K key, V value) {
       nmNode<K, V> current = root;
       DataItem<K, V> tempItem = new DataItem<>(key, value);

       while (true) {
           if (current.isLeaf())
               break;
           else
               current = getNextChild(current, key);
       }
       current.insertItem(tempItem);
       if (current.isFull()) {
           split(current);
           current = current.getParent();
           while (current != null && current.isFull()) {
               split(current);
               current = current.getParent();
           }
       }
   }
     public void delete(K key) {
         nmNode<K, V> curNode = root;
 
         while (true) {
             if (curNode.isLeaf())
                 break;
             else
                 curNode = getNextChild(curNode, key);
         }
 
         if (curNode.isLeaf()) {
             if (curNode.getNumItems() > 1)
                 curNode.deleteItem(key);
             else {
                 int index = curNode.findItem(key);
                 root = adoptMerge(curNode, index);
             }
         }
     }
 
     public nmNode<K, V> adoptMerge(nmNode<K, V> curNode, int index) {
         boolean left = true;
         nmNode<K, V> parentNode = curNode.getParent();
         int numItems = parentNode.getNumItems();
         int j = 0;
         nmNode<K, V> siblingNode = null;
         int curPos = 0;
         while (j < numItems && parentNode.getChild(j) != curNode) {
             siblingNode = parentNode.getChild(j);
             ++j;
             curPos = j;
         }
         if (j == 0) {
             siblingNode = parentNode.getChild(j + 1);
             left = false;
         }
         if (siblingNode.getNumItems() == 1 && j < numItems) {
             if (parentNode.getChild(j + 1).getNumItems() > 1) {
                 siblingNode = parentNode.getChild(j + 1);
                 left = false;
             }
         }
         if (siblingNode.getNumItems() > 1) {
             if (left) {
                 curNode.getItem(index).setKey(parentNode.getItem(j - 1).getKey());
                 curNode.getItem(index).setData(parentNode.getItem(j - 1).getData());
                 parentNode.getItem(j - 1).setKey(siblingNode.getItem(siblingNode.getNumItems() - 1).getKey());
                 parentNode.getItem(j - 1).setData(siblingNode.getItem(siblingNode.getNumItems() - 1).getData());
                 if (!curNode.isLeaf()) {
                     nmNode<K, V> lastNode = siblingNode.disconnectChild(siblingNode.getNumItems());
                     nmNode<K, V> tempNode = curNode.disconnectChild(0);
                     curNode.connectChild(1, tempNode);
                     curNode.connectChild(0, lastNode);
                 }
                 siblingNode.removeItem();
             } else {
                 curNode.getItem(index).setKey(parentNode.getItem(j).getKey());
                 curNode.getItem(index).setData(parentNode.getItem(j).getData());
                 parentNode.getItem(j).setKey(siblingNode.getItem(0).getKey());
                 parentNode.getItem(j).setData(siblingNode.getItem(0).getData());
                 if (!curNode.isLeaf()) {
                     nmNode<K, V> firstNode = siblingNode.disconnectChild(0);
                     nmNode<K, V> tempNode = curNode.disconnectChild(curNode.getNumItems());
                     curNode.connectChild(0, tempNode);
                     curNode.connectChild(curNode.getNumItems(), firstNode);
                 }
                 siblingNode.deleteItem(siblingNode.getItem(0).getKey());
             }
         } else {
             if (parentNode.getNumItems() == 1) {
                 DataItem<K, V> tempItem = new DataItem<>(parentNode.getItem(0).getKey(), parentNode.getItem(0).getData());
                 siblingNode.insertItem(tempItem);
                 if (parentNode == root) {
                     return root;
                 } else {
                     curNode = parentNode;
                     return adoptMerge(curNode, 0);
                 }
             } else {
                 DataItem<K, V> tempItem = (curPos > 0)
                         ? new DataItem<>(parentNode.getItem(curPos - 1).getKey(), parentNode.getItem(curPos - 1).getData())
                         : new DataItem<>(parentNode.getItem(curPos).getKey(), parentNode.getItem(curPos).getData());
                 siblingNode.insertItem(tempItem);
                 if (left) {
                     for (j = curPos; j < parentNode.getNumItems(); ++j) {
                         nmNode<K, V> tempNode = parentNode.disconnectChild(j + 1);
                         parentNode.connectChild(j, tempNode);
                     }
                     parentNode.deleteItem(tempItem.getKey());
                 } else {
                     for (j = 0; j < parentNode.getNumItems(); ++j) {
                         nmNode<K, V> tempNode = parentNode.disconnectChild(j + 1);
                         parentNode.connectChild(j, tempNode);
                     }
                     parentNode.deleteItem(tempItem.getKey());
                 }
             }
         }
         return root;
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
 