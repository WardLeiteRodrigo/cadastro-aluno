/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 17/03/2026
 */

public class Aluno extends Pessoa {
    String ra;
    
    public Aluno(String nome, int idade, String ra) {
        super(nome, idade);
        this.ra = ra;
    }
    
    public String toString() {
        return ("\nNome: " + nome + "\nIdade: " + idade + "\nRa: " + ra);
    }
}