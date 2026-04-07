/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
package test;

import model.Aluno;
import model.IdadeInvalidaException;
import model.SemestreInvalidoException;

public class TestaAluno {
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