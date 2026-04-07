package test;

/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public class TestaAluno {
    public static void main(String args[]) {
        Aluno a = new Aluno("Ze da Silva Pereira Antunes", 22, "RA123456", "Engenharia Civil", 3);

        System.out.println(a);
        System.out.println("Nome bibliografico: " + a.getNomeBiblio());
    }
}
