/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package View;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Model.Questao;
import Model.QuestaoAlternativa;
import Model.QuestaoVF;
import Controller.QuestaoAlternativaController;
import Controller.QuestaoVFController;

/**
 * Classe respons�vel por exibir a tela de Quest�es.
 * A tela possui funcionalidades para criar, listar, buscar, remover e editar quest�es.
 */
public class TelaQuestoes extends JFrame {
	/**
	 * Controlador respons�vel por gerenciar Questoes VF e Questoes Alternativas.
	 */
    private QuestaoVFController questaoVFController;
    private QuestaoAlternativaController questaoAlternativaController;

    /**
     * Construtor da classe TelaQuestoes.
     * Inicializa os controllers das quest�es VF e quest�es de m�ltipla escolha.
     * Configura a janela da tela de quest�es.
     * Adiciona bot�es para criar, listar, buscar, remover e editar quest�es.
     */
    public TelaQuestoes() {
        questaoVFController = new QuestaoVFController();
        questaoAlternativaController = new QuestaoAlternativaController();

        // Configura��es da janela
        setTitle("Quest�es");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal da janela
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Bot�o de Criar Quest�o VF
        JButton btnCriarQuestaoVF = new JButton("Criar Quest�o VF");
        btnCriarQuestaoVF.addActionListener(e -> questaoVFController.criarQuestaoVF());
        panel.add(btnCriarQuestaoVF);

        // Bot�o de Criar Quest�o Alternativa
        JButton btnCriarQuestaoAlternativa = new JButton("Criar Quest�o Alternativa");
        btnCriarQuestaoAlternativa.addActionListener(e -> questaoAlternativaController.criarQuestaoAlternativa());
        panel.add(btnCriarQuestaoAlternativa);
        
        // Bot�o de Lista Quest�es
        JButton btnListaQuestoes = new JButton("Lista Quest�es");
        btnListaQuestoes.addActionListener(e -> listarQuestoes());
        panel.add(btnListaQuestoes);
        
        // Bot�o de Listar Quest�es por Mat�ria
        JButton btnListarPorMateria = new JButton("Listar Quest�es por Mat�ria");
        btnListarPorMateria.addActionListener(e -> listarQuestoesPorMateria());
        panel.add(btnListarPorMateria);
        
        // Bot�o de Buscar Quest�o
        JButton btnBuscarQuestao = new JButton("Buscar Quest�o");
        btnBuscarQuestao.addActionListener(e -> buscarQuestao());
        panel.add(btnBuscarQuestao);

        // Bot�o de Remover Quest�o
        JButton btnRemoverQuestao = new JButton("Remover Quest�o");
        btnRemoverQuestao.addActionListener(e -> removerQuestao());
        panel.add(btnRemoverQuestao);
        
        // Bot�o de Editar Quest�o
        JButton btnEditarQuestao = new JButton("Editar Quest�o");
        btnEditarQuestao.addActionListener(e -> editarQuestao());
        panel.add(btnEditarQuestao);


    }

    /**
     * Retorna uma representa��o em formato de string da quest�o fornecida.
     *
     * @param questao A quest�o a ser exibida.
     * @return Uma representa��o em formato de string da quest�o.
     */
    public String exibirQuestao(Questao questao) {
        // Cria uma string com o n�mero da quest�o
        String numQuestaoStr = "N�mero da Quest�o: " + questao.getNumQuestao() + "\n";

        return numQuestaoStr + questao.toString();
    }


