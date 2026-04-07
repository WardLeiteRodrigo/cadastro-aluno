/**
 * Classe que representa o nome de uma pessoa e fornece metodos de manipulacao de string.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
package model;

public class NomePessoa {
    private Texto nome;

    /**
     * Construtor para objetos da classe NomePessoa.
     * 
     * @param nome O nome a ser armazenado.
     */
    public NomePessoa(String nome) {
        setNome(nome);
    }

    /**
     * Obtem o nome armazenado como string.
     * 
     * @return O nome completo.
     */
    public String getNome() {
        return this.nome.getTxt();
    }

    /**
     * Define o nome, instanciando um objeto Texto.
     * 
     * @param nome O nome a ser definido.
     */
    protected void setNome(String nome) {
        this.nome = new Texto(nome);
    }

    /**
     * Obtem a quantidade de palavras no nome.
     * 
     * @return O numero de palavras.
     */
    public int getQtdePalavras() {
        return this.nome.getQtdePalavras();
    }
    
    /**
     * Obtem o nome invertido, de tras para frente.
     * 
     * @return O nome invertido.
     */
    public String getNomeInvertido() {
        return this.nome.inverterTexto();
    }

    /**
     * Obtem o nome formatado para referencias bibliograficas (ex: SILVA, J. M.).
     * 
     * @return O nome em formato bibliografico.
     */
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

    /**
     * Verifica se uma palavra e um conectivo (da, de, do, etc.) que deve ser ignorado na formatacao bibliografica.
     * 
     * @param s A palavra a ser verificada.
     * @return true se for um conectivo, false caso contrario.
     */
    private boolean verificaStr(String s) {
        final String sRet[] = {"da", "de", "do", "di", "das", "dos", "e",};

        for (int i = 0; i < sRet.length; i++){
            if(sRet[i].equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna a representacao em string do nome.
     * 
     * @return O nome completo.
     */
    public String toString() {
        return this.nome.toString();
    }
}