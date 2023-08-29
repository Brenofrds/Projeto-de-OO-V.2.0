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
 * Classe responsável por exibir a tela de Questões.
 * A tela possui funcionalidades para criar, listar, buscar, remover e editar questões.
 */
public class TelaQuestoes extends JFrame {
	/**
	 * Controlador responsável por gerenciar Questoes VF e Questoes Alternativas.
	 */
    private QuestaoVFController questaoVFController;
    private QuestaoAlternativaController questaoAlternativaController;

    /**
     * Construtor da classe TelaQuestoes.
     * Inicializa os controllers das questões VF e questões de múltipla escolha.
     * Configura a janela da tela de questões.
     * Adiciona botões para criar, listar, buscar, remover e editar questões.
     */
    public TelaQuestoes() {
        questaoVFController = new QuestaoVFController();
        questaoAlternativaController = new QuestaoAlternativaController();

        // Configurações da janela
        setTitle("Questões");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal da janela
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Botão de Criar Questão VF
        JButton btnCriarQuestaoVF = new JButton("Criar Questão VF");
        btnCriarQuestaoVF.addActionListener(e -> questaoVFController.criarQuestaoVF());
        panel.add(btnCriarQuestaoVF);

        // Botão de Criar Questão Alternativa
        JButton btnCriarQuestaoAlternativa = new JButton("Criar Questão Alternativa");
        btnCriarQuestaoAlternativa.addActionListener(e -> questaoAlternativaController.criarQuestaoAlternativa());
        panel.add(btnCriarQuestaoAlternativa);
        
        // Botão de Lista Questões
        JButton btnListaQuestoes = new JButton("Lista Questões");
        btnListaQuestoes.addActionListener(e -> listarQuestoes());
        panel.add(btnListaQuestoes);
        
        // Botão de Listar Questões por Matéria
        JButton btnListarPorMateria = new JButton("Listar Questões por Matéria");
        btnListarPorMateria.addActionListener(e -> listarQuestoesPorMateria());
        panel.add(btnListarPorMateria);
        
        // Botão de Buscar Questão
        JButton btnBuscarQuestao = new JButton("Buscar Questão");
        btnBuscarQuestao.addActionListener(e -> buscarQuestao());
        panel.add(btnBuscarQuestao);

        // Botão de Remover Questão
        JButton btnRemoverQuestao = new JButton("Remover Questão");
        btnRemoverQuestao.addActionListener(e -> removerQuestao());
        panel.add(btnRemoverQuestao);
        
        // Botão de Editar Questão
        JButton btnEditarQuestao = new JButton("Editar Questão");
        btnEditarQuestao.addActionListener(e -> editarQuestao());
        panel.add(btnEditarQuestao);


    }

    /**
     * Retorna uma representação em formato de string da questão fornecida.
     *
     * @param questao A questão a ser exibida.
     * @return Uma representação em formato de string da questão.
     */
    public String exibirQuestao(Questao questao) {
        // Cria uma string com o número da questão
        String numQuestaoStr = "Número da Questão: " + questao.getNumQuestao() + "\n";

        return numQuestaoStr + questao.toString();
    }


