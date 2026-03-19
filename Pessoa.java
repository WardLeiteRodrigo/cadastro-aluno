/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public class Pessoa {
    NomePessoa nome;
    int idade;
    
    Pessoa(String nome, int idade) {
        this.nome = new NomePessoa(nome);
        this.idade = idade;
    }
}