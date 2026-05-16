package BFS;

import java.util.*;

public class Graph { //Classe responsável por representar a estrutura do grafo.

    private Map<Node, List<Node>> adjList = new HashMap<>();

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }
    
    // O grafo é implementado utilizando uma lista de adjacência, onde cada nó está associado a uma lista de seus vizinhos (nó-pai ou nó-filho).
    
    public void addEdge(Node a, Node b) {
        adjList.get(a).add(b);
        adjList.get(b).add(a);
    }
    
    public List<Node> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Node getNodeByName(String name) {
        for (Node node : adjList.keySet()) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }
    
    /*Principais responsabilidades:
     * - Nós no grafo
     * - Arestas entre nós
     * - Retornar os vizinhos de um nó
     * - Buscar um nó pelo seu nome
     */ 

    public Set<Node> getNodes() {
        return adjList.keySet();
    }
}

 //Essa abordagem é eficiente para grafos e facilita a implementação dos algoritmos de busca.