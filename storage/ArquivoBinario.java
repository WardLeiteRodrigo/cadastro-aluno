package storage;

import java.io.*;
/**
 * @author julio
 * Classe para gravar e ler arquivo binario
 */
public class ArquivoBinario{
    
    String nomeArq;
    
    ArquivoBinario(String nomeArq){
        this.nomeArq = nomeArq;
    }

    /**
     * Method gravarObj grava um objeto em arquivo
     *
     * @param objeto objeto a ser gravado
     * @param nomeArq nome do arquivo
     */
    public void gravarObj(Object objeto){
        ObjectOutputStream output = null;
        try {
            File file = new File(this.nomeArq);
            output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(objeto);  // escreve o objeto no arquivo
        } catch(Exception e){
            System.out.println(e.toString());
        } finally {
            try {
                output.close();
            } catch(Exception ex) {
                // Nao faz nada!
            }
        }
    }

    /**
     * Method lerObj
     *
     * @param nomeArq nome do arquivo a ser lido
     * @return Object o objeto lido
     */
    public Object lerObj(){
        Object objeto = null;
        ObjectInputStream input = null;
        try {
            File file = new File(this.nomeArq);
            input = new ObjectInputStream(new FileInputStream(file));
            objeto = (Object)input.readObject();  // le o objeto do arquivo
        }
        catch(Exception e){
            System.out.println(e.toString());
        } finally {
            try {
                input.close();
            } catch(Exception ex) {
                // Nao faz nada!
            }
        }
        return objeto;
    }
}
