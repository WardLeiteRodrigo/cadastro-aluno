/**
 * Interface que define o comportamento basico de um menu.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public interface IMenu {
    /**
     * Cria e exibe um menu com as opcoes fornecidas, retornando a escolha do usuario.
     * 
     * @param opcoes O vetor de strings contendo as opcoes do menu.
     * @return O indice (ou identificador) da opcao selecionada.
     */
    int criarMenu(String opcoes[]);
}