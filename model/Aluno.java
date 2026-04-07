package model;

/**
 * Classe que representa um aluno do cadastro. Herda nome e idade da
 * classe Pessoa e adiciona os atributos especificos: RA, curso e semestre.
 *
 * Faz a validacao de idade no momento da criacao, lancando
 * {@link IdadeInvalidaException} caso a idade esteja fora do intervalo
 * valido (16 a 120 anos).
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class Aluno extends Pessoa {

    /** Idade minima permitida para um aluno. */
    public static final int IDADE_MIN = 16;

    /** Idade maxima permitida para um aluno. */
    public static final int IDADE_MAX = 120;

    // Atributos especificos do aluno (encapsulados)
    private String ra;
    private String curso;
    private int semestre;

    /**
     * Constroi um objeto Aluno validando a idade.
     *
     * @param nome     Nome completo do aluno.
     * @param idade    Idade do aluno (deve estar entre {@link #IDADE_MIN} e {@link #IDADE_MAX}).
     * @param ra       Registro academico do aluno.
     * @param curso    Curso no qual o aluno esta matriculado.
     * @param semestre Semestre atual do aluno.
     * @throws IdadeInvalidaException se a idade estiver fora do intervalo valido.
     */
    public Aluno(String nome, int idade, String ra, String curso, int semestre)
            throws IdadeInvalidaException {
        super(nome, idade);

        if (idade < IDADE_MIN || idade > IDADE_MAX) {
            throw new IdadeInvalidaException(
                "Idade invalida: deve estar entre " + IDADE_MIN + " e " + IDADE_MAX + ".");
        }

        // Normaliza o RA (remove espacos no inicio/fim) para evitar duplicatas mascaradas
        this.ra = (ra == null) ? null : ra.trim();
        this.curso = curso;
        this.semestre = semestre;
    }

    /**
     * Obtem o registro academico (RA) do aluno.
     *
     * @return O RA do aluno.
     */
    public String getRa() {
        return this.ra;
    }

    /**
     * Obtem o curso do aluno.
     *
     * @return O nome do curso.
     */
    public String getCurso() {
        return this.curso;
    }

    /**
     * Obtem o semestre atual do aluno.
     *
     * @return O numero do semestre.
     */
    public int getSemestre() {
        return this.semestre;
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
     * Retorna uma representacao em string com todos os dados do aluno.
     *
     * @return String com nome, idade, RA, curso e semestre.
     */
    public String toString() {
        return super.toString()
            + "\nRA: " + ra
            + "\nCurso: " + curso
            + "\nSemestre: " + semestre;
    }
}
