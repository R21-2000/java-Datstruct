import com.datastruct.*;

public class HuffmanCoding {
    public static void main(String[] args){
        char[] charArray = {'A', 'I', 'M', 'O', 'T', 'F', 'K', 'N', 'R'};
        int[] charferq = {45, 35, 29, 19, 4, 8, 15, 10, 5};

        // buat priority queue dengan heap min
        Heap<Integer, HuffmanNode> pq = new Heap<>(charArray.length, true);
        //inputkan setiap huruf dan frekuensinya ke pq
        for(int i=0; i< charferq.length ; i++){
            HuffmanNode node = new HuffmanNode(charferq[i], charArray[i], null, null);
            pq.add(charferq[i], node);
        }
        //membuat heap minimum dari pq
        pq.buildHeap();

        HuffmanNode root = null;
        HuffmanNode x,y;
        int sum;
        while(pq.size() > 1){
            sum = pq.getKey(pq.first());
            x = pq.getData(pq.first());
            pq.removeFirst();

            sum += pq.getKey(pq.first());
            y = pq.getData(pq.first());
            pq.removeFirst();

            root = new HuffmanNode(sum, '-', x,y);
            pq.insert(sum, root);
        }

        MyArrayList<String> codes;
        codes = root.getHuffmanCodes(root, charArray.length);
        System.out.println("---------------------");
        System.out.println(" Huruf | Huffman code ");
        System.out.println("---------------------");
        for (int i = 0; i < charferq.length; i++) {
            String[] hasil = codes.get(i).split(" ");
            System.out.println("   " + hasil[0] + "   |   " + hasil[1]);
        }
        
        System.out.println("---------------------");
    }
}