    /**
     * Lista todas as quest�es, exibindo-as em uma caixa de di�logo.
     */
    public void listarQuestoes() {
        // Obt�m a lista de quest�es Verdadeiro ou Falso e de quest�es Alternativa
        List<QuestaoVF> questoesVF = questaoVFController.getQuestoesVF();
        List<QuestaoAlternativa> questoesAlternativa = questaoAlternativaController.getQuestoesAlternativa();

        // Cria uma lista para armazenar todas as quest�es
        List<Questao> todasQuestoes = new ArrayList<>();

        // Adiciona todas as quest�es VF � lista
        todasQuestoes.addAll(questoesVF);

        // Adiciona todas as quest�es Alternativa � lista
        todasQuestoes.addAll(questoesAlternativa);

        // Ordena a lista de quest�es pelo n�mero da quest�o, utilizando um Comparator personalizado
        Collections.sort(todasQuestoes, new Comparator<Questao>() {
            @Override
            public int compare(Questao q1, Questao q2) {
                return Integer.compare(q1.getNumQuestao(), q2.getNumQuestao());
            }
        });

        // Utilizado para construir a string com as quest�es
        StringBuilder sb = new StringBuilder();

        // Percorre todas as quest�es e adiciona � string
        for (Questao questao : todasQuestoes) {
            sb.append(exibirQuestao(questao)).append("\n\n");
        }

        // Exibe a lista de quest�es em uma caixa de di�logo
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Quest�es", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Lista todas as quest�es de acordo com uma mat�ria espec�fica, exibindo-as em uma caixa de di�logo.
     * A mat�ria � obtida atrav�s de uma entrada do usu�rio.
     */
    public void listarQuestoesPorMateria() {
        // Solicita ao usu�rio que digite a mat�ria
        String materia = JOptionPane.showInputDialog(null, "Digite a mat�ria:");

        // Valida��o de entrada de mat�ria vazia ou cancelada pelo usu�rio
        if (materia == null || materia.trim().isEmpty()) {
            return;
        }

        // Obt�m a lista de quest�es Verdadeiro ou Falso e de quest�es Alternativa
        List<QuestaoVF> questoesVF = questaoVFController.getQuestoesVF();
        List<QuestaoAlternativa> questoesAlternativa = questaoAlternativaController.getQuestoesAlternativa();

        // Utilizado para construir a string com as quest�es encontradas
        StringBuilder sb = new StringBuilder();

        // Filtra as quest�es por mat�ria e adiciona � string
        for (QuestaoVF questaoVF : questoesVF) {
            if (questaoVF.getMateria().equalsIgnoreCase(materia)) {
                sb.append(exibirQuestao(questaoVF)).append("\n\n");
            }
        }

        for (QuestaoAlternativa questaoAlternativa : questoesAlternativa) {
            if (questaoAlternativa.getMateria().equalsIgnoreCase(materia)) {
                sb.append(exibirQuestao(questaoAlternativa)).append("\n\n");
            }
        }

        // Verifica se foram encontradas quest�es com a mat�ria informada
        if (sb.length() > 0) {
            // Exibe a lista de quest�es encontradas em uma caixa de di�logo
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Quest�es por Mat�ria", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Exibe uma mensagem informando que n�o foram encontradas quest�es com a mat�ria informada
            JOptionPane.showMessageDialog(null, "N�o foram encontradas quest�es para a mat�ria informada.", "Lista de Quest�es por Mat�ria", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    

    /**
     * Abre uma janela de di�logo para buscar uma quest�o. O usu�rio pode selecionar o tipo de quest�o
     * (Quest�o Alternativa ou Quest�o VF) e digitar o n�mero da quest�o desejada.
     */
    public void buscarQuestao() {
        // Cria��o da janela de di�logo
        JDialog dialog = new JDialog(this, "Buscar Quest�o", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de di�logo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Bot�es para sele��o do tipo de quest�o
        JButton btnQuestaoAlternativa = new JButton("Quest�o Alternativa");
        JButton btnQuestaoVF = new JButton("Quest�o VF");

        // Campo de texto para digitar o n�mero da quest�o
        JLabel lblNumeroQuestao = new JLabel("N�mero da Quest�o:");
        JTextField txtNumeroQuestao = new JTextField(10);

        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);
        panel.add(btnQuestaoAlternativa);
        panel.add(btnQuestaoVF);

        // A��o do bot�o "Quest�o Alternativa"
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obt�m o n�mero da quest�o digitado
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Busca a quest�o de m�ltipla escolha pelo n�mero
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numQuestao);

            if (questaoAlternativa != null) {
                // Exibe a quest�o de m�ltipla escolha encontrada em uma caixa de di�logo
                JOptionPane.showMessageDialog(null, exibirQuestao(questaoAlternativa), "Quest�o Alternativa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe uma mensagem de erro se a quest�o de m�ltipla escolha n�o for encontrada
                JOptionPane.showMessageDialog(null, "Quest�o Alternativa n�o encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de di�logo ap�s buscar a quest�o
        });

        // A��o do bot�o "Quest�o VF"
        btnQuestaoVF.addActionListener(e -> {
            // Obt�m o n�mero da quest�o digitado
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Busca a quest�o Verdadeiro ou Falso pelo n�mero
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numQuestao);

            if (questaoVF != null) {
                // Exibe a quest�o Verdadeiro ou Falso encontrada em uma caixa de di�logo
                JOptionPane.showMessageDialog(null, exibirQuestao(questaoVF), "Quest�o VF", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe uma mensagem de erro se a quest�o Verdadeiro ou Falso n�o for encontrada
                JOptionPane.showMessageDialog(null, "Quest�o VF n�o encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de di�logo ap�s buscar a quest�o
        });

        // Exibi��o da janela de di�logo:
        dialog.setVisible(true);
    }


    /**
     * Exibe uma janela de confirma��o para exclus�o de uma quest�o. Se o usu�rio confirmar a exclus�o,
     * a quest�o � removida do sistema.
     *
     * @param questao A quest�o a ser exclu�da.
     */
    public void mostrarConfirmacaoExclusao(Questao questao) {
        // Exibe uma caixa de di�logo de confirma��o para a exclus�o da quest�o
        int opcao = JOptionPane.showOptionDialog(
            null,
            "Deseja realmente excluir a quest�o?",
            "Confirma��o de Exclus�o",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{"OK", "Cancelar"},
            "OK"
        );

        // Verifica se a op��o selecionada foi "OK" (excluir a quest�o)
        if (opcao == JOptionPane.YES_OPTION) {
            // Verifica o tipo da quest�o
            if (questao instanceof QuestaoAlternativa) {
                QuestaoAlternativa questaoAlternativa = (QuestaoAlternativa) questao;

                // Chama o controlador para remover a quest�o de m�ltipla escolha
                questaoAlternativaController.removerQuestaoAlternativa(questaoAlternativa);
            } else if (questao instanceof QuestaoVF) {
                QuestaoVF questaoVF = (QuestaoVF) questao;

                // Chama o controlador para remover a quest�o Verdadeiro ou Falso
                questaoVFController.removerQuestaoVF(questaoVF);
            }

            // Exibe uma mensagem de sucesso ap�s a exclus�o da quest�o
            JOptionPane.showMessageDialog(null, "Quest�o removida com sucesso.");

            // Atualiza a lista de quest�es exibida na tela
            listarQuestoes();
        }
    }


    
    
    /**
     * Abre uma janela de di�logo para remover uma quest�o do sistema. O usu�rio pode selecionar
     * entre os tipos de quest�o (Quest�o Alternativa ou Quest�o VF) e informar o n�mero da quest�o
     * a ser removida.
     */
    public void removerQuestao() {
        // Cria��o da janela de di�logo para remover uma quest�o
        JDialog dialog = new JDialog(this, "Remover Quest�o", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de di�logo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // R�tulo e campo de texto para digitar o n�mero da quest�o
        JLabel lblNumeroQuestao = new JLabel("N�mero da Quest�o:");
        JTextField txtNumeroQuestao = new JTextField(10);

        // Adiciona o r�tulo e o campo de texto ao painel
        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);

        // Bot�es para selecionar uma QuestaoAlternativa ou QuestaoVF
        JButton btnQuestaoAlternativa = new JButton("Quest�o Alternativa");
        JButton btnQuestaoVF = new JButton("Quest�o VF");

        // Adiciona os bot�es ao painel
        panel.add(btnQuestaoAlternativa);
        panel.add(btnQuestaoVF);

        // A��o do bot�o "Quest�o Alternativa"
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obt�m o n�mero da quest�o digitado no campo de texto
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());
            // Busca a quest�o Alternativa com base no n�mero da quest�o
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numQuestao);

            if (questaoAlternativa != null) {
                // Mostra uma confirma��o de exclus�o da quest�o
                mostrarConfirmacaoExclusao(questaoAlternativa);
            } else {
                // Exibe uma mensagem de erro se a quest�o Alternativa n�o for encontrada
                JOptionPane.showMessageDialog(null, "Quest�o Alternativa n�o encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de di�logo ap�s buscar a quest�o
        });

        // A��o do bot�o "Quest�o VF"
        btnQuestaoVF.addActionListener(e -> {
            // Obt�m o n�mero da quest�o digitado no campo de texto
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());
            // Busca a quest�o VF com base no n�mero da quest�o
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numQuestao);

            if (questaoVF != null) {
                // Mostra uma confirma��o de exclus�o da quest�o
                mostrarConfirmacaoExclusao(questaoVF);
            } else {
                // Exibe uma mensagem de erro se a quest�o VF n�o for encontrada
                JOptionPane.showMessageDialog(null, "Quest�o VF n�o encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de di�logo ap�s buscar a quest�o
        });

        // Exibi��o da janela de di�logo
        dialog.setVisible(true);
    }

    
    /**
     * Abre uma janela de di�logo para editar uma quest�o do sistema. O usu�rio pode selecionar
     * entre os tipos de quest�o (Quest�o VF ou Quest�o Alternativa) e informar o n�mero da quest�o
     * a ser editada.
     */
    public void editarQuestao() {
        // Cria��o da janela de di�logo para editar uma quest�o
        JDialog dialog = new JDialog(this, "Editar Quest�o", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de di�logo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // R�tulo e campo de texto para digitar o n�mero da quest�o
        JLabel lblNumeroQuestao = new JLabel("N�mero da Quest�o:");
        JTextField txtNumeroQuestao = new JTextField(10);

        // Adiciona o r�tulo e o campo de texto ao painel
        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);

        // Bot�o para selecionar uma QuestaoVF
        JButton btnQuestaoVF = new JButton("QuestaoVF");
        btnQuestaoVF.addActionListener(e -> {
            // Obt�m o n�mero da quest�o digitado no campo de texto
            int numeroQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Verifica se a quest�o VF existe
            if (!questaoVFController.existeQuestao(numeroQuestao)) {
                // Exibe uma mensagem de erro se a quest�o VF n�o for encontrada
                JOptionPane.showMessageDialog(dialog, "Quest�o VF n�o encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obt�m a quest�o VF com base no n�mero da quest�o
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numeroQuestao);
            // Chama o m�todo para editar a quest�o VF
            questaoVFController.editarQuestaoVF(questaoVF);

            // Fecha a janela de di�logo
            dialog.dispose();
        });

        // Bot�o para selecionar uma QuestaoAlternativa
        JButton btnQuestaoAlternativa = new JButton("QuestaoAlternativa");
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obt�m o n�mero da quest�o digitado no campo de texto
            int numeroQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Verifica se a quest�o Alternativa existe
            if (!questaoAlternativaController.existeQuestao(numeroQuestao)) {
                // Exibe uma mensagem de erro se a quest�o Alternativa n�o for encontrada
                JOptionPane.showMessageDialog(dialog, "Quest�o Alternativa n�o encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obt�m a quest�o Alternativa com base no n�mero da quest�o
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numeroQuestao);
            // Chama o m�todo para editar a quest�o Alternativa
            questaoAlternativaController.editarQuestaoAlternativa(questaoAlternativa);

            // Fecha a janela de di�logo
            dialog.dispose();
        });

        // Adiciona os bot�es ao painel
        panel.add(btnQuestaoVF);
        panel.add(btnQuestaoAlternativa);

        // Exibe a janela de di�logo
        dialog.setVisible(true);
    }
}