

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
        //create vertex
        MyVertex v1 = new MyVertex("p");
        MyVertex v2 = new MyVertex("k");
        MyVertex v3 = new MyVertex("z");
        MyVertex v4 = new MyVertex("u");
        MyVertex v5 = new MyVertex("r");

        Graph<MyVertex> WG = new Graph<MyVertex>(false); //undirected
        WG.addEdge(v4, v2, 2);
        WG.addEdge(v5, v3, 7);
        WG.addEdge(v2, v3, 9);
        WG.addEdge(v1, v4, 8);
        WG.addEdge(v4, v5, 6);

        
        System.out.println("Graph yang undirected:");
        WG.printGraph();

        //ini manggil dan make fungsi DFS
        System.out.println("\nDFS traversal dari vertex p:");
        WG.DFS(v1);

        //kalo ini manggil dan make fungsi BFS
        System.out.println("\nBFS traversal dari vertex p:");
        WG.BFS(v1);

        //Manggil fungsi deleteEdge buat apus edge antar 2 vekter
        System.out.println("\nDelete edges dari v1 ke v2 dan v1 ke v3:");
        WG.deleteEdge(v3, v2);
        WG.deleteEdge(v1, v3);

        //Nampilin Graph abis pake fungsi deleteEdge
        System.out.println("Graph setelah dihapus:");
        WG.printGraph();
        
        //Cek Edge yang udah di deleteEdge pake DFS
        System.out.println("\nDFS traversal dari vertex p setelah delete:");
        WG.DFS(v1);

        //Cek Edge yang udah di deleteEdge pake BFS
        System.out.println("\nBFS traversal dari vertex p setelah delete:");
        WG.BFS(v1);
    }
}