package model;

/**
 * Excecao lancada quando o semestre informado para um aluno esta fora
 * do intervalo valido (1 a 12).
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class SemestreInvalidoException extends Exception {

    /**
     * Construtor padrao com mensagem default.
     */
    public SemestreInvalidoException() {
        super("Semestre invalido.");
    }

    /**
     * Construtor que recebe uma mensagem customizada.
     *
     * @param msg Mensagem descritiva do erro.
     */
    public SemestreInvalidoException(String msg) {
        super(msg);
    }
}
