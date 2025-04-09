package com.datastruct;

public class BinarySearchTree<K extends Comparable<? super K>, V> 
                        extends BinaryTree<K, V> 
                        implements Tree<K, V> {
    
    private BTNode<K, V> root; // Node root dari BST

    // Konstruktor buat inisialisasi BST
    public BinarySearchTree() {
        root = null; // Root diinisialisasi jadi null
    }

    // Method buat menyisipkan key dan data ke BST
    public void insert(K key, V data) {
        root = insertNode(root, key, data); // Panggil method rekursif buat menyisipkan node
    }

    // Method buat hapus key dari BST
    public void delete(K key) {
        root = deleteNode(root, key); // Panggil method rekursif buat hapus node
    }

    // Method buat cari data berdasarkan key
    public V search(K key) {
        V info = null; // Inisialisasi variabel buat nyimpen data
        info = getData(find(root, key)); // Cari node dan dapat datanya
        return info; // Return data yang udah ketemu
    }

    // Method buat menemukan node pake key maksimum
    public K max() {
        K kunci = null; // Inisialisasi variabel buat nyimpen key maksimum
        kunci = getKey(findMax(root)); // Cari key maksimum
        return kunci; // Return key maksimum
    }
    
    // Method buat menemukan node pake kunci minimum
    public K min() {
        K kunci = null; // Inisialisasi variabel buat nyimpen key minimum
        kunci = getKey(findMin(root)); // Cari key minimum
        return kunci; // Return key minimum
    }

    // Method buat eksekusi traversal inorder
    public void inOrder() {
        printInOrder(root); // Panggil method buat cetak traversal inorder
    }

    // Method buat eksekusi traversal preorder
    public void preOrder() {
        printPreOrder(root); // Panggil method buat cetak traversal preorder
    }

    // Method buat eksekusi traversal postorder
    public void postOrder() {
        printPostOrder(root); // Panggil Method buat cetak traversal postorder
    }

    // Method buat eksekusi traversal level-order
    public void levelOrder() {
        printLevelOrder(root); // Panggil Method buat cetak traversal level-order
    }
    
    // Method buat dapatin key dari node
    public K getKey(BTNode<K, V> node) {
        return node.getKey(); // return key dari node
    }

    // Method buat dapetin data dari node
    public V getData(BTNode<K, V> node) {
        return node.getData(); // return data dari node
    }

    // Method buat menyisipkan node secara rekursif
    private BTNode<K, V> insertNode(BTNode<K, V> node, K k, V data) {
        if (node == null) {
            // Kalo node kosong, buat node baru
            BTNode<K, V> newNode = new BTNode<K, V>(k, data);
            return newNode; // Return node barunya
        }
        // Kalo key baru kebih kecil, masukkin ke subtree sebelah kiri
        else if (k.compareTo(node.getKey()) < 0) {
            node.setLlink(insertNode(node.getLlink(), k, data));
            return node; // Return node yang udah diperbarui
        }
        // Kalo key baru lebih besar atau sama, masukkin ke subtree kanan
        else {
            node.setRlink(insertNode(node.getRlink(), k, data));
            return node; // return node yang udah diperbarui
        }
    }

    // Method buat hapus node secara rekursif
    private BTNode<K, V> deleteNode(BTNode<K, V> node, K k) {
        if (node == null) return node; // Kalo node kosong, return null
        // Kalo key yang dicari lebih besar, cek ke kanan
        else if (node.getKey().compareTo(k) < 0) {
            node.setRlink(deleteNode(node.getRlink(), k));
            return node; // Return node yang udah diperbarui
        }
        // Kalo key yang dicari lebih kecil, cek ke kiri
        else if (node.getKey().compareTo(k) > 0) {
            node.setLlink(deleteNode(node.getLlink(), k));
            return node; // Return node yang udah diperbarui
        }
        // Nemu node yang mau diapus
        // Kalo gak punya child node di kanan
        if (node.getLlink() == null) {
            BTNode<K, V> temp = node.getRlink();
            return temp; // Return right child node
        }
        // Kalo gak punya child node di kiri
        else if (node.getRlink() == null) {
            BTNode<K, V> temp = node.getLlink();
            return temp; // Return left child node
        }
        // Kalo punya right dan left child node
        else {
            // Cari node yang punya key terbesar dari subtree kiri
            BTNode<K, V> parent = node; 
            BTNode<K, V> child = node.getLlink();
            while (child.getRlink() != null) {
                parent = child;
                child = child.getRlink();
            }
            // Kalo nemu node yang keynya paling besar dari subtree kiri
            if (parent != node) parent.setRlink(child.getLlink());
            // Kalo gak nemu node yang keynya paling besar dari subtree kiri
            else parent.setLlink(child.getLlink());

            // copy data dari node yang keynya terbesar node yang mau dihapus
            node.setKey(child.getKey());
            node.setData(child.getData());

            return node; // Return node yang udah diperbarui disini
        }
    }

    // Method buat cari node based on key
    private BTNode<K, V> find(BTNode<K, V> node, K k) {
        if (node == null || node.getKey() == k) return node; // Kalo nodenya kosong atau keynya ketemu
        else if (node.getKey().compareTo(k) < 0) return find(node.getRlink(), k); // Cek ke kanan
        else return find(node.getLlink(), k); // Cek ke kiri
    }

    // Metode buat nemuin node yang punya key minimum
    private BTNode<K, V> findMin(BTNode<K, V> node) {
        if (node == null || node.getLlink() == null) return node; // Kalo node kosong atau gaada left child node
        else return findMin(node.getLlink()); // cek ke kiri
    }

    // Method buat nemuin node yang punya key maksimum
    private BTNode<K, V> findMax(BTNode<K, V> node) {
        if (node == null || node.getRlink() == null) return node; // Kalo node kosong atau gaada right child node
        else return findMax(node.getRlink()); // cek kek kananya
    }
}
