/*
 * Modified from: http://www.java2s.com/ref/java/java-data-structures-234-tree.html
 * 
*/
import com.datastruct.nmTree;

public class Tree23Main {
   public static void main(String[] args) {
      nmTree theTree = new nmTree();

      theTree.insert(50);
      theTree.insert(40);
      theTree.insert(60);
      theTree.insert(30);
      theTree.insert(70);

      theTree.insert(21);
      theTree.insert(19);
      theTree.insert(65);
      theTree.insert(35);
      theTree.insert(18);
      theTree.insert(17);
      theTree.insert(75);
      //theTree.insert(73);
      theTree.insert(80);
      theTree.displayTree();
      
      int found = theTree.find(30);
      if (found != -1)
         System.out.println("40 ditemukan di tree");
      else
         System.out.println("40 tidak ditemukan di tree ");

      theTree.delete(60);
      System.out.println("2-3 tree after delete:");
      theTree.displayTree();
   }
}