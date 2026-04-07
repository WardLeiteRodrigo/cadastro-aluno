package ui;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;

/**
 * Implementacao de {@link IIO} usando dialogos graficos do Swing
 * (JOptionPane). Mensagens longas sao exibidas em JTextArea com
 * scroll para preservar legibilidade.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class IOGrafico implements IIO {

    /**
     * Le um texto nao vazio via JOptionPane.
     *
     * @param msg Mensagem a ser exibida.
     * @return Texto digitado ou null se cancelado.
     */
    public String lerTexto(String msg) {
        String input;
        do {
            input = JOptionPane.showInputDialog(msg);
            if (input == null) return null;
            if (input.trim().isEmpty()) {
                mostrar("Entrada invalida. O valor nao pode ser vazio.");
            }
        } while (input.trim().isEmpty());
        return input.trim();
    }

    /**
     * Le um inteiro validando intervalo. Mostra mensagem de erro
     * amigavel para entradas nao numericas (sem stack trace).
     *
     * @param msg Mensagem a ser exibida.
     * @param min Valor minimo aceito.
     * @param max Valor maximo aceito.
     * @return Inteiro lido ou null se cancelado.
     */
    public Integer lerInteiro(String msg, int min, int max) {
        while (true) {
            String input = JOptionPane.showInputDialog(msg);
            if (input == null) return null;
            try {
                int valor = Integer.parseInt(input.trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                mostrar("Valor fora do intervalo permitido (" + min + " a " + max + ").");
            } catch (NumberFormatException e) {
                mostrar("Entrada invalida. Por favor, digite um numero inteiro.");
            }
        }
    }

    /**
     * Pergunta sim/nao ao usuario.
     *
     * @param msg Mensagem a ser exibida.
     * @return true se YES, false caso contrario.
     */
    public boolean lerConfirmacao(String msg) {
        int resp = JOptionPane.showConfirmDialog(null, msg, "Confirmacao", JOptionPane.YES_NO_OPTION);
        return resp == JOptionPane.YES_OPTION;
    }

    /**
     * Exibe uma mensagem em janela. Para textos longos usa um
     * JTextArea com scroll.
     *
     * @param msg Mensagem a ser exibida.
     */
    public void mostrar(String msg) {
        JTextArea textArea = new JTextArea(msg);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(420, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Nada a fechar no modo grafico.
     */
    public void fechar() {
        // sem recursos a liberar
    }
}
