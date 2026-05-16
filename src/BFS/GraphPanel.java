package BFS;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel { //Classe responsável por desenhar o grafo na interface gráfica.

    private static final long serialVersionUID = 1L;
    
	private Graph graph;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //Utiliza o método paintComponent para renderizar as arestas, nós e o nome de cada nó

        // arestas
        for (Node node : graph.getNodes()) {
            for (Node vizinho : graph.getNeighbors(node)) {

                g.setColor(Color.BLACK);
                g.drawLine(
                    node.x + 20, node.y + 20,
                    vizinho.x + 20, vizinho.y + 20
                );
            }
        }

        // nós
        for (Node node : graph.getNodes()) {

            if (node.visited) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }

            g.fillOval(node.x, node.y, 40, 40);

            g.setColor(Color.WHITE);
            g.drawString(node.name, node.x + 15, node.y + 25);
        }
        
        //Também utiliza cores para indicar: Nós não visitados e Nós visitados durante a execução dos algoritmos

    }
}

//Essa classe faz a ponte
