/**
 * Classe utilitaria para manipulacao de textos, incluindo remocao de espacos excedentes e inversao.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class Texto {
    private String txt;

    /**
     * Construtor para objetos da classe Texto.
     * 
     * @param txt O texto inicial.
     */
    Texto(String txt) {
        setTxt(txt);
        limpaEspacosExcedentes();
    }

    /**
     * Define o texto.
     * 
     * @param t O texto a ser armazenado.
     */
    private void setTxt(String t) {
        this.txt = t;
    }

    /**
     * Obtem o texto armazenado.
     * 
     * @return O texto.
     */
    public String getTxt() {
        return this.txt;
    }

    /**
     * Inverte os caracteres do texto.
     * 
     * @return O texto invertido.
     */
    public String inverterTexto() {
        String txtInvertido = "";
        if(!(this.txt == null || this.txt.equals("") )) {
            for (int i=this.txt.length()-1; i >= 0; i--){
                txtInvertido = txtInvertido + this.txt.charAt(i);
            }
        } else {
            return null;
        }
        return txtInvertido;
    }

    /**
     * Obtem a quantidade de palavras no texto, separadas por espaco.
     * 
     * @return O numero de palavras.
     */
    public int getQtdePalavras() {
        return (getTxt().split(" ").length);
    }

    /**
     * Remove espacos em branco no inicio, no fim e espacos duplicados entre palavras.
     */
    private void limpaEspacosExcedentes() {
        setTxt(this.txt.trim());

        String s = "";
        for (int i=0; i < this.txt.length(); i++) {
            s = s + this.txt.charAt(i); 
            if(this.txt.charAt(i) == ' '){
                while(this.txt.charAt(i+1) == ' ') { 
                    i++;
                }
            }
        }
        setTxt(s);
    }

    /**
     * Retorna o texto armazenado.
     * 
     * @return O texto em formato de string.
     */
    public String toString() {
        return(getTxt());
    }
}