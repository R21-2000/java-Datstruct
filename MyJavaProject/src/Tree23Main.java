import com.datastruct.nmTree;

public class Tree23Main {
   public static void main(String[] args) {
      nmTree<Integer, String> theTree = new nmTree<>();

      theTree.insert(50, "A");
      theTree.insert(40, "B");
      theTree.insert(60, "C");
      theTree.insert(30, "D");
      theTree.insert(70, "E");

      theTree.insert(21, "F");
      theTree.insert(19, "G");
      theTree.insert(65, "H");
      theTree.insert(35, "I");
      theTree.insert(18, "J");
      theTree.insert(17, "K");
      theTree.insert(75, "L");
      theTree.insert(80, "M");
      theTree.displayTree();
   }
}