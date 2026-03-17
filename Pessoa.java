
/**
 * Escreva uma descrição da classe Pessoa aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Pessoa {
    NomePessoa nome;
    int idade;
    
    
    Pessoa (String nome, int idade){
        this.nome = new NomePessoa(nome);
    }

}