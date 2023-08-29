/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
* Esta classe representa a tela de visualização e interação com os editais.
* Ela herda as propriedades e comportamentos da classe JFrame.
*/
package View;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Controller.EditalController;

/**
* Esta classe representa a tela de visualização e interação com os editais.
* Ela herda as propriedades e comportamentos da classe JFrame.
*/

public class TelaEditais extends JFrame {
	private EditalController editalController;

	/**
	 * Construtor da classe TelaEditais.
	 * Inicializa o controlador de edital e configura a janela.
	 */
	public TelaEditais() {
	    editalController = new EditalController();

	    // Configurações da janela de Editais
	    setTitle("Tela de Editais");
	    setSize(300, 400);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    // Painel principal da janela
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Definindo o layout vertical
	    getContentPane().add(panel);

	    // Painel de preenchimento no topo
	    panel.add(Box.createVerticalGlue());

	    // Botão "Criar Edital"
	    JButton btnCriarEdital = new JButton("Criar Edital");
	    btnCriarEdital.addActionListener(e -> {
	        editalController.criarEdital();
	    });
	    panel.add(btnCriarEdital);

	    // Botão "Listar Editais"
	    JButton btnListarEditais = new JButton("Listar Editais");
	    btnListarEditais.addActionListener(e -> {
	        editalController.listarEditais();
	    });
	    panel.add(btnListarEditais);

	    // Botão "Listar Editais Abertos"
	    JButton btnListarEditaisAbertos = new JButton("Listar Editais Abertos");
	    btnListarEditaisAbertos.addActionListener(e -> {
	        editalController.listarEditaisAbertos();
	    });
	    panel.add(btnListarEditaisAbertos);

	    // Botão "Remover Edital"
	    JButton removerEditalButton = new JButton("Remover Edital");
	    removerEditalButton.addActionListener(e -> {
	        editalController.removerEdital();
	    });
	    panel.add(removerEditalButton);

	    // Botão "Pesquisar Edital"
	    JButton pesquisarEditalButton = new JButton("Pesquisar Edital");
	    pesquisarEditalButton.addActionListener(e -> {
	        editalController.pesquisarEdital();
	    });
	    panel.add(pesquisarEditalButton);

	    // Botão "Editar Edital"
	    JButton btnEditarEdital = new JButton("Editar Edital");
	    btnEditarEdital.addActionListener(e -> {
	        editalController.editarEdital();
	    });
	    panel.add(btnEditarEdital);

	    // Painel de preenchimento na parte inferior
	    panel.add(Box.createVerticalGlue());
	}

}