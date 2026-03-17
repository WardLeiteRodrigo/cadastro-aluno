
/**
 * Escreva uma descrição da classe Pessoa aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Aluno extends Pessoa {
    String ra;
    
    
    public Aluno(String nome, int idade, String ra){
        super(nome, idade);
        this.ra = ra;
        
    }
    
    public String toString(){
            return ("\nNome: " + nome + "\nIdade: " + idade + "\nRa: " + ra);
    }
}