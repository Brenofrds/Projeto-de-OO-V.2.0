/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Model;

/**
 * Classe abstrata que representa uma quest�o.
 * 
 * Essa classe cont�m os atributos que armazenam os dados de uma quest�o e m�todos
 * de acesso para manipular esses dados. A classe � abstrata, pois n�o pode ser instanciada
 * diretamente, sendo necess�ria a implementa��o de subclasses (QuestaoAlternativa e QuestaoVF) 
 * para criar tipos espec�ficos de quest�es.
 */
public abstract class Questao {
    // Atributos
    protected String questao; // Texto da quest�o
    protected String materia; // Mat�ria � qual a quest�o pertence
    protected int numQuestao; // N�mero da quest�o

    /**
     * Construtor da classe Questao.
     *
     * @param questao    O texto da quest�o.
     * @param materia    A mat�ria � qual a quest�o pertence.
     * @param numQuestao O n�mero da quest�o.
     */
    public Questao(String questao, String materia, int numQuestao) {
        this.questao = questao;
        this.materia = materia;
        this.numQuestao = numQuestao;
    }
    
    /**
     * Retorna uma representa��o em formato de string da quest�o.
     *
     * @return A representa��o da quest�o em formato de string.
     */
    @Override
    public abstract String toString();
    
    // M�todos Especiais
    public String getQuestao() {
        return questao;
    }
    
    public void setQuestao(String questao) {
    	this.questao = questao;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }

}