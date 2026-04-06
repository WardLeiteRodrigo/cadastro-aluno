/**
 * Classe que representa um aluno, herdando atributos e metodos de Pessoa.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
package model;

public class Aluno extends Pessoa {  
    public String ra;
    public String curso;
    public int semestre;

    /**
     * Construtor para objetos da classe Aluno.
     * 
     * @param nome O nome do aluno.
     * @param idade A idade do aluno.
     * @param ra O registro academico do aluno.
     * @param curso O curso no qual o aluno esta matriculado.
     * @param semestre O semestre atual do aluno.
     */
    public Aluno(String nome, int idade, String ra, String curso, int semestre) {
        super(nome, idade);
        
        this.ra = ra;
        this.curso = curso;
        this.semestre = semestre;
    }
    
    /**
     * Obtem o registro academico (RA) do aluno.
     * 
     * @return O RA do aluno.
     */
    public String getRa() {
        ra = this.ra;
        return ra;
    }
    
    /**
     * Obtem o nome do aluno formatado para referencias bibliograficas.
     * 
     * @return O nome do aluno em formato bibliografico.
     */
    public String getNomeBiblio() {
        return super.getNomeBiblio();
    }
    
    /**
     * Retorna uma representacao em string dos dados do aluno.
     * 
     * @return Uma string contendo os dados do aluno.
     */
    public String toString() {
        return super.toString() + "\nra: " + ra + "\nCurso: " + curso + "\nSemestre: " + semestre;
    }
}