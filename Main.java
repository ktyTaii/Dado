package com.TDS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main extends JFrame {

    // Componentes da interface
    private JLabel resultadoLabel;         // Exibe o número atual gerado
    private JTextArea historicoArea;       // Mostra o número anterior
    private JButton rolarButton;           // Botão para gerar número
    private JTextField ladosInput;         // Campo para definir o número de lados
    private Random random;                 // Gerador de números aleatórios
    private Integer numeroAnterior = null; // Armazena o número anterior à jogada atual

    // Construtor da interface
    public Main() {
        super("Gerador de Número");

        // Inicializa o gerador aleatório
        random = new Random();

        // Rótulo de resultado (número atual)
        resultadoLabel = new JLabel("Escolha os lados e gere um número", SwingConstants.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Área de histórico com mensagem inicial
        historicoArea = new JTextArea(3, 20);
        historicoArea.setEditable(false);
        historicoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historicoArea.setText("Número anterior:\nSem número anterior");

        // Campo de entrada para número de lados (valor padrão: 6)
        ladosInput = new JTextField("6", 5);
        ladosInput.setFont(new Font("Arial", Font.PLAIN, 16));

        // Botão para gerar número
        rolarButton = new JButton("Gerar Número");
        rolarButton.setFont(new Font("Arial", Font.PLAIN, 16));

        // Ação do botão ao ser clicado
        rolarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lê o número de lados informado
                    int lados = Integer.parseInt(ladosInput.getText());

                    // Validação: mínimo de 2 lados
                    if (lados < 2) {
                        resultadoLabel.setText("Mínimo permitido: 2 lados");
                        historicoArea.setText("Número anterior:\nSem número anterior");
                        numeroAnterior = null;
                        return;
                    }

                    // Gera número aleatório entre 1 e o número de lados
                    int numeroAtual = random.nextInt(lados) + 1;

                    // Atualiza o histórico com o número anterior
                    atualizarHistorico();

                    // Exibe o número atual
                    resultadoLabel.setText("Você gerou: " + numeroAtual);

                    // Atualiza o número anterior para a próxima rodada
                    numeroAnterior = numeroAtual;

                } catch (NumberFormatException ex) {
                    // Caso o valor digitado não seja um número válido
                    resultadoLabel.setText("Digite um número válido");
                    historicoArea.setText("Número anterior:\nSem número anterior");
                    numeroAnterior = null;
                }
            }
        });

        // Painel superior com entrada e botão
        JPanel painelSuperior = new JPanel(new FlowLayout());
        painelSuperior.add(new JLabel("Lados do número:"));
        painelSuperior.add(ladosInput);
        painelSuperior.add(rolarButton);

        // Painel principal com layout organizado
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.add(resultadoLabel, BorderLayout.NORTH);
        painelPrincipal.add(painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add(new JScrollPane(historicoArea), BorderLayout.SOUTH);

        // Configurações da janela
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza na tela
        setVisible(true);
    }

    // Método para atualizar o histórico com o número anterior
    private void atualizarHistorico() {
        if (numeroAnterior != null) {
            historicoArea.setText("Número anterior:\n" + numeroAnterior);
        } else {
            historicoArea.setText("Número anterior:\nSem número anterior");
        }
    }

    // Método principal para iniciar o programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
