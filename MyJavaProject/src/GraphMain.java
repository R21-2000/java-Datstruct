import com.datastruct.Graph;

public class GraphMain {
    public static void main(String[] args) {
        Graph<String> g = new Graph<>(false);

        g.addEdge("V1", "V3", 10);
        g.addEdge("V1", "V4", 14);
        g.addEdge("V1", "V2", 22);
        g.addEdge("V2", "V4", 15);
        g.addEdge("V2", "V5", 5);
        g.addEdge("V3", "V6", 12);
        g.addEdge("V3", "V7", 18);
        g.addEdge("V4", "V5", 8);
        g.addEdge("V4", "V7", 7);
        g.addEdge("V5", "V8", 6);
        g.addEdge("V6", "V7", 15);
        g.addEdge("V7", "V8", 17);

        System.out.println("Undirected Graph:");
        g.printGraph();

        g.primMST("V1");
        System.out.println();
        g.kruskalMST();
    }
}
