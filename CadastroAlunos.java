/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 17/03/2026
 */

public class CadastroAlunos {
    public static void main(String args[]) {
        Aluno va[] = new Aluno[3];
        
        va[0] = new Aluno("Ana Claudia", 23, "RA1234561");
        va[1] = new Aluno("Ze da Silva", 25, "RA1234567");
        va[2] = new Aluno("Joao Ramalho de Souza", 12, "RA1234568");
        
        for (int i = 0; i < va.length; i++){
            System.out.println(va[i].nome.getNomeBiblio());
        }      
    }
}