/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public class Aluno extends Pessoa {
    // Atributos do aluno    
    String ra;
    String curso;
    int semestre;
    //Disciplinas [] disc;

    Aluno(String nome, int idade, String ra, String curso, int semestre) {
        // Aciona o construtor da classe pai (Pessoa)
        super(nome, idade);
        
        this.ra = ra;
        this.curso = curso;
        this.semestre = semestre;
    }
    
    public String getRa() {
        ra = this.ra;
        return ra;
    }
    
    public String getNomeBiblio() {
        return super.getNomeBiblio();
    }
    
    public String toString() {
        return super.toString() + "\nra: " + ra + "\nCurso: " + curso + "\nSemestre: " + semestre;
    }
}