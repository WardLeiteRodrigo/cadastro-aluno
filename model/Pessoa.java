package model;

/**
 * Esta classe modela os dados primários de uma Pessoa (Nome e Idade).
 * Classe base para demais especializações (ex. Aluno).
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class Pessoa {
    private NomePessoa nome;
    private int idade;

    /**
     * Construtor de Pessoa informando nome e idade
     * @param nome O nome completo da pessoa.
     * @param idade A idade em anos da pessoa.
     */
    Pessoa(String nome, int idade){
        this.nome = new NomePessoa(nome);
        this.idade = idade;
    }

    /**
     * Delega e acessa o método getNomeBiblio para produzir uma formatação bibliográfica 
     * da classe de nome referenciada.
     * @return O nome formatado em norma bibliográfica.
     */
    public String getNomeBiblio(){
        return(nome.getNomeBiblio());
    }

    /**
     * Metodo padronizado para retornar string descritiva dos atributos desta base. 
     * @return O nome e a idade dispostos de forma amigável.
     */
    public String toString(){
        return this.nome.toString() + "\nIdade: " + idade;
    }
}
