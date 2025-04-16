package com.datastruct;
/*
 * Modified from: http://www.java2s.com/ref/java/java-data-structures-234-tree.html
 * 
*/

class DataItem {
   private long dData;

   public DataItem(long dd) {
      dData = dd;
   }

   public void setdData(long dData) {
       this.dData = dData;
   }

   public long getdData() {
       return dData;
   }

   public void displayItem() {
      System.out.print("/" + dData);
   }
}

class nmNode {
   private static final int ORDER = 4; //2-3 Tree, ORDER = 4
   private int numItems;
   private nmNode parent;
   private nmNode childArray[] = new nmNode[ORDER];
   private DataItem itemArray[] = new DataItem[ORDER - 1];
   
   public void connectChild(int childNum, nmNode child) {
      childArray[childNum] = child;
      if (child != null)
         child.parent = this;
   }

   public nmNode disconnectChild(int childNum) {
      nmNode tempnmNode = childArray[childNum];
      childArray[childNum] = null;
      return tempnmNode;
   }

   public nmNode getChild(int childNum) {
      return childArray[childNum];
   }

   public nmNode getParent() {
      return parent;
   }

   public boolean isLeaf() {
      return (childArray[0] == null) ? true : false;
   }

   public int getNumItems() {
      return numItems;
   }

   public DataItem getItem(int index) // get DataItem at index
   {
      return itemArray[index];
   }

   public boolean isFull() {
      return (numItems == ORDER - 1) ? true : false;
   }

   public int findItem(long key)
   {
      for (int j = 0; j < ORDER - 1; j++) 
      {
         if (itemArray[j] == null) 
            break;
         else if (itemArray[j].getdData() == key)
            return j;
      }
      return -1;
   }

   //re-arrange items in a node after disconnect a child
   public void deleteItem(long key)
   {
      for (int j = 0; j < numItems; j++) 
      {
         if (itemArray[j] == null) 
            break;
         else if (itemArray[j].getdData() == key) {
            for (int k = j; k < numItems; k++) {
               itemArray[k] = itemArray[k+1];
            }
            --numItems;
            break;
         }
      }
   }

