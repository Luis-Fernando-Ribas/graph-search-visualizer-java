package GUI_BFS;

import java.util.*;

public class BFS { //Implementação do algoritmo de Busca em Profundidade (Depth-First Search).

    private Graph graph;

    public BFS(Graph graph) {
        this.graph = graph;
    }

    public List<Node> executar(Node start) { //A DFS percorre o grafo indo o mais profundo possível em um caminho antes de voltar (backtracking).

        List<Node> visitados = new ArrayList<>();
        Queue<Node> fila = new LinkedList<>(); //Características: Utiliza recursão (ou pilha), explora caminhos completos antes de mudar de direção e pode não encontrar o menor caminho

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