package BFS;

import java.awt.Color;

public class Node { //Classe que representa um nó ou vértice do grafo.
    String name;
    int x, y;
    boolean visited;
    
    Color visitedColor = Color.GREEN;
    
    /*Cada nó possui:
     * - Um nome
     * - Uma posição (x, y) para ser desenhado na interface
     * - Um estado
     * - Uma cor de visita (para visualização gráfica) */
    
    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}