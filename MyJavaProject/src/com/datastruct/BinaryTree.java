package com.datastruct;
/*
 * Create Simple Binary Search Tree
 * 
 * @author: Lely Hiryanto
 * 
 */

 class BTNode<K, V> { // Definisi class BTNode pakai generik K dan V
    private K key; // Menyimpan key darnode, biasanya berupa bilangan bulat
    private V data; // Menyimpan data yang terkait dengan kunci, bisa berupa objek dari kelas lain
    private BTNode<K, V> llink; // Referensi ke anak kiri dari node
    private BTNode<K, V> rlink; // Referensi ke anak kanan dari node

    // Konstruktor buat inisialisasi node pakai key
    public BTNode(K k, V data) {
        this.key = k; // key dari node
        this.data = data; // data dari node
        this.llink = null; // Inisialisasi leftlink jadi null
        this.rlink = null; //  Inisialisasi rightlink jadi null
    }

    // Ngatur key node
    public void setKey(K key) {
        this.key = key; // Ngatur key node pake value baru
    }

    // Method buat dapet key node
    public K getKey() {
        return key; // Return value key node
    }

    // Ngatur data node
    public void setData(V data) {
        this.data = data; // Set data node pake value baru
    }

    // Method buat dapet data node
    public V getData() {
        return data; // Return value dari data node
    }

    // Method buat set leftlink
    public void setLlink(BTNode<K, V> llink) {
        this.llink = llink; // set reference leftlink
    }

    // Method buat get leftlink
    public BTNode<K, V> getLlink() {
        return llink; // return reference dari leftlink
    }

    // Method buat set rightlink
    public void setRlink(BTNode<K, V> rlink) {
        this.rlink = rlink; // return reference dari rightlink
    }

    // Method buat get rightlink
    public BTNode<K, V> getRlink() {
        return rlink; // return reference dari rightlink
    }
}

public class BinaryTree<K, V>{// Definisi class BinaryTree pakai generik K dan V

    // Method buat cetak elemen di list inorder
    public void printInOrder(BTNode<K, V> node) { // set node jadi parameter
        if (node == null) return;    // Kalo node kosong,langsung keluar dari method
        printInOrder(node.getLlink()); // Rekursif ke leftlink
        System.out.print(node.getKey() + ":" + node.getData() + " "); // print key dan data
        printInOrder(node.getRlink()); // Rekursif ke rightlink
    }

    // Method buat cetak elemen di list postorder
    public void printPostOrder(BTNode<K, V> node) { // set node jadi parameter
        if (node == null) return; // Kalo node kosong,langsung keluar dari method
        printPostOrder(node.getLlink()); // Rekursif ke leftlink
        printPostOrder(node.getRlink()); // Rekursif ke rightlink
        System.out.print(node.getKey() + ":" + node.getData() + " "); //print key dan data
    }

    // Method buat cetak elemen di list preorder
    public void printPreOrder(BTNode<K, V> node) { // set node jadi parameter
        if (node == null) return; // Kalo node kosong,langsung keluar dari method
        System.out.print(node.getKey() + ":" + node.getData() + " "); // print key dan data
        printPreOrder(node.getLlink()); // Rekursif ke leftlink
        printPreOrder(node.getRlink()); // Rekursif ke rightlink
    }
    
    private void printLevelOrderRec(MyLinearList<BTNode<K, V>> q) { // Set antrian jadi parameter
        if (q.isEmpty()) return; // Kalo antrian kosong, keluar dari method
        BTNode<K, V> node = q.remove(); // Ambil node dari antrian
        System.out.print(node.getKey() + ":" + node.getData() + " "); // Print key dan data
        if (node.getLlink() != null) q.pushQ(node.getLlink()); // Jika ada leftlink input ke antrian
        if (node.getRlink() != null) q.pushQ(node.getRlink()); // Jika ada rightlink input ke antrian
        printLevelOrderRec(q); // Panggil method ini dengan cara rekursif
    }

    // Method buat print elemen di list level order
    public void printLevelOrder(BTNode<K, V> node) { // set node jadi parameter
        MyLinearList<BTNode<K, V>> q = new MyLinearList<>(); // Buat antrian baru
        if (node != null) q.pushQ(node); // Kalau node terisi,masukkan ke antrian
        printLevelOrderRec(q); // Panggil metode rekursif buat print level order
    }
}