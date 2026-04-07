/**
 * Classe que representa uma pessoa com nome e idade.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
package model;

public class Pessoa {
    private NomePessoa nome;
    private int idade;

    /**
     * Construtor para objetos da classe Pessoa.
     * 
     * @param nome O nome da pessoa.
     * @param idade A idade da pessoa.
     */
    public Pessoa(String nome, int idade){
        this.nome = new NomePessoa(nome);
        this.idade = idade;
    }

    /**
     * Obtem o nome da pessoa formatado para referencias bibliograficas.
     * 
     * @return O nome da pessoa em formato bibliografico.
     */
    public String getNomeBiblio(){
        return(nome.getNomeBiblio());
    }

    /**
     * Retorna uma representacao em string dos dados da pessoa.
     * 
     * @return Uma string contendo os dados da pessoa.
     */
    public String toString(){
        return this.nome.toString() + "\nIdade: " + idade;
    }
}