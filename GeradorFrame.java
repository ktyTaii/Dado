package com.TDS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GeradorFrame extends JFrame {

    private JLabel resultadoLabel;
    private JLabel parabensLabel;
    private JTextArea historicoArea;
    private JButton rolarButton;
    private JTextField ladosInput;
    private Random random;
    private Integer numeroAnterior = null;
    private ConfetePanel confetePanel;

    public GeradorFrame() {
        super("Gerador de Número");

        random = new Random();

        resultadoLabel = new JLabel("Escolha os lados e gere um número", SwingConstants.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 20));

        parabensLabel = new JLabel("Parabéns!", SwingConstants.CENTER);
        parabensLabel.setFont(new Font("Arial", Font.BOLD, 28));
        parabensLabel.setVisible(false);

        historicoArea = new JTextArea(3, 20);
        historicoArea.setEditable(false);
        historicoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historicoArea.setText("Número anterior:\nSem número anterior");

        ladosInput = new JTextField("6", 5);
        ladosInput.setFont(new Font("Arial", Font.PLAIN, 16));

        rolarButton = new JButton("Girar o dado");
        rolarButton.setFont(new Font("Arial", Font.PLAIN, 16));

        rolarButton.addActionListener(e -> gerarNumero());

        JPanel painelSuperior = new JPanel(new FlowLayout());
        painelSuperior.add(new JLabel("Lados:"));
        painelSuperior.add(ladosInput);
        painelSuperior.add(rolarButton);

        JPanel painelCentro = new JPanel(new BorderLayout());
        painelCentro.add(painelSuperior, BorderLayout.NORTH);
        painelCentro.add(parabensLabel, BorderLayout.CENTER);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.add(resultadoLabel, BorderLayout.NORTH);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        painelPrincipal.add(new JScrollPane(historicoArea), BorderLayout.SOUTH);

        confetePanel = new ConfetePanel();
        confetePanel.setOpaque(false);
        setGlassPane(confetePanel);
        confetePanel.setVisible(true);

        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void gerarNumero() {
        try {
            int lados = Integer.parseInt(ladosInput.getText());

            if (lados < 2) {
                resultadoLabel.setText("Deve ter no mínimo 2 lados");
                historicoArea.setText("Número anterior:\nSem número anterior");
                numeroAnterior = null;
                return;
            }

            int numeroAtual = random.nextInt(lados) + 1;

            atualizarHistorico();
            resultadoLabel.setText("Seu número é: " + numeroAtual);

            if (numeroAtual == lados) {
                iniciarArcoIris();
                mostrarParabensArcoIris();
                confetePanel.dispararConfetes();
            }

            numeroAnterior = numeroAtual;

        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Digite um número válido");
            historicoArea.setText("Número anterior:\nSem número anterior");
            numeroAnterior = null;
        }
    }

    private void atualizarHistorico() {
        if (numeroAnterior != null) {
            historicoArea.setText("Número anterior:\n" + numeroAnterior);
        } else {
            historicoArea.setText("Número anterior:\nSem número anterior");
        }
    }

    private void iniciarArcoIris() {
        final Color[] cores = {
                Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA
        };
        final int[] contador = {0};

        javax.swing.Timer timer = new javax.swing.Timer(150, e -> {
            resultadoLabel.setForeground(cores[contador[0] % cores.length]);
            contador[0]++;
            if (contador[0] >= 20) {
                ((javax.swing.Timer) e.getSource()).stop();
                resultadoLabel.setForeground(Color.BLACK);
            }
        });
        timer.start();
    }

    private void mostrarParabensArcoIris() {
        parabensLabel.setVisible(true);
        final Color[] cores = {
                Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA
        };
        final int[] contador = {0};

        javax.swing.Timer timer = new javax.swing.Timer(150, e -> {
            parabensLabel.setForeground(cores[contador[0] % cores.length]);
            contador[0]++;
            if (contador[0] >= 20) {
                ((javax.swing.Timer) e.getSource()).stop();
                parabensLabel.setVisible(false);
            }
        });
        timer.start();
    }
}
