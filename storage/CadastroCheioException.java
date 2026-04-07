package storage;

/**
 * Excecao lancada quando se tenta inserir um aluno em um cadastro que
 * ja atingiu sua capacidade maxima.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class CadastroCheioException extends Exception {

    /**
     * Construtor padrao com mensagem default.
     */
    public CadastroCheioException() {
        super("O cadastro atingiu sua capacidade maxima.");
    }

    /**
     * Construtor que recebe uma mensagem customizada.
     *
     * @param msg Mensagem descritiva do erro.
     */
    public CadastroCheioException(String msg) {
        super(msg);
    }
}
