package com.nklmthr.practice.phase4;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

	int numVertices = 0;
	LinkedList<Integer> adjacencyList[];

	Graph(int numVertices) {
		this.numVertices = numVertices;
		adjacencyList = new LinkedList[numVertices];
		for (int i = 0; i < numVertices; i++) {
			adjacencyList[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int src, int dest) {
		adjacencyList[src].add(dest);
		//adjacencyList[dest].add(src);

	}

	void printGraph() {
		for (int v = 0; v < numVertices; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			for (Integer pCrawl : adjacencyList[v]) {
				System.out.print(" -> " + pCrawl);
			}
			System.out.println("\n");
		}
	}

	public void bfs(int start) {
		boolean[] visited = new boolean[numVertices];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visited[start]= true;
		while (!queue.isEmpty()){
			int s = queue.poll();
			System.out.println(s+" ");
			Iterator itr = adjacencyList[s].iterator();
			while(itr.hasNext()){
				int  n  = (int) itr.next();
				if(!visited[n]){
					queue.add(n);
					visited[n]= true;
				}
				
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph(4);
//		graph.addEdge(0, 1);
//		graph.addEdge(0, 4);
//		graph.addEdge(1, 2);
//		graph.addEdge(1, 3);
//		graph.addEdge(1, 4);
//		graph.addEdge(2, 3);
//		graph.addEdge(3, 4);
		
		graph.addEdge(0, 1); 
		graph.addEdge(0, 2); 
		graph.addEdge(1, 2); 
		graph.addEdge(2, 0); 
		graph.addEdge(2, 3); 
		graph.addEdge(3, 3); 

		// print the adjacency list representation of
		// the above graph
		graph.printGraph();
		graph.bfs(2);
	}

}
