package ui;

/**
 * Interface de entrada e saida do sistema. Permite que a aplicacao
 * funcione tanto em modo grafico ({@link IOGrafico}) quanto em modo
 * texto ({@link IOTexto}) sem alterar a logica de negocio.
 *
 * Convencao: metodos de leitura retornam {@code null} (ou Integer null)
 * quando o usuario cancela a operacao, permitindo que o chamador aborte
 * a acao corrente.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public interface IIO {

    /**
     * Le um texto nao vazio do usuario.
     *
     * @param msg Mensagem a ser exibida.
     * @return O texto digitado, ou {@code null} se o usuario cancelar.
     */
    String lerTexto(String msg);

    /**
     * Le um numero inteiro dentro de um intervalo, repetindo a pergunta
     * ate obter um valor valido ou o usuario cancelar.
     *
     * @param msg Mensagem a ser exibida.
     * @param min Valor minimo aceito (inclusive).
     * @param max Valor maximo aceito (inclusive).
     * @return O inteiro lido, ou {@code null} se o usuario cancelar.
     */
    Integer lerInteiro(String msg, int min, int max);

    /**
     * Le uma confirmacao Sim/Nao do usuario.
     *
     * @param msg Mensagem a ser exibida.
     * @return true para sim, false para nao ou cancelamento.
     */
    boolean lerConfirmacao(String msg);

    /**
     * Exibe uma mensagem ao usuario.
     *
     * @param msg Mensagem a ser exibida.
     */
    void mostrar(String msg);

    /**
     * Libera os recursos utilizados pela implementacao (ex.: Scanner).
     * Deve ser chamado ao encerrar o programa.
     */
    void fechar();
}
