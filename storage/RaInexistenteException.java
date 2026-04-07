package storage;

/**
 * Excecao lancada quando uma operacao (remocao, atualizacao ou busca) e
 * requisitada para um RA que nao existe no cadastro.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class RaInexistenteException extends Exception {

    /**
     * Construtor padrao com mensagem default.
     */
    public RaInexistenteException() {
        super("RA nao encontrado no cadastro.");
    }

    /**
     * Construtor que recebe uma mensagem customizada.
     *
     * @param msg Mensagem descritiva do erro.
     */
    public RaInexistenteException(String msg) {
        super(msg);
    }
}
