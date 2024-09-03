import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ContadorBasico {
    public static void main(String[] args) {
        JFrame meuJFrame = new JFrame("Contador de números");
        meuJFrame.setSize(1000, 600);

        JPanel meuJPanel = new JPanel();
        meuJPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 10, 5); // Margens
        meuJPanel.add(new JLabel("Digite o primeiro número: "), gbc);

        JTextArea numero1 = new JTextArea(1, 2);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // O botão ocupará duas colunas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Expande horizontalmente
        meuJPanel.add(numero1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 5); // Margens
        meuJPanel.add(new JLabel("Digite o segundo número: "), gbc);

        JTextArea numero2 = new JTextArea(1, 2);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // O botão ocupará duas colunas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Expande horizontalmente
        meuJPanel.add(numero2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 5); // Margens
        meuJPanel.add(new JLabel("O que você deseja: "), gbc);

        String[] opcoes = {"Contar Números", "Números Primos", "Números Pares", "Números Ímpares"};
        JComboBox<String> comboBox = new JComboBox<>(opcoes);
        gbc.gridx = 1;
        gbc.gridy = 2;
        meuJPanel.add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 2, 5); // Margens
        meuJPanel.add(new JLabel("Resultado: "), gbc);

        JTextArea localResposta = new JTextArea(5, 20);
        localResposta.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Expande em ambas as direções
        meuJPanel.add(new JScrollPane(localResposta), gbc);

        JButton botaoCapturar = new JButton("Confirmar");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        meuJPanel.add(botaoCapturar, gbc);

        JButton botaoSair = new JButton("Sair");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        meuJPanel.add(botaoSair, gbc);

        botaoCapturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto1 = numero1.getText(); // Captura o texto do JTextArea
                String texto2 = numero2.getText(); // Captura o texto do JTextArea
                String itemSelecionado = (String) comboBox.getSelectedItem();
                StringBuilder resultado = new StringBuilder(); // Usado para armazenar o resultado

                try {
                    int numeroUm = Integer.parseInt(texto1); // Converte para inteiro
                    int numeroDois = Integer.parseInt(texto2); // Converte para inteiro

                    if (numeroDois < numeroUm || numeroUm == numeroDois) {
                        throw new ParametrosInvalidosException();
                    }

                    int diferencaNumeros = numeroDois - numeroUm;
                    int numerosPrimos = numeroUm;

                    if (itemSelecionado.equals("Contar Números")){
                        resultado.append("Contagem dos números:\n");
                        for (int i = 1; i < diferencaNumeros;){
                            resultado.append(i).append("\n");
                            i++;
                        }
                    }
                    else if (itemSelecionado.equals("Números Primos")){
                        resultado.append("Os números primos entre ").append(numeroUm).append(" e ").append(numeroDois).append(" são:\n");
                        for (int i = 1; i < diferencaNumeros;){
                            numerosPrimos++;
                            i++;
                            if ((numerosPrimos % 2 != 0) && (numerosPrimos % 3 != 0)){
                                resultado.append(numerosPrimos).append("\n");
                            }
                        }
                    }
                    else if (itemSelecionado.equals("Números Pares")){
                        resultado.append("Os números pares entre ").append(numeroUm).append(" e ").append(numeroDois).append(" são:\n");
                        for (int i = 1; i < diferencaNumeros;){
                            numerosPrimos++;
                            i++;
                            if ((numerosPrimos % 2 == 0)){
                                resultado.append(numerosPrimos).append("\n");
                            }
                        }
                    } else if (itemSelecionado.equals("Números Ímpares")) {
                        resultado.append("Os números ímpares entre ").append(numeroUm).append(" e ").append(numeroDois).append(" são:\n");
                        for (int i = 1; i < diferencaNumeros;){
                            numerosPrimos++;
                            i++;
                            if ((numerosPrimos % 2 != 0)){
                                resultado.append(numerosPrimos).append("\n");
                            }
                        }
                    }
                    localResposta.setFont(new Font("Serif", Font.BOLD, 18));
                    localResposta.setText(resultado.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(meuJFrame, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (ParametrosInvalidosException ex) {
                    JOptionPane.showMessageDialog(meuJFrame, "O segundo parâmetro deve ser maior que o primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Encerra a aplicação
            }
        });

        meuJFrame.add(meuJPanel);
        meuJFrame.setVisible(true);
    }
}