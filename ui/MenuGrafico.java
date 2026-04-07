package ui;

/**
 * Implementacao de {@link IMenu} que delega a entrada/saida para uma
 * instancia de {@link IIO} (modo grafico). Mantem a logica de montagem
 * do menu independente do meio de comunicacao com o usuario.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class MenuGrafico implements IMenu {

    /** Interface de I/O usada para mostrar e ler a opcao do usuario. */
    private IIO io;

    /**
     * Cria o menu grafico recebendo a interface de I/O por injecao.
     *
     * @param io Interface de I/O (esperado: {@link IOGrafico}).
     */
    public MenuGrafico(IIO io) {
        this.io = io;
    }

    /**
     * Monta a string do menu e le a opcao do usuario via {@link IIO}.
     *
     * @param opcoes Vetor com as opcoes a serem exibidas.
     * @return A opcao escolhida; em caso de cancelamento retorna 5 (sair).
     */
    public int criarMenu(String[] opcoes) {
        StringBuilder itens = new StringBuilder();
        for (int i = 0; i < opcoes.length; i++) {
            itens.append("\n").append(opcoes[i]);
        }
        itens.append("\n\nSelecione a opcao:");

        Integer opcao = io.lerInteiro(itens.toString(), 1, opcoes.length);
        // Se o usuario cancelar, assume "sair" (ultima opcao)
        return (opcao == null) ? opcoes.length : opcao;
    }
}
