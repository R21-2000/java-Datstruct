package com.datastruct;
/* 
 * Struktur data Graph dengan bobot pada setiap edge
 * sources: https://www.lavivienpost.net/weighted-graph-as-adjacency-list/  
 * 
 */

import java.util.*;

class Edge<T> { 
	private T neighbor; //connected vertex
	private int weight; //isi dari edge
	
	public Edge(T v, int w) {
		this.neighbor = v; 
		this.weight = w;
	}

	public void setNeighbor(T neighbor) {
		this.neighbor = neighbor;
	}
	public T getNeighbor() {
		return neighbor;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getWeight() {
		return weight;
	}
	
	//Time O(1) Space O(1)
	@Override
	public String toString() {
		return "(" + neighbor + "," + weight + ")";
	}
}

public class Graph<T> { 
    //Map<T, LinkedList<Edge<T>>> adj;
	private Map<T, MyLinearList<Edge<T>>> adj;
	boolean directed;
	
	//Constructor, Time O(1) Space O(1)
	public Graph (boolean type) { 
        adj = new HashMap<>();
		directed = type; // false: undirected, true: directed
	}

    //Nambah edges sama nambah nodes, Time O(1) Space O(1)
	public void addEdge(T a, T b, int w) {
		adj.putIfAbsent(a, new MyLinearList<>()); //add node
		adj.putIfAbsent(b, new MyLinearList<>()); //add node
		Edge<T> edge1 = new Edge<>(b, w);
		adj.get(a).pushQ(edge1);//add(edge1); //add edge
		if (!directed) { //undirected
			Edge<T> edge2 = new Edge<>(a, w);
			adj.get(b).pushQ(edge2);
		}			
	}

    //Print graph as hashmap, Time O(V+E), Space O(1)
	public void printGraph() {
		for (T key: adj.keySet()) {
			//System.out.println(key.toString() + " : " + adj.get(key).toString());
            System.out.print(key.toString() + " : ");
			MyLinearList<Edge<T>> thelist = adj.get(key);
			Node<Edge<T>> curr = thelist.head;
			while(curr != null) {
				System.out.print(curr.getData());
				curr = curr.getNext();
			}
			System.out.println();
		}
	}

	public void deleteEdge(T a, T b) {
		// Hapus edge dari a ke b
		if (adj.containsKey(a)) {
			MyLinearList<Edge<T>> edges = adj.get(a);
			Node<Edge<T>> curr = edges.head;
			Node<Edge<T>> prev = null;
	
			while (curr != null) {
				if (curr.getData().getNeighbor().equals(b)) {
					if (prev == null) {
						// Hapus head
						edges.head = curr.getNext();
						if (edges.head == null) edges.tail = null;
					} else {
						prev.setNext(curr.getNext());
						if (curr.getNext() == null) edges.tail = prev;
					}
					break;
				}
				prev = curr;
				curr = curr.getNext();
			}
		}
	
		// Jika graph tidak directed, hapus edge dari b ke a juga
		if (!directed) {
			if (adj.containsKey(b)) {
				MyLinearList<Edge<T>> edges = adj.get(b);
				Node<Edge<T>> curr = edges.head;
				Node<Edge<T>> prev = null;
	
				while (curr != null) {
					if (curr.getData().getNeighbor().equals(a)) {
						if (prev == null) {
							edges.head = curr.getNext();
							if (edges.head == null) edges.tail = null;
						} else {
							prev.setNext(curr.getNext());
							if (curr.getNext() == null) edges.tail = prev;
						}
						break;
					}
					prev = curr;
					curr = curr.getNext();
				}
			}
		}
	}
	
	//DFS 
	public void DFS(T src) {
			Set<T> visited = new HashSet<>();
			Stack<T> stack = new Stack<>();
			stack.push(src);
		
			System.out.print("DFS traversal: ");
		
			while (!stack.isEmpty()) {
				T current = stack.pop();
				if (!visited.contains(current)) {
					System.out.print(current + " ");
					visited.add(current);
		
					MyLinearList<Edge<T>> neighbors = adj.get(current);
					if (neighbors != null) {
						// Neihbors ini kita push ke stack
						// biar bisa di pop lagi
						// terus di print
						List<T> neighborList = new ArrayList<>();
						Node<Edge<T>> currNode = neighbors.head;
						while (currNode != null) {
							neighborList.add(currNode.getData().getNeighbor());
							currNode = currNode.getNext();
						}
						//nah kalo ini kita reverse
						// biar urutannya sesuai sama yang kita mau
						// soalnya stack itu LIFO
						// jadi kalo kita push ke stack
						// terus kita pop, yang terakhir di push
						Collections.reverse(neighborList);
						for (T neighbor : neighborList) {
							if (!visited.contains(neighbor)) {
								stack.push(neighbor);
							}
						}
					}
				}
			}
			System.out.println();		
	}

	//BFS
	public void BFS(T src) { 
			Set<T> visited = new HashSet<>();
			Queue<T> queue = new LinkedList<>();
			queue.offer(src);
			visited.add(src);
		
			System.out.print("BFS traversal: ");
		
			while (!queue.isEmpty()) {
				T current = queue.poll();
				System.out.print(current + " ");
				//kalo fungsi ini itu buat nambahin node ke queue
				MyLinearList<Edge<T>> neighbors = adj.get(current);
				if (neighbors != null) {
					Node<Edge<T>> currNode = neighbors.head;
					while (currNode != null) {
						T neighbor = currNode.getData().getNeighbor();
						if (!visited.contains(neighbor)) {
							visited.add(neighbor);
							queue.offer(neighbor);
						}
						currNode = currNode.getNext();
					}
				}
			System.out.println();
		}
		
	}
}
