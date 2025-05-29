import com.datastruct.nmTree;

public class Tree23Main {
   public static void main(String[] args) {
      nmTree<Integer, String> theTree = new nmTree<>();

      theTree.insert(42, "A");
      theTree.insert(22, "B");
      theTree.insert(58, "C");
      theTree.insert(12, "D");
      theTree.insert(51, "E");
      theTree.insert(15, "F");
      theTree.insert(30, "G");
      theTree.insert(71, "H");
      theTree.insert(8, "I");
      theTree.insert(23, "J");
      theTree.insert(77, "K");
      theTree.insert(2, "L");
      theTree.insert(80, "M");
      theTree.insert(3, "N");
      theTree.insert(10, "O");
      theTree.insert(17, "P");
      theTree.insert(5, "Q");
      theTree.insert(62, "R");
      theTree.insert(9, "S");
      theTree.insert(55, "T");
      theTree.insert(88, "U");
      theTree.displayTree();

      theTree.delete(88);
      System.out.println("============================================");
      System.out.println("2-3 tree setelah delete:");
      theTree.displayTree();
   }
}