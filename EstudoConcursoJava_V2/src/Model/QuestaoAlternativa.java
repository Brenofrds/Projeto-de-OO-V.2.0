/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Model;

import java.util.List;

/**
 * Classe que representa uma quest�o de m�ltipla escolha.
 *
 * Essa classe herda os atributos e m�todos da classe abstrata Questao e adiciona um array para armazenar
 * as alternativas da quest�o e um char para armazenar a resposta correta.
 */
public class QuestaoAlternativa extends Questao {
    private String[] alternativas; // Array de alternativas da quest�o
    private char resposta; // Resposta correta da quest�o (representada por um caractere)

    /**
     * Construtor da classe QuestaoAlternativa.
     *
     * @param questao       O texto da quest�o.
     * @param materia       A mat�ria � qual a quest�o pertence.
     * @param numQuestao    O n�mero da quest�o.
     * @param alternativas  O array de alternativas da quest�o.
     * @param resposta      A resposta correta da quest�o (representada por um caractere).
     */
    public QuestaoAlternativa(String questao, String materia, int numQuestao, String[] alternativas, char resposta) {
        super(questao, materia, numQuestao);
        this.alternativas = alternativas;
        this.resposta = resposta;
    }
    
    /**
     * Retorna uma representa��o em formato de string da quest�o.
     *
     * @return A representa��o da quest�o em formato de string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quest�o de M�ltipla Escolha\n");
        sb.append("Quest�o: ").append(getQuestao()).append("\n");
        sb.append("Mat�ria: ").append(getMateria()).append("\n");
        sb.append("Alternativas:\n");
        for (int i = 0; i < alternativas.length; i++) {
            sb.append((char) ('A' + i)).append(") ").append(alternativas[i]).append("\n");
        }
        sb.append("Resposta Correta: ").append(resposta).append("\n");
        return sb.toString();
    }
    
    //metodos especiais
    public String[] getAlternativas() {
        return alternativas;
    }
    
    public void setAlternativas(List<String> opcoes) {
 		this.alternativas = opcoes.toArray(new String[0]);
 	}

    public char getResposta() {
        return resposta;
    }
    
    public void setResposta(char resposta) {
        this.resposta = resposta;
    }

    public String getMateria() {
        return super.materia;
    }
    
    public int getNumQuestao() {
    	return super.numQuestao;	
    }
    
    public void setQuestao(String questao) {
        super.questao = questao;
    }
}