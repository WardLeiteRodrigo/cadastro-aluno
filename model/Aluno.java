package model;

/**
 * A classe Aluno herda de Pessoa e acrescenta atributos acadêmicos.
 * Ela armazena RA, Curso e Semestre de um aluno.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 24/03/2026
 */
public class Aluno extends Pessoa {
    public String ra;
    public String curso;
    public int semestre;

    /**
     * Construtor da classe Aluno.
     * 
     * @param nome     Nome do aluno.
     * @param idade    Idade do aluno.
     * @param ra       Registro do aluno (RA).
     * @param curso    Nome do curso que o aluno estuda.
     * @param semestre Semestre atual.
     */
    public Aluno(String nome, int idade, String ra, String curso, int semestre) {
        super(nome, idade);
        try {
            this.ra = ra;
            this.curso = curso;
            this.semestre = semestre;
        } catch (Exception e) {
            System.out.println("Erro ao criar aluno: " + e.getMessage());
        }
    }

    /**
     * @return O RA do aluno.
     */
    public String getRa() {
        return this.ra;
    }

    /**
     * @return Nome em formato de bibliografia herdado da superclasse Pessoa.
     */
    public String getNomeBiblio() {
        return super.getNomeBiblio();
    }

    /**
     * Retorna os dados do Aluno estruturados para exibição em texto.
     * 
     * @return String formatada contendo Nome, RA, Curso e Semestre.
     */
    public String toString() {
        return "Nome: " + super.toString() + "\nRA: " + ra + "\nCurso: " + curso + " (Semestre: " + semestre + ")";
    }
}
