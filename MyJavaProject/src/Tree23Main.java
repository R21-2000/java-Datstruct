public class Tree23Main {
   public static void main(String[] args) {
       nmTree<Integer, String> theTree = new nmTree<>();

       theTree.insert(50, "Fifty");
       theTree.insert(40, "Forty");
       theTree.insert(60, "Sixty");
       theTree.insert(30, "Thirty");
       theTree.insert(70, "Seventy");
       theTree.insert(21, "Twenty-One");
       theTree.insert(19, "Nineteen");
       theTree.insert(65, "Sixty-Five");
       theTree.insert(35, "Thirty-Five");
       theTree.insert(18, "Eighteen");
       theTree.insert(17, "Seventeen");
       theTree.insert(75, "Seventy-Five");
       theTree.insert(80, "Eighty");

       theTree.displayTree();

       int found = theTree.find(30);
       if (found != -1)
           System.out.println("30 ditemukan di tree");
       else
           System.out.println("30 tidak ditemukan di tree");

       theTree.delete(60);
       System.out.println("2-3 tree setelah delete:");
       theTree.displayTree();
   }
}