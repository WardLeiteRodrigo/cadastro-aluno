package ui;

import java.util.Scanner;

/**
 * Implementacao de {@link IIO} usando o console (System.in/out).
 * O usuario pode cancelar qualquer entrada digitando "cancelar".
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class IOTexto implements IIO {

    /** Palavra-chave para cancelar uma operacao via console. */
    private static final String CANCELAR = "cancelar";

    /** Scanner compartilhado para leitura do teclado. */
    private Scanner sc;

    /**
     * Cria a implementacao de IO em modo texto inicializando o Scanner.
     */
    public IOTexto() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Le um texto nao vazio do console.
     *
     * @param msg Mensagem a ser exibida.
     * @return Texto digitado ou null se o usuario digitar "cancelar".
     */
    public String lerTexto(String msg) {
        String input;
        do {
            System.out.print(msg + " (ou 'cancelar' para abortar): ");
            input = sc.nextLine();
            if (input.trim().equalsIgnoreCase(CANCELAR)) return null;
            if (input.trim().isEmpty()) {
                mostrar("Entrada invalida. O valor nao pode ser vazio.");
            }
        } while (input.trim().isEmpty());
        return input.trim();
    }

    /**
     * Le um inteiro validando intervalo via console.
     *
     * @param msg Mensagem a ser exibida.
     * @param min Valor minimo aceito.
     * @param max Valor maximo aceito.
     * @return Inteiro lido ou null se cancelado.
     */
    public Integer lerInteiro(String msg, int min, int max) {
        while (true) {
            System.out.print(msg + " (ou 'cancelar' para abortar): ");
            String input = sc.nextLine();
            if (input.trim().equalsIgnoreCase(CANCELAR)) return null;
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
     * Pergunta S/N ao usuario via console.
     *
     * @param msg Mensagem a ser exibida.
     * @return true se a resposta comecar por 'S' (case-insensitive).
     */
    public boolean lerConfirmacao(String msg) {
        System.out.print(msg + " (S/N): ");
        String resp = sc.nextLine();
        return resp.trim().equalsIgnoreCase("S");
    }

    /**
     * Exibe uma mensagem no console.
     *
     * @param msg Mensagem a ser exibida.
     */
    public void mostrar(String msg) {
        System.out.println(msg);
    }

    /**
     * Fecha o Scanner do teclado.
     */
    public void fechar() {
        if (sc != null) {
            sc.close();
        }
    }
}
