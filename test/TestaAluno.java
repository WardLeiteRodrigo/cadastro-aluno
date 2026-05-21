package test;

import model.Aluno;
import model.IdadeInvalidaException;
import model.SemestreInvalidoException;

/**
 * Teste manual da classe {@link Aluno}: constroi um aluno com dados validos,
 * imprime sua representacao textual padrao e o nome em formato bibliografico,
 * tratando as excecoes de validacao de idade e semestre.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/03/19
 */
public class TestaAluno {

    /**
     * Classe de teste manual: nao deve ser instanciada.
     */
    private TestaAluno() {
    }

    /**
     * Ponto de entrada do teste manual. Cria uma instancia de {@link Aluno}
     * e imprime no console seu {@code toString()} e seu nome bibliografico.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String args[]) {
        try {
            Aluno a = new Aluno("Ze da Silva Pereira Antunes", 22, "RA123456", "Engenharia Civil", 3);
            System.out.println(a);
            System.out.println("Nome bibliografico: " + a.getNomeBiblio());
        } catch (IdadeInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (SemestreInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
