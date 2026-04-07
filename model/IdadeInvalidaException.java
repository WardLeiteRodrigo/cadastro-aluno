package model;

/**
 * Excecao lancada quando a idade informada para um aluno esta fora do
 * intervalo valido (16 a 120 anos).
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class IdadeInvalidaException extends Exception {

    /**
     * Construtor padrao com mensagem default.
     */
    public IdadeInvalidaException() {
        super("Idade invalida.");
    }

    /**
     * Construtor que recebe uma mensagem customizada.
     *
     * @param msg Mensagem descritiva do erro.
     */
    public IdadeInvalidaException(String msg) {
        super(msg);
    }
}
