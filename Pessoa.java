/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public class Pessoa {
    private NomePessoa nome;
    private int idade;

    Pessoa(String nome, int idade){
        this.nome = new NomePessoa(nome);
        this.idade = idade;
    }

    public String getNomeBiblio(){
        return(nome.getNomeBiblio());
    }

    public String toString(){
        return this.nome.toString() + "\nIdade: " + idade;
    }
}