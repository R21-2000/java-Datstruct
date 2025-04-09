import com.datastruct.*;
public class ProgramMain {
    public static void main(String[] args) {
        // Membuat objek BinarySearchTree untuk menyimpan integer
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        // Data yang akan dimasukkan ke dalam BST
        int[] keys = {27, 54, 13, 24, 40, 56, 77, 33, 12, 85, 73, 10, 3, 15, 22};

        // Menyisipkan data ke dalam BST
        for (int key : keys) {
            bst.insert(key, Integer.toString(key)); // Menggunakan key sebagai data
        }

        // Menampilkan BST dalam urutan inorder
        System.out.println("Urutan Inorder BST:");
        bst.inOrder(); // Menampilkan elemen dalam urutan inorder

        // Menghapus kunci dari BST
        int[] keysToRemove = {33, 56, 27}; // Kunci yang akan dihapus
        for (int key : keysToRemove) {
            System.out.println("\nHapus key: " + key);
            bst.delete(key); // Menghapus kunci dari BST
            System.out.println("Bentuk Inorder Traversal Setelah Hapus Key " + key + ":");
            bst.inOrder(); // Menampilkan BST setelah penghapusan
        }
    }
}
