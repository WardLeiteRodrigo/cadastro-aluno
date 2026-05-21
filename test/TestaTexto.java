package test;

import model.Texto;
import javax.swing.JOptionPane;

/**
 * Teste manual da classe {@link Texto}: solicita um texto ao usuario via
 * {@link JOptionPane} e imprime no console o texto original (com a
 * quantidade de palavras) e sua versao invertida.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/03/17
 */
public class TestaTexto {

    /**
     * Classe de teste manual: nao deve ser instanciada.
     */
    private TestaTexto() {
    }

    /**
     * Ponto de entrada do teste manual. Le um texto digitado pelo usuario e
     * exibe no console suas variacoes geradas por {@link Texto}.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String[] args) {
        String texto = JOptionPane.showInputDialog("Forneca um texto: ");

        Texto txt = new Texto(texto);

        System.out.println("Texto              : " + txt.getTxt() + " (" + txt.getQtdePalavras()+ " palavras)");
        System.out.println("Texto invertido    : " + txt.inverterTexto());
    }
}
