/**
 * Classe que representa uma pessoa com nome e idade.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 1.0 2026/04/07
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
     * Obtem o nome da pessoa.
     *
     * @return O nome (texto puro) da pessoa.
     */
    public String getNome(){
        return this.nome.getNome();
    }

    /**
     * Obtem a idade da pessoa.
     *
     * @return A idade em anos.
     */
    public int getIdade(){
        return this.idade;
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