package storage;

/**
 * Excecao lancada quando se tenta inserir um aluno com RA ja existente
 * no cadastro.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class RaDuplicadoException extends Exception {

    /**
     * Construtor padrao com mensagem default.
     */
    public RaDuplicadoException() {
        super("Ja existe um aluno cadastrado com este RA.");
    }

    /**
     * Construtor que recebe uma mensagem customizada.
     *
     * @param msg Mensagem descritiva do erro.
     */
    public RaDuplicadoException(String msg) {
        super(msg);
    }
}
