package DFS;

import java.util.*;

public class DFS {

    private Graph graph;

    public DFS(Graph graph) {
        this.graph = graph;
    }

    public List<Node> executar(Node start) {
        List<Node> visitados = new ArrayList<>();
        dfsRecursivo(start, visitados);
        return visitados;
    }

    private void dfsRecursivo(Node atual, List<Node> visitados) {

        visitados.add(atual);

        for (Node vizinho : graph.getNeighbors(atual)) {
            if (!visitados.contains(vizinho)) {
                dfsRecursivo(vizinho, visitados);
            }
        }
    }
}

 //Retorna uma lista com a ordem de visita dos nós.