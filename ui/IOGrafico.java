package ui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Implementacao grafica de {@link IIO} usando componentes Swing
 * variados: {@link JDialog} modal para entrada de dados (com
 * {@link JTextField} para textos e {@link JSpinner} para inteiros),
 * {@link JFileChooser} para selecao de arquivos e {@link JTextArea}
 * em {@link JScrollPane} para exibicao de listas longas. Apenas o
 * dialogo de confirmacao continua usando {@link JOptionPane}.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 2.0 2026/04/27
 */
public class IOGrafico implements IIO {

    /**
     * Le um texto nao vazio em um JDialog modal com JTextField.
     *
     * @param msg Rotulo do campo (ex.: "Nome:").
     * @return Texto digitado, ou null se cancelado.
     */
    public String lerTexto(String msg) {
        while (true) {
            String[] resultado = new String[1];
            JTextField campo = new JTextField(20);
            JPanel painel = montarPainelCampo(msg, campo);

            boolean ok = exibirDialogo("Entrada", painel, campo, new Runnable() {
                public void run() {
                    resultado[0] = campo.getText();
                }
            });

            if (!ok) return null;
            if (resultado[0] == null || resultado[0].trim().isEmpty()) {
                mostrar("Entrada invalida. O valor nao pode ser vazio.");
                continue;
            }
            return resultado[0].trim();
        }
    }

    /**
     * Le um inteiro em um JDialog modal com JSpinner ja limitado ao intervalo.
     *
     * @param msg Rotulo do campo.
     * @param min Valor minimo aceito.
     * @param max Valor maximo aceito.
     * @return Inteiro lido, ou null se cancelado.
     */
    public Integer lerInteiro(String msg, int min, int max) {
        int inicial = (min <= 0 && max >= 0) ? 0 : min;
        SpinnerNumberModel modelo = new SpinnerNumberModel(inicial, min, max, 1);
        JSpinner spinner = new JSpinner(modelo);
        spinner.setPreferredSize(new Dimension(120, 26));

        JPanel painel = montarPainelCampo(msg + " (" + min + " a " + max + ")", spinner);
        Integer[] resultado = new Integer[1];

        boolean ok = exibirDialogo("Entrada", painel, spinner, new Runnable() {
            public void run() {
                try {
                    spinner.commitEdit();
                } catch (Exception ignored) {
                    // valor parcial; usa o ultimo valido
                }
                resultado[0] = (Integer) spinner.getValue();
            }
        });

        return ok ? resultado[0] : null;
    }

    /**
     * Pergunta sim/nao usando JOptionPane (suficiente para confirmacao).
     */
    public boolean lerConfirmacao(String msg) {
        int resp = JOptionPane.showConfirmDialog(null, msg, "Confirmacao", JOptionPane.YES_NO_OPTION);
        return resp == JOptionPane.YES_OPTION;
    }

    /**
     * Exibe uma mensagem em janela com JTextArea + JScrollPane para preservar
     * legibilidade em textos longos.
     */
    public void mostrar(String msg) {
        JTextArea textArea = new JTextArea(msg);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(420, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Abre um JFileChooser para o usuario escolher o caminho do arquivo,
     * filtrando por arquivos .dat. Ao salvar, garante que o caminho
     * retornado termine em ".dat".
     */
    public String escolherArquivo(boolean paraSalvar) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(paraSalvar ? "Salvar cadastro" : "Abrir cadastro");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
            "Arquivos de cadastro (*.dat)", "dat");
        chooser.setFileFilter(filtro);
        chooser.setAcceptAllFileFilterUsed(false);

        int resp = paraSalvar ? chooser.showSaveDialog(null) : chooser.showOpenDialog(null);
        if (resp != JFileChooser.APPROVE_OPTION) return null;
        File f = chooser.getSelectedFile();
        if (f == null) return null;

        String caminho = f.getAbsolutePath();
        if (paraSalvar && !caminho.toLowerCase().endsWith(".dat")) {
            caminho = caminho + ".dat";
        }
        return caminho;
    }

    public void fechar() {
        // sem recursos a liberar
    }

    // ----------------- helpers privados -----------------

    /**
     * Monta um painel vertical com um rotulo (JLabel) e um campo (JTextField/JSpinner).
     */
    private JPanel montarPainelCampo(String rotulo, JComponent campo) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // JLabel ignora \n em texto puro; converte para HTML para preservar quebras de linha
        String html = "<html>" + rotulo.replace("&", "&amp;")
                                       .replace("<", "&lt;")
                                       .replace(">", "&gt;")
                                       .replace("\n", "<br>") + "</html>";
        JLabel lbl = new JLabel(html, SwingConstants.LEFT);
        lbl.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        campo.setAlignmentX(JComponent.LEFT_ALIGNMENT);

        painel.add(lbl);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campo);
        return painel;
    }

    /**
     * Cria um JDialog modal com botoes OK/Cancelar e bloqueia ate o usuario
     * escolher. Retorna true se OK, false se Cancelar/fechou.
     *
     * @param titulo      Titulo da janela.
     * @param conteudo    Painel central com os campos.
     * @param foco        Componente que deve receber o foco inicial.
     * @param aoConfirmar Callback executado antes de fechar quando OK e clicado.
     */
    private boolean exibirDialogo(String titulo, JPanel conteudo, JComponent foco, Runnable aoConfirmar) {
        final boolean[] confirmou = new boolean[1];
        final JDialog dlg = new JDialog((java.awt.Frame) null, titulo, true);
        dlg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JButton ok = new JButton("OK");
        JButton cancelar = new JButton("Cancelar");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aoConfirmar.run();
                confirmou[0] = true;
                dlg.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmou[0] = false;
                dlg.dispose();
            }
        });

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(ok);
        botoes.add(cancelar);

        dlg.getContentPane().setLayout(new BorderLayout());
        dlg.getContentPane().add(conteudo, BorderLayout.CENTER);
        dlg.getContentPane().add(botoes, BorderLayout.SOUTH);
        dlg.getRootPane().setDefaultButton(ok);

        dlg.pack();
        dlg.setLocationRelativeTo(null);
        if (foco != null) foco.requestFocusInWindow();
        dlg.setVisible(true);
        return confirmou[0];
    }
}
