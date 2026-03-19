/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */

public class NomePessoa {
    private Texto nome;

    public NomePessoa(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return this.nome.getTxt();
    }

    protected void setNome(String nome) {
        this.nome = new Texto(nome);
    }

    public int getQtdePalavras() {
        return this.nome.getQtdePalavras();
    }
    
    public String getNomeInvertido() {
        return this.nome.inverterTexto();
    }

    public String getNomeBiblio() {
        String vts[] = this.nome.getTxt().split(" ");
        int qtd = vts.length;

        String sBib = vts[qtd-1] + ", "; 
        for (int i=0; i < (qtd-1); i++){
            String pal = vts[i].toLowerCase(); 
            if(!verificaStr(pal)) { 
                sBib = sBib + vts[i].toUpperCase().charAt(0) + ". ";
            }
        }
        return sBib;
    }

    private boolean verificaStr(String s) {
        final String sRet[] = {"da", "de", "do", "di", "das", "dos", "e",};

        for (int i = 0; i < sRet.length; i++){
            if(sRet[i].equals(s)){
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.nome.toString();
    }
}