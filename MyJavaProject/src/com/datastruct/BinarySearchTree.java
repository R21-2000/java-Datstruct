package com.datastruct;

// Kelas BinarySearchTree yang menggunakan generik K dan V
public class BinarySearchTree<K extends Comparable<? super K>, V> 
                        extends BinaryTree<K, V> 
                        implements Tree<K, V> {
    
    private BTNode<K, V> root; // Node akar dari BST

    // Konstruktor untuk menginisialisasi BST
    public BinarySearchTree() {
        root = null; // Akar diinisialisasi sebagai null
    }

    // Metode untuk menyisipkan kunci dan data ke dalam BST
    public void insert(K key, V data) {
        root = insertNode(root, key, data); // Memanggil metode rekursif untuk menyisipkan node
    }

    // Metode untuk menghapus kunci dari BST
    public void delete(K key) {
        root = deleteNode(root, key); // Memanggil metode rekursif untuk menghapus node
    }

    // Metode untuk mencari data berdasarkan kunci
    public V search(K key) {
        V info = null; // Inisialisasi variabel untuk menyimpan data
        info = getData(find(root, key)); // Mencari node dan mendapatkan data
        return info; // Mengembalikan data yang ditemukan
    }

    // Metode untuk menemukan node dengan kunci maksimum
    public K max() {
        K kunci = null; // Inisialisasi variabel untuk menyimpan kunci maksimum
        kunci = getKey(findMax(root)); // Mencari kunci maksimum
        return kunci; // Mengembalikan kunci maksimum
    }
    
    // Metode untuk menemukan node dengan kunci minimum
    public K min() {
        K kunci = null; // Inisialisasi variabel untuk menyimpan kunci minimum
        kunci = getKey(findMin(root)); // Mencari kunci minimum
        return kunci; // Mengembalikan kunci minimum
    }

    // Metode untuk melakukan traversal inorder
    public void inOrder() {
        printInOrder(root); // Memanggil metode untuk mencetak traversal inorder
    }

    // Metode untuk melakukan traversal preorder
    public void preOrder() {
        printPreOrder(root); // Memanggil metode untuk mencetak traversal preorder
    }

    // Metode untuk melakukan traversal postorder
    public void postOrder() {
        printPostOrder(root); // Memanggil metode untuk mencetak traversal postorder
    }

    // Metode untuk melakukan traversal level-order
    public void levelOrder() {
        printLevelOrder(root); // Memanggil metode untuk mencetak traversal level-order
    }
    
    // Metode untuk mendapatkan kunci dari node
    public K getKey(BTNode<K, V> node) {
        return node.getKey(); // Mengembalikan kunci dari node
    }

    // Metode untuk mendapatkan data dari node
    public V getData(BTNode<K, V> node) {
        return node.getData(); // Mengembalikan data dari node
    }

    // Metode untuk menyisipkan node secara rekursif
    private BTNode<K, V> insertNode(BTNode<K, V> node, K k, V data) {
        if (node == null) {
            // Jika node kosong, buat node baru
            BTNode<K, V> newNode = new BTNode<K, V>(k, data);
            return newNode; // Kembalikan node baru
        }
        // Jika kunci baru lebih kecil, masukkan ke subtree kiri
        else if (k.compareTo(node.getKey()) < 0) {
            node.setLlink(insertNode(node.getLlink(), k, data));
            return node; // Kembalikan node yang telah diperbarui
        }
        // Jika kunci baru lebih besar atau sama, masukkan ke subtree kanan
        else {
            node.setRlink(insertNode(node.getRlink(), k, data));
            return node; // Kembalikan node yang telah diperbarui
        }
    }

    // Metode untuk menghapus node secara rekursif
    private BTNode<K, V> deleteNode(BTNode<K, V> node, K k) {
        if (node == null) return node; // Jika node kosong, kembalikan null
        // Jika kunci yang dicari lebih besar, telusuri ke kanan
        else if (node.getKey().compareTo(k) < 0) {
            node.setRlink(deleteNode(node.getRlink(), k));
            return node; // Kembalikan node yang telah diperbarui
        }
        // Jika kunci yang dicari lebih kecil, telusuri ke kiri
        else if (node.getKey().compareTo(k) > 0) {
            node.setLlink(deleteNode(node.getLlink(), k));
            return node; // Kembalikan node yang telah diperbarui
        }
        // Node yang akan dihapus ditemukan
        // Jika tidak memiliki anak di kanan
        if (node.getLlink() == null) {
            BTNode<K, V> temp = node.getRlink();
            return temp; // Kembalikan anak kanan
        }
        // Jika tidak memiliki anak di kiri
        else if (node.getRlink() == null) {
            BTNode<K, V> temp = node.getLlink();
            return temp; // Kembalikan anak kiri
        }
        // Jika memiliki kedua anak
        else {
            // Cari node dengan kunci terbesar dari subtree kiri
            BTNode<K, V> parent = node; 
            BTNode<K, V> child = node.getLlink();
            while (child.getRlink() != null) {
                parent = child;
                child = child.getRlink();
            }
            // Jika ditemukan node dengan kunci terbesar dari subtree kiri
            if (parent != node) parent.setRlink(child.getLlink());
            else parent.setLlink(child.getLlink());

            // Salin data dari node dengan kunci terbesar ke node yang akan dihapus
            node.setKey(child.getKey());
            node.setData(child.getData());

            return node; // Kembalikan node yang telah diperbarui
        }
    }

    // Metode untuk mencari node berdasarkan kunci
    private BTNode<K, V> find(BTNode<K, V> node, K k) {
        if (node == null || node.getKey() == k) return node; // Jika node kosong atau kunci ditemukan
        else if (node.getKey().compareTo(k) < 0) return find(node.getRlink(), k); // Telusuri ke kanan
        else return find(node.getLlink(), k); // Telusuri ke kiri
    }

    // Metode untuk menemukan node dengan kunci minimum
    private BTNode<K, V> findMin(BTNode<K, V> node) {
        if (node == null || node.getLlink() == null) return node; // Jika node kosong atau tidak ada anak kiri
        else return findMin(node.getLlink()); // Telusuri ke anak kiri
    }

    // Metode untuk menemukan node dengan kunci maksimum
    private BTNode<K, V> findMax(BTNode<K, V> node) {
        if (node == null || node.getRlink() == null) return node; // Jika node kosong atau tidak ada anak kanan
        else return findMax(node.getRlink()); // Telusuri ke anak kanan
    }
}
