/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Controller;

import Model.QuestaoVF;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * Controller responsável por gerenciar as questões de verdadeiro ou falso.
 */
public class QuestaoVFController {
    private List<QuestaoVF> listaQuestoesVF;
    private int numQuestaoAtual; // Atributo para controlar o número da próxima questão

    /**
     * Construtor da classe QuestaoVFController.
     * Inicializa a lista de questões de verdadeiro ou falso e o número da próxima questão como 1.
     */
    public QuestaoVFController() {
        listaQuestoesVF = new ArrayList<>();
        numQuestaoAtual = 1; // Inicializa com o número 1
    }
    
    /**
     * Busca uma questão de verdadeiro ou falso pelo número da questão.
     *
     * @param numQuestao O número da questão a ser buscada.
     * @return A questão de verdadeiro ou falso encontrada, ou null caso não seja encontrada.
     */
    public QuestaoVF buscarQuestaoVF(int numQuestao) {
        for (QuestaoVF questao : listaQuestoesVF) {
            if (questao.getNumQuestao() == numQuestao) {
                return questao;
            }
        }
        return null; // Caso a questão não seja encontrada
    }

    /**
     * Adiciona uma questão de verdadeiro ou falso à lista de questões.
     * Atribui o número da questão e incrementa o número da próxima questão.
     *
     * @param questaoVF A questão de verdadeiro ou falso a ser adicionada.
     */
    public void adicionarQuestaoVF(QuestaoVF questaoVF) {
        questaoVF.setNumQuestao(numQuestaoAtual); // Atribui o número da questão
        listaQuestoesVF.add(questaoVF);
        numQuestaoAtual++; // Incrementa o número da próxima questão
    }

    /**
     * Remove uma questão de verdadeiro ou falso da lista de questões.
     *
     * @param questaoVF A questão de verdadeiro ou falso a ser removida.
     */
    public void removerQuestaoVF(QuestaoVF questaoVF) {
        listaQuestoesVF.remove(questaoVF);
    }
    
    /**
     * Cria uma nova Questão VF por meio de uma janela de diálogo.
     * A janela permite ao usuário inserir o enunciado, matéria e resposta da questão.
     * A questão é então criada e adicionada ao QuestaoVFController.
     */
    public void criarQuestaoVF() {
        // Criação da janela de diálogo para criar uma nova questão VF
        JDialog dialog = new JDialog();
        dialog.setTitle("Criar Questão VF");
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Campos de texto para o enunciado, matéria e resposta
        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(30);

        JLabel lblMateria = new JLabel("Matéria:");
        JTextField txtMateria = new JTextField(30);

        JLabel lblResposta = new JLabel("Resposta (Verdadeira ou Falsa):");
        JTextField txtResposta = new JTextField(30);

        panel.add(lblEnunciado);
        panel.add(txtEnunciado);
        panel.add(lblMateria);
        panel.add(txtMateria);
        panel.add(lblResposta);
        panel.add(txtResposta);

        // Botão de OK para criar a questão VF
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> {
            // Obtém os valores dos campos de texto
            String enunciado = txtEnunciado.getText();
            String materia = txtMateria.getText();
            String respostaStr = txtResposta.getText();

            boolean resposta;
            // Verifica se a resposta é "Verdadeira" ou "Falsa"
            if (respostaStr.equalsIgnoreCase("verdadeira")) {
                resposta = true;
            } else if (respostaStr.equalsIgnoreCase("falsa")) {
                resposta = false;
            } else {
                // Validação: exibe uma mensagem de erro se a resposta não for válida
                JOptionPane.showMessageDialog(null, "Resposta inválida. Digite Verdadeira ou Falsa.", "Erro", JOptionPane.ERROR_MESSAGE);
                return; // Sai do método se a resposta for inválida
            }

            // Obter o número da próxima questão
            int numQuestao = getProximoNumQuestao();

            // Criar a questão VF e adicioná-la ao QuestaoVFController
            QuestaoVF questaoVF = new QuestaoVF(enunciado, materia, numQuestao, resposta);
            adicionarQuestaoVF(questaoVF);

            // Exibir mensagem de sucesso e fechar a janela de diálogo
            JOptionPane.showMessageDialog(null, "Questão VF criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose(); // Fecha a janela de diálogo após criar a questão
        });

        panel.add(btnOK);

        // Exibição da janela de diálogo
        dialog.setVisible(true);
    }


    /**
     * Atualiza uma questão de verdadeiro ou falso na lista de questões.
     *
     * @param questaoVF A questão de verdadeiro ou falso atualizada.
     */
    public void atualizarQuestao(QuestaoVF questaoVF) {
        for (int i = 0; i < listaQuestoesVF.size(); i++) {
            QuestaoVF questao = listaQuestoesVF.get(i);
            if (questao.getNumQuestao() == questaoVF.getNumQuestao()) {
                listaQuestoesVF.set(i, questaoVF);
                break;
            }
        }
    }
    
    /**
     * Abre uma janela de diálogo para editar uma questão do tipo Verdadeiro ou Falso (VF).
     * Os campos de enunciado e resposta correta são preenchidos com os valores atuais da questãoVF
     * fornecida como parâmetro. O usuário pode fazer as alterações desejadas e salvar a questão atualizada.
     *
     * @param questaoVF A questão VF a ser editada.
     */
    public void editarQuestaoVF(QuestaoVF questaoVF) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Questão Verdadeiro ou Falso");
        dialog.setModal(true);
        dialog.setSize(400, 300);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(questaoVF.getQuestao());
        panel.add(lblEnunciado);
        panel.add(txtEnunciado);

        JLabel lblResposta = new JLabel("Resposta (V ou F):");
        JTextField txtResposta = new JTextField(String.valueOf(questaoVF.getRespostaCorreta()));
        panel.add(lblResposta);
        panel.add(txtResposta);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            questaoVF.setQuestao(txtEnunciado.getText());
            questaoVF.setRespostaCorreta(txtResposta.getText().equalsIgnoreCase("V"));

            atualizarQuestao(questaoVF);

            JOptionPane.showMessageDialog(dialog, "Questão VF atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            dialog.dispose();
        });

        panel.add(btnSalvar);

        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    /**
     * Verifica se uma questão de verdadeiro ou falso existe na lista de questões.
     *
     * @param numQuestao O número da questão a ser verificada.
     * @return true se a questão existir, false caso contrário.
     */
    public boolean existeQuestao(int numQuestao) {
        for (QuestaoVF questao : listaQuestoesVF) {
            if (questao.getNumQuestao() == numQuestao) {
                return true;
            }
        }
        return false; // Caso a questão não seja encontrada
    }
    
    /**
     * Obtém a lista de todas as questões de verdadeiro ou falso.
     *
     * @return A lista de questões de verdadeiro ou falso.
     */
    public List<QuestaoVF> getQuestoesVF() {
        return listaQuestoesVF;
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