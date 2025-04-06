package com.datastruct;

public class Heap <K extends Comparable<? super K>,V>{
    private MyArrayList<BTNode<K,V>>arrList;
    private Boolean priority;

    public Heap(int capacity, boolean priority){
        arrList = new MyArrayList<BTNode<K,V>>(capacity);
        this.priority = priority;
    }
    public int size(){
        return arrList.size();
    }
    
    public V getData(int index){
        return arrList.get(index).getData();
    }
    
    public V getData(BTNode<K,V>node){
        return node.getData();
    }

    public K getKey(int index){
        return arrList.get(index).getKey();
    }

    public K getKey(BTNode<K,V>node){
        return node.getKey();
    }

    public void add(K key, V data){
        arrList.add(new BTNode<K,V>(key,data));
    }

    public void insert(K key, V data){
        arrList.add(new BTNode<K,V>(key,data));
        int size = arrList.size();
        for(int i = size / 2 - 1; i>=0; i =(i+1)/2-1){
            if(priority) heapifyMin(size, i);
            else heapifyMax(size, i);
        }
    }

    public void buildHeap(){
        int size = arrList.size();
        for(int i = size / 2 - 1; i>=0;i--){
            if(priority)heapifyMin(size,i);
            else heapifyMax(size,i);
        }
    }
    void heapifyMax(int size,int i)
    {
        int parent = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if(left < size && arrList.get(left).getKey().compareTo(arrList.get(parent).getKey())>0)
                parent = left;
        
        if(right < size && arrList.get(right).getKey().compareTo(arrList.get(parent).getKey())>0)
                parent = right;
        
        if(parent !=i)
        {
            BTNode<K,V> temp = arrList.get(i);
            arrList.set(i, arrList.get(parent));
            arrList.set(parent,temp);

            heapifyMax(size,parent);
        }
    }
    public void sort(){
        int size = arrList.size();
        buildHeap();
        for(int i = size - 1;i >= 0; i--)
        {

        }
    }
    
    private void heapifyMin(int index, int size) {
        int smallest = index; // Inisialisasi smallest sebagai index
        int leftChild = 2 * index + 1; // Indeks anak kiri
        int rightChild = 2 * index + 2; // Indeks anak kanan
    
        // Cek apakah anak kiri lebih kecil dari node saat ini
        if (leftChild < size && arrList.get(leftChild).getKey().compareTo(arrList.get(smallest).getKey()) < 0) {
            smallest = leftChild;
        }
    
        // Cek apakah anak kanan lebih kecil dari node saat ini
        if (rightChild < size && arrList.get(rightChild).getKey().compareTo(arrList.get(smallest).getKey()) < 0) {
            smallest = rightChild;
        }
    
        // Jika smallest bukan index awal, tukar dan panggil heapifyMin secara rekursif
        if (smallest != index) {
            BTNode<K, V> temp = arrList.get(index);
            arrList.set(index, arrList.get(smallest));
            arrList.set(smallest, temp);
            heapifyMin(smallest, size);
        }
    }
    public BTNode<K, V> first() {
        if (arrList.isEmpty()) {
            return null; // Mengembalikan null jika heap kosong
        }
        return arrList.get(0); // Mengembalikan elemen pertama (root heap)
    }
    
    public BTNode<K, V> removeFirst() {
        if (arrList.isEmpty()) {
            return null; // Mengembalikan null jika heap kosong
        }
        BTNode<K, V> root = arrList.get(0); // Mendapatkan elemen pertama (root heap)
        // BTNode<K, V> lastNode = arrList.remove(arrList.size() - 1); // Menghapus elemen terakhir
        // if (arrList.size() > 0) {
        //     arrList.set(0, lastNode); // Mengganti root dengan elemen terakhir
        //     heapifyMin(0); // Memulihkan sifat heap
        // }
        BTNode<K, V> lastNode = arrList.get(arrList.size() - 1); // Ambil elemen terakhir
    arrList.remove(arrList.size() - 1); // Hapus elemen terakhir

    if (arrList.size() > 0) {
        arrList.set(0, lastNode); // Ganti root dengan elemen terakhir
        if (priority) {
            heapifyMin(0, arrList.size()); // <- PERBAIKAN DI SINI
        } else {
            heapifyMax(arrList.size(), 0);
        }
    }
        return root; // Mengembalikan elemen pertama yang dihapus
    }

    public void display(){
        arrList.cetakList();
    }
}