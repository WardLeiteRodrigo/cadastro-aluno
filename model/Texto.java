package model;

/**
 * Classe utilitária para manipulação de textos.
 * Remove espaços excedentes e permite contagem e inversão de palavras.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class Texto {
    private String txt;

    /**
     * Construtor da classe Texto.
     * Ao inicializar, espaços excedentes são removidos automaticamente.
     * @param txt O texto bruto recebido.
     */
    public Texto(String txt) {
        setTxt(txt);
        limpaEspacosExcedentes();
    }

    /**
     * Define o valor do texto interno.
     * @param t Valor em string.
     */
    private void setTxt(String t) {
        this.txt = t;
    }

    /**
     * Retorna o texto formatado.
     * @return O texto armazenado sem espaços excedentes.
     */
    public String getTxt() {
        return this.txt;
    }

    /**
     * Inverte a ordem dos caracteres do texto atual.
     * @return String invertida ou null caso o texto esteja vazio.
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
     * Retorna a quantidade de palavras no texto separadas por espaço.
     * @return Número de palavras.
     */
    public int getQtdePalavras() {
        return (getTxt().split(" ").length);
    }

    /**
     * Limpa espaços vazios nas extremidades e entre as palavras.
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
     * Retorna a representação em String do texto.
     * @return Exatamente o que getTxt() retornaria.
     */
    public String toString() {
        return(getTxt());
    }
}
