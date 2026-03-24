/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class Aluno extends Pessoa {  
    String ra;
    String curso;
    int semestre;

    Aluno(String nome, int idade, String ra, String curso, int semestre) {
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