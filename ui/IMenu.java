/**
 * Interface destinada a estabelecer o contrato padrão de exibição de menu (Polimorfismo).
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public interface IMenu {
    /**
     * Método primário encarregado de construir e apresentar o menu visualmente.
     * @param opcoes Uma matriz contendo as strings de opções da aplicação.
     * @return O digito da opção escolhida pelo usuário.
     */
    int criarMenu(String opcoes[]);
}