/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Controller;

import Model.QuestaoAlternativa;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
/**
 * Controller responsável por gerenciar as questões de múltipla escolha.
 */
public class QuestaoAlternativaController {
    private List<QuestaoAlternativa> listaQuestoesAlternativa;
    private int numQuestaoAtual; // Atributo para controlar o número da próxima questão

    /**
     * Construtor da classe QuestaoAlternativaController.
     * Inicializa a lista de questões de múltipla escolha e o número da próxima questão como 1.
     */
    public QuestaoAlternativaController() {
        listaQuestoesAlternativa = new ArrayList<>();
        numQuestaoAtual = 1; // Inicializa com o número 1
    }

    /**
     * Adiciona uma questão de múltipla escolha à lista de questões.
     * Atribui o número da questão e incrementa o número da próxima questão.
     *
     * @param questaoAlternativa A questão de múltipla escolha a ser adicionada.
     */
    public void adicionarQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        questaoAlternativa.setNumQuestao(numQuestaoAtual); // Atribui o número da questão
        listaQuestoesAlternativa.add(questaoAlternativa);
        numQuestaoAtual++; // Incrementa o número da próxima questão
    }

    /**
     * Remove uma questão de múltipla escolha da lista de questões.
     *
     * @param questaoAlternativa A questão de múltipla escolha a ser removida.
     */
    public void removerQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        listaQuestoesAlternativa.remove(questaoAlternativa);
    }

    /**
     * Busca uma questão de múltipla escolha pelo número da questão.
     *
     * @param numQuestao O número da questão a ser buscada.
     * @return A questão de múltipla escolha encontrada, ou null caso não seja encontrada.
     */
    
    /**
     * Cria uma nova Questão Alternativa por meio de uma janela de diálogo.
     * A janela permite ao usuário inserir a matéria, enunciado, alternativas e resposta correta da questão.
     * A questão é então criada e adicionada ao QuestaoAlternativaController.
     */
    public void criarQuestaoAlternativa() {
        // Criação da janela de diálogo para criar uma nova questão alternativa
        JDialog dialog = new JDialog();
        dialog.setTitle("Criar Questão Alternativa");
        dialog.setSize(400, 400);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        dialog.add(panel);

        // Campos de texto para a matéria, enunciado, alternativas e resposta
        JLabel lblMateria = new JLabel("Matéria:");
        JTextField txtMateria = new JTextField(30);

        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(30);

        JLabel lblAlternativas = new JLabel("Alternativas:");

        List<JPanel> alternativasPanelList = new ArrayList<>();

        // Criação das alternativas
        for (int i = 0; i < 4; i++) {
            JPanel alternativaPanel = new JPanel();
            JTextField txtAlternativa = new JTextField(15);

            alternativaPanel.add(new JLabel((char) ('A' + i) + ")"));
            alternativaPanel.add(txtAlternativa);

            alternativasPanelList.add(alternativaPanel);
        }

        JLabel lblResposta = new JLabel("Resposta (A, B, C ou D):");
        JTextField txtResposta = new JTextField(5);

        panel.add(lblMateria);
        panel.add(txtMateria);
        panel.add(lblEnunciado);
        panel.add(txtEnunciado);
        panel.add(lblAlternativas);

        // Adiciona as alternativas ao painel
        for (JPanel alternativaPanel : alternativasPanelList) {
            panel.add(alternativaPanel);
        }

        panel.add(lblResposta);
        panel.add(txtResposta);

        // Botão de OK para criar a questão alternativa
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> {
            // Obtém os valores dos campos de texto
            String materia = txtMateria.getText();
            String enunciado = txtEnunciado.getText();
            String[] alternativas = new String[4];
            char resposta = ' ';

            // Obtém as alternativas e a resposta correta
            for (int i = 0; i < 4; i++) {
                JPanel alternativaPanel = alternativasPanelList.get(i);
                JTextField txtAlternativa = (JTextField) alternativaPanel.getComponent(1);
                String alternativa = txtAlternativa.getText();

                // Validação: verifica se todas as alternativas foram preenchidas
                if (alternativa.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todas as alternativas.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                alternativas[i] = alternativa;

                // Verifica se a resposta corresponde a uma das alternativas
                if (txtResposta.getText().equalsIgnoreCase(String.valueOf((char) ('A' + i)))) {
                    resposta = (char) ('A' + i);
                }
            }

            // Validação: verifica se uma resposta válida foi selecionada
            if (resposta == ' ') {
                JOptionPane.showMessageDialog(null, "Resposta inválida. Selecione uma das alternativas como resposta correta.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter o número da próxima questão
            int numQuestao = getProximoNumQuestao();

            // Criar a questão Alternativa e adicioná-la ao QuestaoAlternativaController
            QuestaoAlternativa questaoAlternativa = new QuestaoAlternativa(enunciado, materia, numQuestao, alternativas, resposta);
            adicionarQuestaoAlternativa(questaoAlternativa);

            // Exibir mensagem de sucesso e fechar a janela de diálogo
            JOptionPane.showMessageDialog(null, "Questão Alternativa criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        panel.add(btnOK);

        // Exibição da janela de diálogo
        dialog.setVisible(true);
    }

    
    public QuestaoAlternativa buscarQuestaoAlternativa(int numQuestao) {
        // Percorre a lista de questões alternativas
        for (QuestaoAlternativa questao : listaQuestoesAlternativa) {
            // Verifica se o número da questão da iteração atual é igual ao número da questão buscada
            if (questao.getNumQuestao() == numQuestao) {
                // Retorna a questão alternativa encontrada
                return questao;
            }
        }
        // Retorna null caso a questão não seja encontrada na lista
        return null;
    }


    /**
     * Atualiza uma questão de múltipla escolha na lista de questões.
     *
     * @param questaoAlternativa A questão de múltipla escolha atualizada.
     */
    public void atualizarQuestao(QuestaoAlternativa questaoAlternativa) {
        // Percorre a lista de questões alternativas
        for (int i = 0; i < listaQuestoesAlternativa.size(); i++) {
            // Obtém uma questão alternativa da lista pelo índice
            QuestaoAlternativa questao = listaQuestoesAlternativa.get(i);
            // Verifica se o número da questão da iteração atual é igual ao número da questão que será atualizada
            if (questao.getNumQuestao() == questaoAlternativa.getNumQuestao()) {
                // Atualiza a questão alternativa na lista com a nova questão alternativa fornecida
                listaQuestoesAlternativa.set(i, questaoAlternativa);
                // Encerra o loop, já que a questão foi atualizada com sucesso
                break;
            }
        }
    }
    
    /**
     * Abre uma janela de diálogo para editar uma questão do tipo Alternativa (múltipla escolha).
     * Os campos de enunciado, opções e resposta correta são preenchidos com os valores atuais da questaoAlternativa
     * fornecida como parâmetro. O usuário pode fazer as alterações desejadas e salvar a questão atualizada.
     *
     * @param questaoAlternativa A questão Alternativa a ser editada.
     */
    public void editarQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Questão de Múltipla Escolha");
        dialog.setModal(true);
        dialog.setSize(700, 600);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(questaoAlternativa.getQuestao());
        panel.add(lblEnunciado);
        panel.add(txtEnunciado);

        JLabel lblOpcoes = new JLabel("Escolha a Opção a ser editada");
        panel.add(lblOpcoes);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String opcao : questaoAlternativa.getAlternativas()) {
            listModel.addElement(opcao);
        }
        JList<String> listOpcoes = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listOpcoes);
        panel.add(scrollPane);

        JButton btnEditarOpcao = new JButton("Editar Opção");
        btnEditarOpcao.addActionListener(e -> {
            int selectedIndex = listOpcoes.getSelectedIndex();
            if (selectedIndex != -1) {
                String opcaoAtual = listModel.getElementAt(selectedIndex);
                String novaOpcao = JOptionPane.showInputDialog(dialog, "Digite a nova opção:", opcaoAtual);
                if (novaOpcao != null && !novaOpcao.isEmpty()) {
                    listModel.setElementAt(novaOpcao, selectedIndex);
                }
            }
        });
        panel.add(btnEditarOpcao);

        JLabel lblRespostaCorreta = new JLabel("Resposta Correta:");
        JTextField txtRespostaCorreta = new JTextField(String.valueOf(questaoAlternativa.getResposta()));
        panel.add(lblRespostaCorreta);
        panel.add(txtRespostaCorreta);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            questaoAlternativa.setQuestao(txtEnunciado.getText());

            List<String> opcoes = new ArrayList<>();
            for (int i = 0; i < listModel.getSize(); i++) {
                opcoes.add(listModel.getElementAt(i));
            }
            questaoAlternativa.setAlternativas(opcoes);

            String respostaCorreta = txtRespostaCorreta.getText();
            if (!respostaCorreta.isEmpty()) {
                questaoAlternativa.setResposta(respostaCorreta.charAt(0));
            }

            atualizarQuestao(questaoAlternativa);

            JOptionPane.showMessageDialog(dialog, "Questão de Múltipla Escolha atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            dialog.dispose();
        });
        panel.add(btnSalvar);

        dialog.add(panel);
        dialog.setVisible(true);
    }



    /**
     * Verifica se uma questão de múltipla escolha existe na lista de questões.
     *
     * @param numQuestao O número da questão a ser verificada.
     * @return true se a questão existir, false caso contrário.
     */
    public boolean existeQuestao(int numQuestao) {
        for (QuestaoAlternativa questao : listaQuestoesAlternativa) {
            if (questao.getNumQuestao() == numQuestao) {
                return true;
            }
        }
        return false; // Caso a questão não seja encontrada
    }

    /**
     * Obtém a lista de todas as questões de múltipla escolha.
     *
     * @return A lista de questões de múltipla escolha.
     */
    public List<QuestaoAlternativa> getQuestoesAlternativa() {
        return listaQuestoesAlternativa;
    }

    /**
     * Obtém o número da próxima questão a ser adicionada.
     *
     * @return O número da próxima questão.
     */
    public int getProximoNumQuestao() {
        return numQuestaoAtual;
    }

}