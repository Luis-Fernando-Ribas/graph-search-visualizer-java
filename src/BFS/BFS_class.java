package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_class { //Implementação do algoritmo de Busca em Largura (Breadth-First Search).

    private Graph graph;

    public BFS_class(Graph graph) {
        this.graph = graph;
    }

    public List<Node> executar(Node start) { 
        List<Node> visitados = new ArrayList<>();
        Queue<Node> fila = new LinkedList<>();

        fila.add(start);
        visitados.add(start);

        while (!fila.isEmpty()) {
            Node atual = fila.poll();
            	
            for (Node vizinho : graph.getNeighbors(atual)) {

                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    fila.add(vizinho);
                }
            }
        }

        return visitados;
    }
}

//Retorna uma lista com a ordem de visita dos nós.