/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Model;

/**
 * Classe que representa uma quest�o do tipo Verdadeiro ou Falso.
 *
 * Essa classe herda os atributos e m�todos da classe abstrata Questao e adiciona um atributo
 * para armazenar a resposta correta da quest�o.
 */
public class QuestaoVF extends Questao {
    private boolean respostaCorreta; // Indica se a resposta correta � verdadeira (true) ou falsa (false)

    /**
     * Construtor da classe QuestaoVF.
     *
     * @param questao         O texto da quest�o.
     * @param materia         A mat�ria � qual a quest�o pertence.
     * @param numQuestao      O n�mero da quest�o.
     * @param respostaCorreta A resposta correta da quest�o (true para verdadeira, false para falsa).
     */
    public QuestaoVF(String questao, String materia, int numQuestao, boolean respostaCorreta) {
        super(questao, materia, numQuestao);
        this.respostaCorreta = respostaCorreta;
    }
    /**
     * Retorna uma representa��o em formato de string da quest�o.
     *
     * @return A representa��o da quest�o em formato de string.
     */
    @Override
    public String toString() {
        return "Quest�o VF" +
                "\nQuest�o: " + getQuestao() +
                "\nMat�ria: " + getMateria() +
                "\nResposta Correta: " + (respostaCorreta ? "Verdadeira" : "Falsa");
    }
    
    //Metodos especiais
    public boolean getRespostaCorreta() {
        return respostaCorreta;
    }
    
    public void setRespostaCorreta(boolean respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

    public String getQuestao() {
        return super.questao;
    }
    
    public void setQuestao(String questao) {
        super.questao = questao;
    }

    public String getMateria() {
        return super.materia;
    }
    
    public int getNumQuestao() {
    	return super.numQuestao;
    }
}