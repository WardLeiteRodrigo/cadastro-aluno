package test;

import model.NomePessoa;
import javax.swing.JOptionPane;

/**
 * Teste manual da classe {@link NomePessoa}: solicita um nome ao usuario
 * via {@link JOptionPane} e imprime no console o nome original (com
 * quantidade de palavras), sua versao invertida e o formato bibliografico.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/03/19
 */
public class TestaNomePessoa {

    /**
     * Classe de teste manual: nao deve ser instanciada.
     */
    private TestaNomePessoa() {
    }

    /**
     * Ponto de entrada do teste manual. Le um nome digitado pelo usuario e
     * exibe no console as variacoes geradas por {@link NomePessoa}.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String[] args) {
        String nomePessoa = JOptionPane.showInputDialog("Forneca um nome: ");

        NomePessoa nome = new NomePessoa(nomePessoa);

        System.out.println("Nome              : " + nome.getNome() + " (" + nome.getQtdePalavras()+ " palavras)");
        System.out.println("Nome invertido    : " + nome.getNomeInvertido());
        System.out.println("Nome bibliografico: " + nome.getNomeBiblio());
    }
}
