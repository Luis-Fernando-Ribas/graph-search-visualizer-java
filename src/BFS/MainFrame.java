package BFS;

//importações
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;


public class MainFrame extends JFrame { //Classe principal da interface gráfica.

	//criando atributos
	
    JTextField inputNode; // Capturar a entrada do usuário (nó inicial)
    JTextArea output; //Capturar a saída do caminho dos nós
 	private Graph graph; //grafo
 	private GraphPanel graphPanel; // desenho do grafo
 	private Timer timer; // tempo da visita do nó
    
    private static final long serialVersionUID = 1L;
    
    private Node n1, n2, n3, n4, n5; //numero de nós

    private void criarGrafo() { //criação do metodo grafo
        graph = new Graph();  //iniciando o grafo

        n1 = new Node("1", 100, 100); //nome e posição dos nós
        n2 = new Node("2", 300, 100);
        n3 = new Node("3", 200, 250);
        n4 = new Node("4", 400, 250);
        n5 = new Node("5", 300, 350);

        graph.addNode(n1); //Estruturando os nós
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);

        graph.addEdge(n1, n2); //relaacionando os nós com seus vizinhos
        graph.addEdge(n1, n3);
        graph.addEdge(n2, n4);
        graph.addEdge(n3, n5);

    }
    
	//Começo da aplicação
	
	public static void main(String[] args) { //Configurando a janela da aplicação
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { //tratamento de erro
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) { //imorimindo erro
					e.printStackTrace();
				}
			}	
		});
	}

	private void resetarGrafo() { //resetar grafo

	    // parar animação
	    if (timer != null && timer.isRunning()) {
	        timer.stop();
	    } //preserva a saída correta no output, mas interrompe a animação

	    // limpar estado dos nós
	    for (Node node : graph.getNodes()) {
	        node.visited = false;
	    }

	    // limpar output
	    output.setText("");

	    // redesenhar
	    graphPanel.repaint();
	}

	//Criação da janela
	
	public MainFrame() {

	    criarGrafo(); //Organizar os componentes (nós, vizinhos, painel do grafo)

	    setTitle("Busca em Grafos - BFS");
	    setSize(800, 700);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    getContentPane().setBackground(new Color(168, 218, 220));

	    //Up
	    JPanel topPanel = new JPanel();
	    topPanel.setBackground(new Color(168, 218, 220));

	    inputNode = new JTextField(5); //entrada de nó inicial
	    JButton bfsButton = new JButton("BFS");
	    JButton resetButton = new JButton("Resetar");

	    topPanel.add(new JLabel("Nó inicial:"));
	    topPanel.add(inputNode);
	    topPanel.add(bfsButton);
	    topPanel.add(resetButton); //Atualizar a interface conforme a execução

	    add(topPanel, BorderLayout.NORTH);

	    // Center
	    graphPanel = new GraphPanel(graph); //onde a animação vai acontecer
	    add(graphPanel, BorderLayout.CENTER);

	    // Down
	    output = new JTextArea(5, 30); //saida
	    output.setFont(new Font("Arial", Font.BOLD, 18));
	    add(new JScrollPane(output), BorderLayout.SOUTH);

	    //Action
	    bfsButton.addActionListener(e -> executarBFS()); //botões
	    resetButton.addActionListener(e -> resetarGrafo());

	    //Style
	    bfsButton.setBackground(new Color(100, 149, 237));
	    bfsButton.setForeground(Color.WHITE);

	    resetButton.setBackground(new Color(220, 53, 69));
	    resetButton.setForeground(Color.WHITE);

	    setVisible(true); //janela visivel
	}
	 
	/*Método responsável por executar o algoritmo de busca.
	 - Etapas:
	 - 1. Captura o nó inicial informado pelo usuário
	 - Valida a entrada
	 - Limpa o estado anterior do grafo
	 - Executa o algoritmo BFS
	 - Exibe o resultado no JTextArea
	 - Anima a visita dos nós utilizando Timer
	 - Esse método conecta a lógica do algoritmo com a interface gráfica.*/
	
	 private void executarBFS() { //metodo o algoritmo
		    String start = inputNode.getText().trim();

		    // Verifica se está vazio
		    if (start.isEmpty()) {
		        output.setText("Digite um nó inicial!");
		        return;
		    }

		    //Busca o Node real no grafo
		    Node startNode = graph.getNodeByName(start);

		    if (startNode == null) { //verificando se é um nó existente
		        output.setText("Nó não existe no grafo!");
		        return;
		    }

		    //Limpa visitas anteriores
		    for (Node node : graph.getNodes()) {
		        node.visited = false;
		    }

		    //Executa BFS
		    BFS_class bfs = new BFS_class(graph);  //uso do algoritmo
		    List<Node> resultado = bfs.executar(startNode);

		    //Mostra na saída (JTextArea)
		    output.setText("Ordem de visita (BFS):\n");

		    for (int i = 0; i < resultado.size(); i++) { //imprimindo em escrita o caminho
		        output.append(resultado.get(i).name);

		        if (i < resultado.size() - 1) {
		            output.append(" -> ");  //espaçamento e sentido dos valores
		        }
		    }

		    output.append("\n\nTotal de nós visitados: " + resultado.size()); //garantindo que todos os nós foram visitados
		    
		    for (Node node : graph.getNodes()) { //verificando visita
		        node.visited = false;
		        node.visitedColor = new Color(231, 76, 60); // vermelho
		    }
		    
		    // Animação com Timer

		    int delay = 500; //Controlar a animação da busca com Timer
		    final int[] index = {0};

		    timer = new Timer(delay, e -> { //tempo da troca de cores um por um
		        if (index[0] < resultado.size()) {
		            Node node = resultado.get(index[0]);

		            node.visited = true;
		            graphPanel.repaint();

		            index[0]++;
		        } else {
		            timer.stop();
		        }
		    });

		    timer.start();
		   graphPanel.repaint();
	}
	 //Essa classe integra toda a aplicação.
}
