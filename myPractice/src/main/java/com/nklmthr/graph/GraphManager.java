package com.nklmthr.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphManager {

	public static void main(String[] args) {
		Graph g = new Graph();
		g.insert("a", "b");
		g.insert("b", "c");
		//g.insert("a", "d");
		g.insert("c", "d");
		g.traverse();
		System.out.println("edge a->d " + g.hasEdge("a", "d"));
		System.out.println("edge b->c " + g.hasEdge("b", "c"));
	}

}

class Graph {
	Map<Node, LinkedList<Node>> adjList = new HashMap<Node, LinkedList<Node>>();

	public Node getSourceNode(String source) {
		for (Node node : adjList.keySet()) {
			if (node.getValue().equals(source)) {
				return node;
			}

		}
		return new Node(source);
	}

	public Node getTargetNode(String source, String target) {
		for (Node node : adjList.keySet()) {
			if (node.getValue().equals(source)) {
				LinkedList<Node> list = adjList.get(node);
				for (Node listNode : list) {
					if (listNode.getValue().equals(target)) {
						return listNode;
					}
				}
			}
		}
		return new Node(target);

	}

	public void insert(String source, String target) {
		Node sourceNode = getSourceNode(source), targetNode = getTargetNode(source, target);
		if (adjList.containsKey(sourceNode)) {
			LinkedList<Node> list = adjList.get(sourceNode);
			list.addLast(targetNode);
		} else {
			adjList.put(sourceNode, new LinkedList<Node>());
			adjList.get(sourceNode).addLast(targetNode);
		}

	}

	public boolean hasEdge(String source, String target) {
		Node sourceNode = getSourceNode(source), targetNode = getTargetNode(source, target);
		if (adjList.containsKey(sourceNode)) {
			LinkedList<Node> list = adjList.get(sourceNode);
			for (Node listNode : list) {
				if (listNode.getValue().equals(target)) {
					return true;
				}
			}
		}
		return false;
	}

	public void traverse() {
		for (Node node : adjList.keySet()) {
			System.out.print("[[" + node.getValue() + "]]");
			LinkedList<Node> list = adjList.get(node);
			for (Node listNode : list) {
				System.out.print(" - [" + listNode.getValue() + "]");
			}
			System.out.println();
		}
	}

	public void bfsTraverse(String node) {

	}

}

class Node {
	String value;
	Node next;

	public Node() {
		super();
	}

	public Node(String value) {
		super();
		this.value = value;
	}

	public Node(String value, Node node) {
		super();
		this.value = value;
		this.next = node;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node node) {
		this.next = node;
	}

}