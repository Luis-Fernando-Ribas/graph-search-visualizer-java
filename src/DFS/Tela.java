package DFS;

import javax.swing.*;
import java.awt.*;

public class Tela extends JFrame { //Tela da aplicação

    private static final long serialVersionUID = 1L;

	public Tela() {
        setUndecorated(true); // remove barra padrão
        setSize(900, 800);
        setLayout(new BorderLayout());

        JPanel barra = new JPanel();
        barra.setBackground(new Color(58, 123, 213)); // cor da barra
        barra.setPreferredSize(new Dimension(500, 40));

        JLabel titulo = new JLabel("Busca em Grafos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        barra.add(titulo);

        add(barra, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Tela();
    }
}