   public int insertItem(DataItem newItem) {
      numItems++;
      long newKey = newItem.getdData();

      for (int j = ORDER - 2; j >= 0; j--) {
         if (itemArray[j] == null)
            continue;
         else {
            long itsKey = itemArray[j].getdData();
            if (newKey < itsKey)
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

   public DataItem removeItem() {
      DataItem temp = itemArray[numItems - 1];
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

public class nmTree {
   private nmNode root = new nmNode();

   public int find(long key) {
      nmNode curnmNode = root;
      int childNumber;
      while (true) {
         if ((childNumber = curnmNode.findItem(key)) != -1)
            return childNumber; 
         else if (curnmNode.isLeaf())
            return -1; 
         else
            curnmNode = getNextChild(curnmNode, key);
      } 
   }
   public void insert(long dValue) {
      nmNode curnmNode = root;
      DataItem tempItem = new DataItem(dValue);
      
      while (true) {
         if (curnmNode.isLeaf())
            break;
         else
            curnmNode = getNextChild(curnmNode, dValue);
      }
      curnmNode.insertItem(tempItem);
      // dilanggar dahulu max items per nmNode dari 2 menjadi 3 
      if (curnmNode.isFull()) 
      {
        split(curnmNode); // split it
        curnmNode = curnmNode.getParent();
        // untuk setiap nmNode yang melanggar max items per nmNode
        while(curnmNode.isFull()){
            split(curnmNode); // split it
            curnmNode = curnmNode.getParent();
        }
      }
   }

   public nmNode adoptMerge(nmNode curNode, int index) {
      boolean left = true;
      nmNode parentNode = curNode.getParent();
      int numItems = parentNode.getNumItems();
      int j = 0; nmNode siblingNode = null;
      int curPos = 0;
      while(j < numItems && parentNode.getChild(j) != curNode) {
         siblingNode = parentNode.getChild(j);
         ++j;
         curPos = j;
      }
      if(j == 0) {
         siblingNode = parentNode.getChild(j+1);
         left = false;
      }
      //select right sibling if it has more items than the left with single item
      if(siblingNode.getNumItems() == 1 && j < numItems) {
         if(parentNode.getChild(j+1).getNumItems() > 1) {
            siblingNode = parentNode.getChild(j+1);
            left = false;
         }
      }
      //adopt
      if(siblingNode.getNumItems() > 1) {
         //left sibling
         if(left) {
            curNode.getItem(index).setdData(parentNode.getItem(j-1).getdData());
            parentNode.getItem(j-1).setdData(siblingNode.getItem(siblingNode.getNumItems()-1).getdData());
            if(curNode.isLeaf() == false) {
               nmNode lastNode = siblingNode.disconnectChild(siblingNode.getNumItems());
               nmNode tempNode = curNode.disconnectChild(0);
               curNode.connectChild(1, tempNode);
               curNode.connectChild(0, lastNode);
            }
            siblingNode.removeItem();
         }
         else {
            //right sibling
            curNode.getItem(index).setdData(parentNode.getItem(j).getdData());
            parentNode.getItem(j).setdData(siblingNode.getItem(0).getdData());
            if(curNode.isLeaf() == false) {
               nmNode firstNode = siblingNode.disconnectChild(0);
               nmNode tempNode = curNode.disconnectChild(curNode.getNumItems());
               curNode.connectChild(0, tempNode);
               curNode.connectChild(curNode.getNumItems(), firstNode);
            }
            siblingNode.deleteItem(siblingNode.getItem(0).getdData());
         }
      }
      //merge
      else {
         if(parentNode.getNumItems() == 1) {
            DataItem tempItem = new DataItem(parentNode.getItem(0).getdData());
            siblingNode.insertItem(tempItem);
            if(parentNode == root) {
               return root;   
            }
            //merge or adopt upward
            else {
               curNode = parentNode;
               return (adoptMerge(curNode, 0));
            }
         }
         else {
            DataItem tempItem = null;
            if(curPos > 0) tempItem = new DataItem(parentNode.getItem(curPos-1).getdData());
            else tempItem = new DataItem(parentNode.getItem(curPos).getdData());
            siblingNode.insertItem(tempItem);
            if(left) {
               for(j=curPos; j < parentNode.getNumItems(); ++j) {
                  nmNode tempNode = parentNode.disconnectChild(j+1);
                  parentNode.connectChild(j, tempNode);
               }
               parentNode.deleteItem(tempItem.getdData());
            }
            else {
               for(j=0; j < parentNode.getNumItems(); ++j) {
                  nmNode tempNode = parentNode.disconnectChild(j+1);
                  parentNode.connectChild(j, tempNode);
               }
               parentNode.deleteItem(tempItem.getdData());
            }
         }
      }
      return root;
   }

   //Delete a key from a leaf node in nmTree 
   public void delete(long dValue) {
      nmNode curNode = root;

      while (true) {
         if (curNode.isLeaf())
            break;
         else
            curNode = getNextChild(curNode, dValue);
      }
      //delete leaf node
      if(curNode.isLeaf()) { 
         if(curNode.getNumItems() > 1) 
            curNode.deleteItem(dValue);
         else {
            int index = curNode.findItem(dValue);
            root = adoptMerge(curNode, index);//, -1);
         }   
      }
      //delete internal node
   }   

   public void split(nmNode thisnmNode) {
      DataItem itemB, itemC;
      nmNode parent, child2, child3;
      int itemIndex;

      itemC = thisnmNode.removeItem();
      itemB = thisnmNode.removeItem();
      child2 = thisnmNode.disconnectChild(2);
      child3 = thisnmNode.disconnectChild(3);
      
      nmNode newRight = new nmNode();

      if (thisnmNode == root) {
         root = new nmNode();
         parent = root;
         root.connectChild(0, thisnmNode);
      } else
         parent = thisnmNode.getParent();

      itemIndex = parent.insertItem(itemB);
      int n = parent.getNumItems();

      for (int j = n - 1; j > itemIndex; j--) {
         nmNode temp = parent.disconnectChild(j);
         parent.connectChild(j + 1, temp);
      }
      parent.connectChild(itemIndex + 1, newRight);
      newRight.insertItem(itemC);
      newRight.connectChild(0, child2);
      newRight.connectChild(1, child3);
   }

   public nmNode getNextChild(nmNode thenmNode, long theValue) {
      int j;

      int numItems = thenmNode.getNumItems();
      for (j = 0; j < numItems; j++) {
         if (theValue < thenmNode.getItem(j).getdData())
            return thenmNode.getChild(j);
      }
      return thenmNode.getChild(j);
   }

   public void displayTree() {
      recDisplayTree(root, 0, 0);
   }

   private void recDisplayTree(nmNode thisnmNode, int level, int childNumber) {
      System.out.print("level=" + level + " child=" + childNumber + " ");
      thisnmNode.displaynmNode(); 

      int numItems = thisnmNode.getNumItems();
      for (int j = 0; j < numItems + 1; j++) {
         nmNode nextnmNode = thisnmNode.getChild(j);
         if (nextnmNode != null)
            recDisplayTree(nextnmNode, level + 1, j);
         else
            return;
      }
   }

}