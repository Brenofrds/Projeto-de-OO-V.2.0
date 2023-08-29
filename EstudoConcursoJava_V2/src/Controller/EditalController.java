/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Controller;

import Model.Edital;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Controller respons�vel por gerenciar os editais.
 */
public class EditalController {
	/**
	 * 	lista de editais respons�vel por gerenciar os editais.
	 */
    private static List<Edital> listaEditais;

    /**
     * Construtor da classe EditalController.
     * Inicializa a lista de editais.
     */
    public EditalController() {
        listaEditais = new ArrayList<>();
    }

    /**
     * Adiciona um edital � lista de editais.
     *
     * @param edital O edital a ser adicionado.
     */
    public void adicionarEdital(Edital edital) {
        listaEditais.add(edital);
    }

    /**
     * Remove um edital da lista de editais.
     *
     * @param edital O edital a ser removido.
     */
    public void removerEdital(Edital edital) {
        listaEditais.remove(edital);
    }
    public void criarEdital() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2)); // Layout de grid com 7 linhas e 2 colunas

        // Campos de texto para inserir as informa��es do edital
        JTextField nomeField = new JTextField();
        JTextField dataInicioField = new JTextField();
        JTextField dataFimField = new JTextField();
        JTextField dataProvaField = new JTextField();
        JTextField valorInscricaoField = new JTextField();
        JTextField escolaridadeField = new JTextField();
        JTextField numVagasField = new JTextField();

        // Adicione os campos de texto ao painel
        panel.add(new JLabel("Nome:")); // R�tulo para o campo "Nome"
        panel.add(nomeField);
        panel.add(new JLabel("Data de In�cio das Inscri��es:")); // R�tulo para o campo "Data de In�cio das Inscri��es"
        panel.add(dataInicioField);
        panel.add(new JLabel("Data de Fim das Inscri��es:")); // R�tulo para o campo "Data de Fim das Inscri��es"
        panel.add(dataFimField);
        panel.add(new JLabel("Data da Prova:")); // R�tulo para o campo "Data da Prova"
        panel.add(dataProvaField);
        panel.add(new JLabel("Valor da Inscri��o:")); // R�tulo para o campo "Valor da Inscri��o"
        panel.add(valorInscricaoField);
        panel.add(new JLabel("Escolaridade:")); // R�tulo para o campo "Escolaridade"
        panel.add(escolaridadeField);
        panel.add(new JLabel("N�mero de Vagas:")); // R�tulo para o campo "N�mero de Vagas"
        panel.add(numVagasField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Criar Edital", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String dataInicioInscricao = dataInicioField.getText();
            String dataFimInscricao = dataFimField.getText();
            String dataProva = dataProvaField.getText();
            double valorInscricao = Double.parseDouble(valorInscricaoField.getText());
            String escolaridade = escolaridadeField.getText();
            int numVagas = Integer.parseInt(numVagasField.getText());

            Edital novoEdital = new Edital(nome, dataInicioInscricao, dataFimInscricao, dataProva, valorInscricao, escolaridade, numVagas);

            adicionarEdital(novoEdital); // Chamando o m�todo para adicionar o novo edital � lista
            JOptionPane.showMessageDialog(null, "Edital criado com sucesso!");
        }
    }
    
    /**
     * Lista todos os editais cadastrados.
     */
    public void listarEditais() {
        // Verifique se existem editais
        if (listaEditais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "N�o existem editais cadastrados.");
            return;
        }

        // Construa a representa��o dos editais (por exemplo, em uma string)
        StringBuilder sb = new StringBuilder();
        for (Edital edital : listaEditais) {
            sb.append(edital.toString());
            sb.append("\n\n");
        }

        // Exiba os editais em uma caixa de di�logo
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Editais", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Retorna uma lista de editais abertos, ou seja, editais cujo per�odo de inscri��o est� em andamento.
     *
     * @return A lista de editais abertos.
     */
    public List<Edital> getEditaisAbertos() {
        // Cria uma nova lista para armazenar os editais abertos
        List<Edital> editaisAbertos = new ArrayList<>();

        // Obt�m a data atual
        LocalDate dataAtual = LocalDate.now();

        // Percorre a lista de editais
        for (Edital edital : listaEditais) {
            // Converte as datas de in�cio e fim de inscri��o do edital para o tipo LocalDate
            LocalDate dataInicio = LocalDate.parse(edital.getDataInicioInscricao());
            LocalDate dataFim = LocalDate.parse(edital.getDataFimInscricao());

            // Verifica se a data atual est� entre a data de in�cio e a data de fim de inscri��o do edital
            if (dataAtual.isAfter(dataInicio) && dataAtual.isBefore(dataFim)) {
                // Adiciona o edital � lista de editais abertos
                editaisAbertos.add(edital);
            }
        }

        // Retorna a lista de editais abertos
        return editaisAbertos;
    }
    
    /**
     * Lista os editais abertos para inscri��o na data atual.
     */
    public void listarEditaisAbertos() {
        // Obt�m a lista de editais abertos
        List<Edital> editaisAbertos = getEditaisAbertos();

        // Verifique se existem editais abertos
        if (editaisAbertos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "N�o existem editais abertos para inscri��o na data atual.");
            return;
        }

        // Construa a representa��o dos editais abertos (por exemplo, em uma string)
        StringBuilder sb = new StringBuilder();
        for (Edital edital : editaisAbertos) {
            sb.append(edital.toString());
            sb.append("\n\n");
        }

        // Exiba os editais abertos em uma caixa de di�logo
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Editais Abertos", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Busca um edital pelo nome.
     *
     * @param nomeEdital O nome do edital a ser buscado.
     * @return O edital encontrado, ou null caso n�o seja encontrado.
     */
    public Edital buscarEditalPorNome(String nomeEdital) {
        // Percorre a lista de editais
        for (Edital edital : listaEditais) {
            // Verifica se o nome do edital � igual ao nome fornecido (ignorando mai�sculas e min�sculas)
            if (edital.getNome().equalsIgnoreCase(nomeEdital)) {
                // Retorna o edital encontrado
                return edital;
            }
        }

        // Caso nenhum edital seja encontrado, retorna null
        return null; 
    }
    
    /**
     * Remove um edital.
     * Solicita ao usu�rio o nome do edital a ser removido, busca o edital correspondente e realiza a remo��o.
     * Exibe mensagens de confirma��o e resultado da opera��o.
     */
    public void removerEdital() {
        // Solicita ao usu�rio o nome do edital a ser removido
        String nomeEdital = JOptionPane.showInputDialog(null, "Digite o nome do edital que deseja remover:");

        // Busca o edital pelo nome fornecido
        Edital edital = buscarEditalPorNome(nomeEdital);

        if (edital != null) {
            // Confirma��o da remo��o com o usu�rio
            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o edital:\n" + edital.toString(), "Confirma��o de Remo��o", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                // Remove o edital utilizando o EditalController
                removerEdital(edital);
                JOptionPane.showMessageDialog(null, "Edital removido com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Edital n�o encontrado!");
        }
    }
    
    /**
     * Realiza a pesquisa de um edital.
     * Solicita ao usu�rio o nome do edital a ser pesquisado e exibe o resultado da pesquisa.
     * Caso o edital seja encontrado, exibe os detalhes do edital.
     * Caso contr�rio, exibe uma mensagem informando que o edital n�o foi encontrado.
     */
    public void pesquisarEdital() {
        // Solicita ao usu�rio o nome do edital a ser pesquisado
        String nomeEdital = JOptionPane.showInputDialog(null, "Digite o nome do edital que deseja pesquisar:");

        // Busca o edital pelo nome fornecido
        Edital edital = buscarEditalPorNome(nomeEdital);

        if (edital != null) {
            // Exibe os detalhes do edital encontrado
            JOptionPane.showMessageDialog(null, edital.toString(), "Edital Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Exibe uma mensagem informando que o edital n�o foi encontrado
            JOptionPane.showMessageDialog(null, "Edital n�o encontrado!", "Edital n�o Encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Realiza a edi��o de um edital existente.
     * Solicita ao usu�rio o nome do edital a ser editado e exibe um painel com os campos de texto preenchidos
     * com as informa��es atuais do edital. Permite ao usu�rio editar as informa��es do edital e salvar as altera��es.
     * Caso o edital seja encontrado e as altera��es sejam confirmadas, as informa��es do edital s�o atualizadas.
     * Caso contr�rio, exibe uma mensagem informando que o edital n�o foi encontrado.
     */
    public void editarEdital() {
        // Solicita ao usu�rio o nome do edital a ser editado
        String nomeEdital = JOptionPane.showInputDialog(null, "Digite o nome do edital a ser editado:");

        // Busca o edital pelo nome fornecido
        Edital edital = buscarEditalPorNome(nomeEdital);

        if (edital != null) {
            // Cria um painel para a edi��o do edital
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(7, 2)); // Layout com 7 linhas e 2 colunas

            // Cria campos de texto preenchidos com as informa��es atuais do edital
            JTextField nomeField = new JTextField(edital.getNome());
            JTextField dataInicioField = new JTextField(edital.getDataInicioInscricao());
            JTextField dataFimField = new JTextField(edital.getDataFimInscricao());
            JTextField dataProvaField = new JTextField(edital.getDataProva());
            JTextField valorInscricaoField = new JTextField(Double.toString(edital.getValorInscricao()));
            JTextField escolaridadeField = new JTextField(edital.getEscolaridade());
            JTextField numVagasField = new JTextField(Integer.toString(edital.getNumVagas()));

            // Adiciona os campos de texto ao painel
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("Data de In�cio das Inscri��es:"));
            panel.add(dataInicioField);
            panel.add(new JLabel("Data de Fim das Inscri��es:"));
            panel.add(dataFimField);
            panel.add(new JLabel("Data da Prova:"));
            panel.add(dataProvaField);
            panel.add(new JLabel("Valor da Inscri��o:"));
            panel.add(valorInscricaoField);
            panel.add(new JLabel("Escolaridade:"));
            panel.add(escolaridadeField);
            panel.add(new JLabel("N�mero de Vagas:"));
            panel.add(numVagasField);

            // Exibe o JOptionPane com os campos de texto e bot�es OK/Cancelar
            int result = JOptionPane.showConfirmDialog(null, panel, "Editar Edital", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Atualiza as informa��es do edital com os valores fornecidos
                edital.setNome(nomeField.getText());
                edital.setDataInicioInscricao(dataInicioField.getText());
                edital.setDataFimInscricao(dataFimField.getText());
                edital.setDataProva(dataProvaField.getText());
                edital.setValorInscricao(Double.parseDouble(valorInscricaoField.getText()));
                edital.setEscolaridade(escolaridadeField.getText());
                edital.setNumVagas(Integer.parseInt(numVagasField.getText()));

                JOptionPane.showMessageDialog(null, "Edital editado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Edital n�o encontrado!");
        }
    }
    
    /**
     * Retorna a lista de editais.
     *
     * @return A lista de editais.
     */
    public List<Edital> getEditais() {
        return listaEditais;
    }
}