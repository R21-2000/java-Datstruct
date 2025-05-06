package com.datastruct;

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
	private Map<T, MyLinearList<Edge<T>>> adj;
	private boolean directed;
	
	public Graph (boolean type) { 
        adj = new HashMap<>();
		directed = type; 
	}

	public void addEdge(T a, T b, int w) {
		adj.putIfAbsent(a, new MyLinearList<>()); //tambah node
		adj.putIfAbsent(b, new MyLinearList<>()); 
		adj.get(a).pushQ(new Edge<>(b, w)); //tambah edge
		if (!directed) { 
			adj.get(b).pushQ(new Edge<>(b, w));
		}			
	}

    //Print graph as hashmap, Time O(V+E), Space O(1)
	public void printGraph() {
		for (T key: adj.keySet()) {
            System.out.print(key + " : ");
			MyLinearList<Edge<T>> edges = adj.get(key);
			Node<Edge<T>> curr = edges.head;
			while(curr != null) {
				System.out.print(curr.getData());
				curr = curr.getNext();
			}
			System.out.println();
		}
	}

	public void deleteEdge(T a, T b) {
		// Hapus edge dari a ke b
		 // Check if both nodes exist in the graph
		 if (!adj.containsKey(a) || !adj.containsKey(b)) {
			return;
		}
	
		// Remove edge from a to b
		MyLinearList<Edge<T>> edgesA = adj.get(a);
		Node<Edge<T>> currA = edgesA.head;
		Node<Edge<T>> prevA = null;
		
		while (currA != null) {
			if (currA.getData().getNeighbor().equals(b)) {
				if (prevA == null) {
					edgesA.head = currA.getNext();
					if (edgesA.head == null) edgesA.tail = null;
				} else {
					prevA.setNext(currA.getNext());
					if (currA.getNext() == null) edgesA.tail = prevA;
				}
				break;
			}
			prevA = currA;
			currA = currA.getNext();
		}
	
		// If undirected, also remove edge from b to a
		if (!directed) {
			MyLinearList<Edge<T>> edgesB = adj.get(b);
			Node<Edge<T>> currB = edgesB.head;
			Node<Edge<T>> prevB = null;
			
			while (currB != null) {
				if (currB.getData().getNeighbor().equals(a)) {
					if (prevB == null) {
						edgesB.head = currB.getNext();
						if (edgesB.head == null) edgesB.tail = null;
					} else {
						prevB.setNext(currB.getNext());
						if (currB.getNext() == null) edgesB.tail = prevB;
					}
					break;
				}
				prevB = currB;
				currB = currB.getNext();
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
						List<T> neighborList = new ArrayList<>();
						Node<Edge<T>> currNode = neighbors.head;
						while (currNode != null) {
							neighborList.add(currNode.getData().getNeighbor());
							currNode = currNode.getNext();
						}
						Collections.reverse(neighborList);
						for (T neighbor : neighborList) {
							if (!visited.contains(neighbor)) {
								stack.push(neighbor);
							}
						}
					}
				}
			System.out.println();
		}		
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
