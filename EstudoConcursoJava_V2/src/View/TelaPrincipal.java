/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe respons�vel por exibir a tela principal do aplicativo.
 */
public class TelaPrincipal extends JFrame {

	/**
     * Construtor da classe TelaPrincipal.
     * Configura a janela e adiciona os bot�es de Editais e Quest�es.
     */
    public TelaPrincipal() {
        // Configura��es da janela
        setTitle("Meu Aplicativo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal da janela
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Bot�o de Editais
        JButton btnEditais = new JButton("Editais");
        btnEditais.addActionListener(e -> {
            // Abrir a tela de Editais
            TelaEditais telaEditais = new TelaEditais();
            telaEditais.setVisible(true);
        });
        panel.add(btnEditais);

        // Bot�o de Quest�es
        JButton btnQuestoes = new JButton("Quest�es");
        btnQuestoes.addActionListener(e -> {
            // Abrir a tela de Quest�es
            TelaQuestoes telaQuestoes = new TelaQuestoes();
            telaQuestoes.setVisible(true);
        });
        panel.add(btnQuestoes);
    }

    /**
     * M�todo principal que cria uma inst�ncia da classe TelaPrincipal
     * e exibe a janela principal.
     *
     * @param args os argumentos de linha de comando (n�o utilizados neste caso)
     */
    public static void main(String[] args) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();

        // Exibir a janela
        telaPrincipal.setVisible(true);
    }
}