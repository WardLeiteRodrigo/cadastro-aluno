package storage;

import java.io.*;
/**
 * Exemplo de gravacao e leitura de um array de {@link model.Pessoa} em
 * arquivo binario utilizando a classe utilitaria {@link ArquivoBinario}.
 * Demonstra como persistir multiplos objetos serializaveis de uma unica vez
 * e recupera-los preservando o tipo do array original.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/27
 */
public class GravarArray {

    /**
     * Classe de exemplo: nao deve ser instanciada.
     */
    private GravarArray() {
    }

    /**
     * Ponto de entrada do exemplo: cria um array de pessoas, grava-o em
     * arquivo binario e em seguida le o conteudo de volta para a memoria,
     * imprimindo os objetos lidos.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String[] args) {
        /*
        Pessoa cad[] = new Pessoa[2];
        cad[0] = new Pessoa("Julio Arakaki", 22, "12345", 78, 1.67);
        cad[1] = new Pessoa("Bruno da Costa Neto", 22, "12345", 78, 1.67);

        // Arquivo para gravar os dados
        ArquivoBinario ab = new ArquivoBinario("lixo.bin");
        
        // Gravacao do array
        ab.gravarObj(cad);

        // Leitura dos dados
        Pessoa cad1[] = (Pessoa[])ab.lerObj();
        System.out.println("\nLido do arquivo:" + cad1[0]);
        System.out.println("\nLido do arquivo:" + cad1[1]);
        */
    }
}
