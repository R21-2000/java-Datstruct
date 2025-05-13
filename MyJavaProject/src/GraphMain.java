

import com.datastruct.Graph;

class MyVertex{
	String nodeName;
	MyVertex(String name)
	{
		this.nodeName = name;
	}

    @Override
    public String toString() {
        return (nodeName);
    }
}


public class GraphMain {
    public static void main(String[] args) {
        // Buat simpul sesuai gambar (V0 - V6)
        MyVertex v0 = new MyVertex("V0");
        MyVertex v1 = new MyVertex("V1");
        MyVertex v2 = new MyVertex("V2");
        MyVertex v3 = new MyVertex("V3");
        MyVertex v4 = new MyVertex("V4");
        MyVertex v5 = new MyVertex("V5");
        MyVertex v6 = new MyVertex("V6");

        // Graph directed sesuai soal
        Graph<MyVertex> G = new Graph<>(true); // directed = true

        // Tambahkan edge sesuai gambar
        G.addEdge(v2, v0, 1);
        G.addEdge(v2, v3, 1);
        G.addEdge(v0, v1, 1);
        G.addEdge(v0, v3, 1);
        G.addEdge(v1, v4, 1);
        G.addEdge(v3, v1, 1);
        G.addEdge(v3, v4, 1);
        G.addEdge(v3, v5, 1);
        G.addEdge(v4, v6, 1);
        G.addEdge(v5, v6, 1);

        // Cetak graph
        System.out.println("Graph:");
        G.printGraph();

        // Hitung shortest path dari V2 ke V6
        int distance = G.shortestPath(v2, v6);
        System.out.println("Jarak terpendek dari V2 ke V6 adalah: " + distance);
    }
}