package com.datastruct;

import java.util.*;

public class Graph<T> {
    private final boolean isDirected;
    private final List<Vertex> vertices = new ArrayList<>();

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    private class Vertex {
        T data;
        List<Edge> edges = new ArrayList<>();

        Vertex(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private class Edge {
        Vertex src;
        Vertex dest;
        int weight;

        Edge(Vertex src, Vertex dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + src + "," + dest + "," + weight + ")";
        }
    }

    public void addEdge(T from, T to, int weight) {
        Vertex v1 = getOrCreateVertex(from);
        Vertex v2 = getOrCreateVertex(to);

        v1.edges.add(new Edge(v1, v2, weight));
        if (!isDirected) {
            v2.edges.add(new Edge(v2, v1, weight));
        }
    }

    private Vertex getOrCreateVertex(T data) {
        for (Vertex v : vertices) {
            if (v.data.equals(data)) return v;
        }
        Vertex newVertex = new Vertex(data);
        vertices.add(newVertex);
        return newVertex;
    }

    public void printGraph() {
        for (Vertex v : vertices) {
            System.out.print(v + " : ");
            for (Edge e : v.edges) {
                System.out.print("(" + e.dest + "," + e.weight + ") ");
            }
            System.out.println();
        }
    }

    public List<String> primMST(T startData) {
        List<String> result = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        Vertex start = getOrCreateVertex(startData);
        visited.add(start);
        pq.addAll(start.edges);
        int totalWeight = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited.contains(edge.dest)) continue;

            visited.add(edge.dest);
            result.add(edge.toString());
            totalWeight += edge.weight;
            for (Edge next : edge.dest.edges) {
                if (!visited.contains(next.dest)) {
                    pq.add(next);
                }
            }
        }

        System.out.println("MST dengan Algoritma Prim:");
        System.out.println(result);
        System.out.println("MST Length = " + totalWeight);
        return result;
    }

    public List<String> kruskalMST() {
        List<String> result = new ArrayList<>();
        Map<Vertex, Vertex> parent = new HashMap<>();
        for (Vertex v : vertices) parent.put(v, v);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        Set<String> seen = new HashSet<>(); // untuk undirected graph, hindari duplikat edge
        for (Vertex v : vertices) {
            for (Edge e : v.edges) {
                String edgeKey = e.src + "-" + e.dest;
                String reverseKey = e.dest + "-" + e.src;
                if (!seen.contains(edgeKey) && !seen.contains(reverseKey)) {
                    pq.add(e);
                    seen.add(edgeKey);
                }
            }
        }

        int totalWeight = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            Vertex root1 = find(parent, edge.src);
            Vertex root2 = find(parent, edge.dest);

            if (!root1.equals(root2)) {
                result.add(edge.toString());
                totalWeight += edge.weight;
                parent.put(root1, root2);
            }
        }

        System.out.println("MST dengan Algoritma Kruskal:");
        System.out.println(result);
        System.out.println("MST Length = " + totalWeight);
        return result;
    }

    private Vertex find(Map<Vertex, Vertex> parent, Vertex v) {
        if (parent.get(v) != v)
            parent.put(v, find(parent, parent.get(v)));
        return parent.get(v);
    }
}