    /**
     * Lista todas as questões, exibindo-as em uma caixa de diálogo.
     */
    public void listarQuestoes() {
        // Obtém a lista de questões Verdadeiro ou Falso e de questões Alternativa
        List<QuestaoVF> questoesVF = questaoVFController.getQuestoesVF();
        List<QuestaoAlternativa> questoesAlternativa = questaoAlternativaController.getQuestoesAlternativa();

        // Cria uma lista para armazenar todas as questões
        List<Questao> todasQuestoes = new ArrayList<>();

        // Adiciona todas as questões VF à lista
        todasQuestoes.addAll(questoesVF);

        // Adiciona todas as questões Alternativa à lista
        todasQuestoes.addAll(questoesAlternativa);

        // Ordena a lista de questões pelo número da questão, utilizando um Comparator personalizado
        Collections.sort(todasQuestoes, new Comparator<Questao>() {
            @Override
            public int compare(Questao q1, Questao q2) {
                return Integer.compare(q1.getNumQuestao(), q2.getNumQuestao());
            }
        });

        // Utilizado para construir a string com as questões
        StringBuilder sb = new StringBuilder();

        // Percorre todas as questões e adiciona à string
        for (Questao questao : todasQuestoes) {
            sb.append(exibirQuestao(questao)).append("\n\n");
        }

        // Exibe a lista de questões em uma caixa de diálogo
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Questões", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Lista todas as questões de acordo com uma matéria específica, exibindo-as em uma caixa de diálogo.
     * A matéria é obtida através de uma entrada do usuário.
     */
    public void listarQuestoesPorMateria() {
        // Solicita ao usuário que digite a matéria
        String materia = JOptionPane.showInputDialog(null, "Digite a matéria:");

        // Validação de entrada de matéria vazia ou cancelada pelo usuário
        if (materia == null || materia.trim().isEmpty()) {
            return;
        }

        // Obtém a lista de questões Verdadeiro ou Falso e de questões Alternativa
        List<QuestaoVF> questoesVF = questaoVFController.getQuestoesVF();
        List<QuestaoAlternativa> questoesAlternativa = questaoAlternativaController.getQuestoesAlternativa();

        // Utilizado para construir a string com as questões encontradas
        StringBuilder sb = new StringBuilder();

        // Filtra as questões por matéria e adiciona à string
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

        // Verifica se foram encontradas questões com a matéria informada
        if (sb.length() > 0) {
            // Exibe a lista de questões encontradas em uma caixa de diálogo
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Questões por Matéria", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Exibe uma mensagem informando que não foram encontradas questões com a matéria informada
            JOptionPane.showMessageDialog(null, "Não foram encontradas questões para a matéria informada.", "Lista de Questões por Matéria", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    

    /**
     * Abre uma janela de diálogo para buscar uma questão. O usuário pode selecionar o tipo de questão
     * (Questão Alternativa ou Questão VF) e digitar o número da questão desejada.
     */
    public void buscarQuestao() {
        // Criação da janela de diálogo
        JDialog dialog = new JDialog(this, "Buscar Questão", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Botões para seleção do tipo de questão
        JButton btnQuestaoAlternativa = new JButton("Questão Alternativa");
        JButton btnQuestaoVF = new JButton("Questão VF");

        // Campo de texto para digitar o número da questão
        JLabel lblNumeroQuestao = new JLabel("Número da Questão:");
        JTextField txtNumeroQuestao = new JTextField(10);

        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);
        panel.add(btnQuestaoAlternativa);
        panel.add(btnQuestaoVF);

        // Ação do botão "Questão Alternativa"
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obtém o número da questão digitado
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Busca a questão de múltipla escolha pelo número
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numQuestao);

            if (questaoAlternativa != null) {
                // Exibe a questão de múltipla escolha encontrada em uma caixa de diálogo
                JOptionPane.showMessageDialog(null, exibirQuestao(questaoAlternativa), "Questão Alternativa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe uma mensagem de erro se a questão de múltipla escolha não for encontrada
                JOptionPane.showMessageDialog(null, "Questão Alternativa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Ação do botão "Questão VF"
        btnQuestaoVF.addActionListener(e -> {
            // Obtém o número da questão digitado
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Busca a questão Verdadeiro ou Falso pelo número
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numQuestao);

            if (questaoVF != null) {
                // Exibe a questão Verdadeiro ou Falso encontrada em uma caixa de diálogo
                JOptionPane.showMessageDialog(null, exibirQuestao(questaoVF), "Questão VF", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe uma mensagem de erro se a questão Verdadeiro ou Falso não for encontrada
                JOptionPane.showMessageDialog(null, "Questão VF não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Exibição da janela de diálogo:
        dialog.setVisible(true);
    }


    /**
     * Exibe uma janela de confirmação para exclusão de uma questão. Se o usuário confirmar a exclusão,
     * a questão é removida do sistema.
     *
     * @param questao A questão a ser excluída.
     */
    public void mostrarConfirmacaoExclusao(Questao questao) {
        // Exibe uma caixa de diálogo de confirmação para a exclusão da questão
        int opcao = JOptionPane.showOptionDialog(
            null,
            "Deseja realmente excluir a questão?",
            "Confirmação de Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{"OK", "Cancelar"},
            "OK"
        );

        // Verifica se a opção selecionada foi "OK" (excluir a questão)
        if (opcao == JOptionPane.YES_OPTION) {
            // Verifica o tipo da questão
            if (questao instanceof QuestaoAlternativa) {
                QuestaoAlternativa questaoAlternativa = (QuestaoAlternativa) questao;

                // Chama o controlador para remover a questão de múltipla escolha
                questaoAlternativaController.removerQuestaoAlternativa(questaoAlternativa);
            } else if (questao instanceof QuestaoVF) {
                QuestaoVF questaoVF = (QuestaoVF) questao;

                // Chama o controlador para remover a questão Verdadeiro ou Falso
                questaoVFController.removerQuestaoVF(questaoVF);
            }

            // Exibe uma mensagem de sucesso após a exclusão da questão
            JOptionPane.showMessageDialog(null, "Questão removida com sucesso.");

            // Atualiza a lista de questões exibida na tela
            listarQuestoes();
        }
    }


    
    
    /**
     * Abre uma janela de diálogo para remover uma questão do sistema. O usuário pode selecionar
     * entre os tipos de questão (Questão Alternativa ou Questão VF) e informar o número da questão
     * a ser removida.
     */
    public void removerQuestao() {
        // Criação da janela de diálogo para remover uma questão
        JDialog dialog = new JDialog(this, "Remover Questão", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Rótulo e campo de texto para digitar o número da questão
        JLabel lblNumeroQuestao = new JLabel("Número da Questão:");
        JTextField txtNumeroQuestao = new JTextField(10);

        // Adiciona o rótulo e o campo de texto ao painel
        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);

        // Botões para selecionar uma QuestaoAlternativa ou QuestaoVF
        JButton btnQuestaoAlternativa = new JButton("Questão Alternativa");
        JButton btnQuestaoVF = new JButton("Questão VF");

        // Adiciona os botões ao painel
        panel.add(btnQuestaoAlternativa);
        panel.add(btnQuestaoVF);

        // Ação do botão "Questão Alternativa"
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());
            // Busca a questão Alternativa com base no número da questão
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numQuestao);

            if (questaoAlternativa != null) {
                // Mostra uma confirmação de exclusão da questão
                mostrarConfirmacaoExclusao(questaoAlternativa);
            } else {
                // Exibe uma mensagem de erro se a questão Alternativa não for encontrada
                JOptionPane.showMessageDialog(null, "Questão Alternativa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Ação do botão "Questão VF"
        btnQuestaoVF.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());
            // Busca a questão VF com base no número da questão
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numQuestao);

            if (questaoVF != null) {
                // Mostra uma confirmação de exclusão da questão
                mostrarConfirmacaoExclusao(questaoVF);
            } else {
                // Exibe uma mensagem de erro se a questão VF não for encontrada
                JOptionPane.showMessageDialog(null, "Questão VF não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Exibição da janela de diálogo
        dialog.setVisible(true);
    }

    
    /**
     * Abre uma janela de diálogo para editar uma questão do sistema. O usuário pode selecionar
     * entre os tipos de questão (Questão VF ou Questão Alternativa) e informar o número da questão
     * a ser editada.
     */
    public void editarQuestao() {
        // Criação da janela de diálogo para editar uma questão
        JDialog dialog = new JDialog(this, "Editar Questão", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Rótulo e campo de texto para digitar o número da questão
        JLabel lblNumeroQuestao = new JLabel("Número da Questão:");
        JTextField txtNumeroQuestao = new JTextField(10);

        // Adiciona o rótulo e o campo de texto ao painel
        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);

        // Botão para selecionar uma QuestaoVF
        JButton btnQuestaoVF = new JButton("QuestaoVF");
        btnQuestaoVF.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numeroQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Verifica se a questão VF existe
            if (!questaoVFController.existeQuestao(numeroQuestao)) {
                // Exibe uma mensagem de erro se a questão VF não for encontrada
                JOptionPane.showMessageDialog(dialog, "Questão VF não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtém a questão VF com base no número da questão
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numeroQuestao);
            // Chama o método para editar a questão VF
            questaoVFController.editarQuestaoVF(questaoVF);

            // Fecha a janela de diálogo
            dialog.dispose();
        });

        // Botão para selecionar uma QuestaoAlternativa
        JButton btnQuestaoAlternativa = new JButton("QuestaoAlternativa");
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numeroQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Verifica se a questão Alternativa existe
            if (!questaoAlternativaController.existeQuestao(numeroQuestao)) {
                // Exibe uma mensagem de erro se a questão Alternativa não for encontrada
                JOptionPane.showMessageDialog(dialog, "Questão Alternativa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtém a questão Alternativa com base no número da questão
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numeroQuestao);
            // Chama o método para editar a questão Alternativa
            questaoAlternativaController.editarQuestaoAlternativa(questaoAlternativa);

            // Fecha a janela de diálogo
            dialog.dispose();
        });

        // Adiciona os botões ao painel
        panel.add(btnQuestaoVF);
        panel.add(btnQuestaoAlternativa);

        // Exibe a janela de diálogo
        dialog.setVisible(true);
    }
}