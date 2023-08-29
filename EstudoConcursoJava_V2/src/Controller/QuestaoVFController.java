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
 * Controller respons�vel por gerenciar as quest�es de verdadeiro ou falso.
 */
public class QuestaoVFController {
    private List<QuestaoVF> listaQuestoesVF;
    private int numQuestaoAtual; // Atributo para controlar o n�mero da pr�xima quest�o

    /**
     * Construtor da classe QuestaoVFController.
     * Inicializa a lista de quest�es de verdadeiro ou falso e o n�mero da pr�xima quest�o como 1.
     */
    public QuestaoVFController() {
        listaQuestoesVF = new ArrayList<>();
        numQuestaoAtual = 1; // Inicializa com o n�mero 1
    }
    
    /**
     * Busca uma quest�o de verdadeiro ou falso pelo n�mero da quest�o.
     *
     * @param numQuestao O n�mero da quest�o a ser buscada.
     * @return A quest�o de verdadeiro ou falso encontrada, ou null caso n�o seja encontrada.
     */
    public QuestaoVF buscarQuestaoVF(int numQuestao) {
        for (QuestaoVF questao : listaQuestoesVF) {
            if (questao.getNumQuestao() == numQuestao) {
                return questao;
            }
        }
        return null; // Caso a quest�o n�o seja encontrada
    }

    /**
     * Adiciona uma quest�o de verdadeiro ou falso � lista de quest�es.
     * Atribui o n�mero da quest�o e incrementa o n�mero da pr�xima quest�o.
     *
     * @param questaoVF A quest�o de verdadeiro ou falso a ser adicionada.
     */
    public void adicionarQuestaoVF(QuestaoVF questaoVF) {
        questaoVF.setNumQuestao(numQuestaoAtual); // Atribui o n�mero da quest�o
        listaQuestoesVF.add(questaoVF);
        numQuestaoAtual++; // Incrementa o n�mero da pr�xima quest�o
    }

    /**
     * Remove uma quest�o de verdadeiro ou falso da lista de quest�es.
     *
     * @param questaoVF A quest�o de verdadeiro ou falso a ser removida.
     */
    public void removerQuestaoVF(QuestaoVF questaoVF) {
        listaQuestoesVF.remove(questaoVF);
    }
    
    /**
     * Cria uma nova Quest�o VF por meio de uma janela de di�logo.
     * A janela permite ao usu�rio inserir o enunciado, mat�ria e resposta da quest�o.
     * A quest�o � ent�o criada e adicionada ao QuestaoVFController.
     */
    public void criarQuestaoVF() {
        // Cria��o da janela de di�logo para criar uma nova quest�o VF
        JDialog dialog = new JDialog();
        dialog.setTitle("Criar Quest�o VF");
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);

        // Painel principal da janela de di�logo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Campos de texto para o enunciado, mat�ria e resposta
        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(30);

        JLabel lblMateria = new JLabel("Mat�ria:");
        JTextField txtMateria = new JTextField(30);

        JLabel lblResposta = new JLabel("Resposta (Verdadeira ou Falsa):");
        JTextField txtResposta = new JTextField(30);

        panel.add(lblEnunciado);
        panel.add(txtEnunciado);
        panel.add(lblMateria);
        panel.add(txtMateria);
        panel.add(lblResposta);
        panel.add(txtResposta);

        // Bot�o de OK para criar a quest�o VF
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> {
            // Obt�m os valores dos campos de texto
            String enunciado = txtEnunciado.getText();
            String materia = txtMateria.getText();
            String respostaStr = txtResposta.getText();

            boolean resposta;
            // Verifica se a resposta � "Verdadeira" ou "Falsa"
            if (respostaStr.equalsIgnoreCase("verdadeira")) {
                resposta = true;
            } else if (respostaStr.equalsIgnoreCase("falsa")) {
                resposta = false;
            } else {
                // Valida��o: exibe uma mensagem de erro se a resposta n�o for v�lida
                JOptionPane.showMessageDialog(null, "Resposta inv�lida. Digite Verdadeira ou Falsa.", "Erro", JOptionPane.ERROR_MESSAGE);
                return; // Sai do m�todo se a resposta for inv�lida
            }

            // Obter o n�mero da pr�xima quest�o
            int numQuestao = getProximoNumQuestao();

            // Criar a quest�o VF e adicion�-la ao QuestaoVFController
            QuestaoVF questaoVF = new QuestaoVF(enunciado, materia, numQuestao, resposta);
            adicionarQuestaoVF(questaoVF);

            // Exibir mensagem de sucesso e fechar a janela de di�logo
            JOptionPane.showMessageDialog(null, "Quest�o VF criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose(); // Fecha a janela de di�logo ap�s criar a quest�o
        });

        panel.add(btnOK);

        // Exibi��o da janela de di�logo
        dialog.setVisible(true);
    }


    /**
     * Atualiza uma quest�o de verdadeiro ou falso na lista de quest�es.
     *
     * @param questaoVF A quest�o de verdadeiro ou falso atualizada.
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
     * Abre uma janela de di�logo para editar uma quest�o do tipo Verdadeiro ou Falso (VF).
     * Os campos de enunciado e resposta correta s�o preenchidos com os valores atuais da quest�oVF
     * fornecida como par�metro. O usu�rio pode fazer as altera��es desejadas e salvar a quest�o atualizada.
     *
     * @param questaoVF A quest�o VF a ser editada.
     */
    public void editarQuestaoVF(QuestaoVF questaoVF) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Quest�o Verdadeiro ou Falso");
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

            JOptionPane.showMessageDialog(dialog, "Quest�o VF atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            dialog.dispose();
        });

        panel.add(btnSalvar);

        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    /**
     * Verifica se uma quest�o de verdadeiro ou falso existe na lista de quest�es.
     *
     * @param numQuestao O n�mero da quest�o a ser verificada.
     * @return true se a quest�o existir, false caso contr�rio.
     */
    public boolean existeQuestao(int numQuestao) {
        for (QuestaoVF questao : listaQuestoesVF) {
            if (questao.getNumQuestao() == numQuestao) {
                return true;
            }
        }
        return false; // Caso a quest�o n�o seja encontrada
    }
    
    /**
     * Obt�m a lista de todas as quest�es de verdadeiro ou falso.
     *
     * @return A lista de quest�es de verdadeiro ou falso.
     */
    public List<QuestaoVF> getQuestoesVF() {
        return listaQuestoesVF;
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