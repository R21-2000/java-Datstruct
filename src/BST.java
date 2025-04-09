import com.datastruct.*;

public class BST {
    public static void main(String[] args) {
        // Buat instance dari BinarySearchTree pakai tipe Integer sama string
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        // Insert Key ke dalam BSTnya
        bst.insert(27, "Node 27");
        bst.insert(54, "Node 54");
        bst.insert(13, "Node 13");
        bst.insert(24, "Node 24");
        bst.insert(40, "Node 40");
        bst.insert(56, "Node 56");
        bst.insert(77, "Node 77");
        bst.insert(33, "Node 33");
        bst.insert(12, "Node 12");
        bst.insert(85, "Node 85");
        bst.insert(73, "Node 73");
        bst.insert(10, "Node 10");
        bst.insert(3, "Node 3");
        bst.insert(15, "Node 15");
        bst.insert(22, "Node 22");

        // Show BST sesuai urutan level
        System.out.print("Levelorder: ");
        bst.levelOrder();
        System.out.println();

        // Hapus Key 33 dari BST
        bst.delete(33);
        System.out.print("Levelorder setelah Hapus key 33: ");
        bst.levelOrder();
        System.out.println();

        // Hapus Key 56 dari BST
        bst.delete(56);
        System.out.print("Levelorder setelah Hapus key 56: ");
        bst.levelOrder();
        System.out.println();

        // Hapus Key 27 dari BST
        bst.delete(27);
        System.out.print("Levelorder setelah Hapus key 27: ");
        bst.levelOrder();
        System.out.println();
    }
}
