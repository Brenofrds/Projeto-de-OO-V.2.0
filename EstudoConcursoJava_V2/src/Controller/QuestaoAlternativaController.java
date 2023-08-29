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
 * Controller respons�vel por gerenciar as quest�es de m�ltipla escolha.
 */
public class QuestaoAlternativaController {
    private List<QuestaoAlternativa> listaQuestoesAlternativa;
    private int numQuestaoAtual; // Atributo para controlar o n�mero da pr�xima quest�o

    /**
     * Construtor da classe QuestaoAlternativaController.
     * Inicializa a lista de quest�es de m�ltipla escolha e o n�mero da pr�xima quest�o como 1.
     */
    public QuestaoAlternativaController() {
        listaQuestoesAlternativa = new ArrayList<>();
        numQuestaoAtual = 1; // Inicializa com o n�mero 1
    }

    /**
     * Adiciona uma quest�o de m�ltipla escolha � lista de quest�es.
     * Atribui o n�mero da quest�o e incrementa o n�mero da pr�xima quest�o.
     *
     * @param questaoAlternativa A quest�o de m�ltipla escolha a ser adicionada.
     */
    public void adicionarQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        questaoAlternativa.setNumQuestao(numQuestaoAtual); // Atribui o n�mero da quest�o
        listaQuestoesAlternativa.add(questaoAlternativa);
        numQuestaoAtual++; // Incrementa o n�mero da pr�xima quest�o
    }

    /**
     * Remove uma quest�o de m�ltipla escolha da lista de quest�es.
     *
     * @param questaoAlternativa A quest�o de m�ltipla escolha a ser removida.
     */
    public void removerQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        listaQuestoesAlternativa.remove(questaoAlternativa);
    }

    /**
     * Busca uma quest�o de m�ltipla escolha pelo n�mero da quest�o.
     *
     * @param numQuestao O n�mero da quest�o a ser buscada.
     * @return A quest�o de m�ltipla escolha encontrada, ou null caso n�o seja encontrada.
     */
    
    /**
     * Cria uma nova Quest�o Alternativa por meio de uma janela de di�logo.
     * A janela permite ao usu�rio inserir a mat�ria, enunciado, alternativas e resposta correta da quest�o.
     * A quest�o � ent�o criada e adicionada ao QuestaoAlternativaController.
     */
    public void criarQuestaoAlternativa() {
        // Cria��o da janela de di�logo para criar uma nova quest�o alternativa
        JDialog dialog = new JDialog();
        dialog.setTitle("Criar Quest�o Alternativa");
        dialog.setSize(400, 400);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);

        // Painel principal da janela de di�logo
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        dialog.add(panel);

        // Campos de texto para a mat�ria, enunciado, alternativas e resposta
        JLabel lblMateria = new JLabel("Mat�ria:");
        JTextField txtMateria = new JTextField(30);

        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(30);

        JLabel lblAlternativas = new JLabel("Alternativas:");

        List<JPanel> alternativasPanelList = new ArrayList<>();

        // Cria��o das alternativas
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

        // Bot�o de OK para criar a quest�o alternativa
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> {
            // Obt�m os valores dos campos de texto
            String materia = txtMateria.getText();
            String enunciado = txtEnunciado.getText();
            String[] alternativas = new String[4];
            char resposta = ' ';

            // Obt�m as alternativas e a resposta correta
            for (int i = 0; i < 4; i++) {
                JPanel alternativaPanel = alternativasPanelList.get(i);
                JTextField txtAlternativa = (JTextField) alternativaPanel.getComponent(1);
                String alternativa = txtAlternativa.getText();

                // Valida��o: verifica se todas as alternativas foram preenchidas
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

            // Valida��o: verifica se uma resposta v�lida foi selecionada
            if (resposta == ' ') {
                JOptionPane.showMessageDialog(null, "Resposta inv�lida. Selecione uma das alternativas como resposta correta.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter o n�mero da pr�xima quest�o
            int numQuestao = getProximoNumQuestao();

            // Criar a quest�o Alternativa e adicion�-la ao QuestaoAlternativaController
            QuestaoAlternativa questaoAlternativa = new QuestaoAlternativa(enunciado, materia, numQuestao, alternativas, resposta);
            adicionarQuestaoAlternativa(questaoAlternativa);

            // Exibir mensagem de sucesso e fechar a janela de di�logo
            JOptionPane.showMessageDialog(null, "Quest�o Alternativa criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        panel.add(btnOK);

        // Exibi��o da janela de di�logo
        dialog.setVisible(true);
    }

    
    public QuestaoAlternativa buscarQuestaoAlternativa(int numQuestao) {
        // Percorre a lista de quest�es alternativas
        for (QuestaoAlternativa questao : listaQuestoesAlternativa) {
            // Verifica se o n�mero da quest�o da itera��o atual � igual ao n�mero da quest�o buscada
            if (questao.getNumQuestao() == numQuestao) {
                // Retorna a quest�o alternativa encontrada
                return questao;
            }
        }
        // Retorna null caso a quest�o n�o seja encontrada na lista
        return null;
    }


    /**
     * Atualiza uma quest�o de m�ltipla escolha na lista de quest�es.
     *
     * @param questaoAlternativa A quest�o de m�ltipla escolha atualizada.
     */
    public void atualizarQuestao(QuestaoAlternativa questaoAlternativa) {
        // Percorre a lista de quest�es alternativas
        for (int i = 0; i < listaQuestoesAlternativa.size(); i++) {
            // Obt�m uma quest�o alternativa da lista pelo �ndice
            QuestaoAlternativa questao = listaQuestoesAlternativa.get(i);
            // Verifica se o n�mero da quest�o da itera��o atual � igual ao n�mero da quest�o que ser� atualizada
            if (questao.getNumQuestao() == questaoAlternativa.getNumQuestao()) {
                // Atualiza a quest�o alternativa na lista com a nova quest�o alternativa fornecida
                listaQuestoesAlternativa.set(i, questaoAlternativa);
                // Encerra o loop, j� que a quest�o foi atualizada com sucesso
                break;
            }
        }
    }
    
    /**
     * Abre uma janela de di�logo para editar uma quest�o do tipo Alternativa (m�ltipla escolha).
     * Os campos de enunciado, op��es e resposta correta s�o preenchidos com os valores atuais da questaoAlternativa
     * fornecida como par�metro. O usu�rio pode fazer as altera��es desejadas e salvar a quest�o atualizada.
     *
     * @param questaoAlternativa A quest�o Alternativa a ser editada.
     */
    public void editarQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Quest�o de M�ltipla Escolha");
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

        JLabel lblOpcoes = new JLabel("Escolha a Op��o a ser editada");
        panel.add(lblOpcoes);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String opcao : questaoAlternativa.getAlternativas()) {
            listModel.addElement(opcao);
        }
        JList<String> listOpcoes = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listOpcoes);
        panel.add(scrollPane);

        JButton btnEditarOpcao = new JButton("Editar Op��o");
        btnEditarOpcao.addActionListener(e -> {
            int selectedIndex = listOpcoes.getSelectedIndex();
            if (selectedIndex != -1) {
                String opcaoAtual = listModel.getElementAt(selectedIndex);
                String novaOpcao = JOptionPane.showInputDialog(dialog, "Digite a nova op��o:", opcaoAtual);
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

            JOptionPane.showMessageDialog(dialog, "Quest�o de M�ltipla Escolha atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            dialog.dispose();
        });
        panel.add(btnSalvar);

        dialog.add(panel);
        dialog.setVisible(true);
    }



    /**
     * Verifica se uma quest�o de m�ltipla escolha existe na lista de quest�es.
     *
     * @param numQuestao O n�mero da quest�o a ser verificada.
     * @return true se a quest�o existir, false caso contr�rio.
     */
    public boolean existeQuestao(int numQuestao) {
        for (QuestaoAlternativa questao : listaQuestoesAlternativa) {
            if (questao.getNumQuestao() == numQuestao) {
                return true;
            }
        }
        return false; // Caso a quest�o n�o seja encontrada
    }

    /**
     * Obt�m a lista de todas as quest�es de m�ltipla escolha.
     *
     * @return A lista de quest�es de m�ltipla escolha.
     */
    public List<QuestaoAlternativa> getQuestoesAlternativa() {
        return listaQuestoesAlternativa;
    }

    /**
     * Obt�m o n�mero da pr�xima quest�o a ser adicionada.
     *
     * @return O n�mero da pr�xima quest�o.
     */
    public int getProximoNumQuestao() {
        return numQuestaoAtual;
    }

}