import com.datastruct.*;

// Kelas untuk menyimpan informasi mahasiswa
class Student {
    private int nim; // Nomor Induk Mahasiswa
    private String nama; // Nama Mahasiswa

    // Konstruktor untuk menginisialisasi objek Student
    Student(int nim, String nama) {
        this.nim = nim; // Menggunakan 'this' untuk membedakan antara variabel kelas dan parameter
        this.nama = nama;
    }

    // Override metode toString untuk menampilkan informasi mahasiswa
    @Override
    public String toString() {
        return "NIM: " + nim + ", Nama: " + nama;
    }
}

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
        System.out.println("Inorder Traversal of BST:");
        bst.inOrder(); // Menampilkan elemen dalam urutan inorder

        // Menghapus kunci dari BST
        int[] keysToRemove = {33, 56, 27}; // Kunci yang akan dihapus
        for (int key : keysToRemove) {
            System.out.println("\nRemoving key: " + key);
            bst.delete(key); // Menghapus kunci dari BST
            System.out.println("Inorder Traversal after removing " + key + ":");
            bst.inOrder(); // Menampilkan BST setelah penghapusan
        }
    }
}
