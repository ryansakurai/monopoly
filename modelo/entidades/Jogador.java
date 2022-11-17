package modelo.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import modelo.cartas.Carta;
import modelo.enums.CorDeLote;
import modelo.espacos.Espaco;
import modelo.espacos.EstacaoDeMetro;
import modelo.espacos.Lote;
import modelo.espacos.Propriedade;
import modelo.espacos.Utilidade;

/**
 *  @author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class Jogador {

    private final String nome;
    private final ArrayList<Propriedade> propriedades = new ArrayList<>();
    private final ArrayList<CorDeLote> monopolios = new ArrayList<>();
    private final Queue<Boolean> ultimosLancamentos = new LinkedList<>();
    private Integer primeiroResultado;
    private int saldo = 1500;
    private Espaco posicao;
    private boolean preso;
    private boolean faliu;
    private int qtDeRodadasPreso;

    /**
     *  Inicia o jogador com 1500 de saldo
     * 
     *  @param nome - nome do jogador
     */
    public Jogador(String nome) {
        this.nome = nome;
    }

    /**
     *  @return Cópia da lista de propriedades do jogador
     */
    public ArrayList<Propriedade> getPropriedades() {
    	return new ArrayList<>(propriedades);
    }
    
    /**
     * 	@return Se a propriedade foi adicionada
     */
    public boolean adicionarPropriedade(Propriedade p) {
        return propriedades.add(p);
    }
    
    /**
     * 	@return Se a propriedade foi removida
     */
    public boolean removerPropriedade(Propriedade p) {
        return propriedades.remove(p);
    }
    
    /**
     * 	@param indice - índice no vetor da propriedade a ser removida
     * 	@return Propriedade removida
     */
    public Propriedade removerPropriedade(int indice) {
        return propriedades.remove(indice);
    }

    /**
     *  @return Cópia da lista de lotes do jogador
     */
    public ArrayList<Lote> getLotes() {
        ArrayList<Lote> lotes = new ArrayList<>();

        for(Propriedade x : propriedades)
            if(x instanceof Lote)
                lotes.add( (Lote) x );

        return lotes;
    }
    
    /**
     * 	@param cor - cor dos lotes a serem retornados
     *  @return Cópia da lista de lotes do jogador da cor especificada
     */
    public ArrayList<Lote> getLotes(CorDeLote cor) {
        ArrayList<Lote> lotes = new ArrayList<>();

        for(Propriedade x : propriedades)
            if(x instanceof Lote) {
            	Lote lote = (Lote) x;
            	if(lote.getCor() == cor)
            		lotes.add(lote);
            }

        return lotes;
    }

    /**
     *  @return Cópia da lista de utilidades do jogador
     */
    public ArrayList<Utilidade> getUtilidades() {
        ArrayList<Utilidade> utilidades = new ArrayList<>();

        for(Propriedade x : propriedades)
            if(x instanceof Utilidade)
                utilidades.add( (Utilidade) x );

        return utilidades;
    }

    /**
     *  @return Cópia da lista de estações de metrô do jogador
     */
    public ArrayList<EstacaoDeMetro> getEstacoes() {
        ArrayList<EstacaoDeMetro> estacoes = new ArrayList<>();

        for(Propriedade x : propriedades)
            if(x instanceof EstacaoDeMetro)
                estacoes.add( (EstacaoDeMetro) x );

        return estacoes;
    }

    /**
     *  @return resultado do lançamento do dado
     */
    public int lancarDado(Dado dado) {
        int resultado = dado.lancar();

        //esse é o primeiro dado do lançamento
        if(primeiroResultado == null) {
            primeiroResultado = resultado;
        }
        //esse é o segundo dado do lançamento
        else {
            adicionarLancamento(primeiroResultado == resultado);
            primeiroResultado = null;
        }

        return resultado;
    }

    /**
     *  Adiciona o dado de um lançamento ao histórico de duplas do ultimosLancamentos 
     * 
     *  @param deuDupla - se o ultimo lançamento deu dupla
     */
    private void adicionarLancamento(boolean deuDupla) {
        ultimosLancamentos.add(deuDupla);

        //a fila terá sempre no máximo os últimos 3 lançamentos
        while(ultimosLancamentos.size() > 3)
            ultimosLancamentos.remove();
    }

    /**
     *  Limpa o histórico de duplas das 3 ultimas jogadas do jogador
     */
    public void limparHistoricoDeDuplas() {
        ultimosLancamentos.clear();
    }

    /**
     *  Informa se os três ultimos lançamentos deram dupla e, em caso positivo,
     *  limpa o histórico de duplas
     */
    public boolean deuTresDuplas() {
        if(ultimosLancamentos.size() < 3)
            return false;

        for(boolean x : ultimosLancamentos)
            if(x == false)
                return false;

        limparHistoricoDeDuplas();

        return true;
    }

    public int getSaldo() {
        return saldo;
    }

    /**
     *  @param qt - quantidade de saldo para aumentar
     */
    public void aumentarSaldo(int qt) {
    	if(qt >= 0)
    		this.saldo += qt;
    	else
			throw new IllegalArgumentException("Quantidade negativa!");
    }

    /**
     *  @param qt - quantidade de saldo para diminuir
     */
    public void diminuirSaldo(int qt) {
    	if(qt >= 0)
    		this.saldo -= qt;
    	else
			throw new IllegalArgumentException("Quantidade negativa!");
    }

    public Espaco getPosicao() {
        return posicao;
    }

    public void setPosicao(Espaco posicao) {
        this.posicao = posicao;
    }

    public boolean isPreso() {
        return preso;
    }

    /**
     *  Coloca o jogador como preso
     */
    public void prender() {
        preso = true;
    }

    /**
     *  Coloca o jogador como livre e sua quantidade de rodadas preso
     */
    public void soltar() {
        preso = false;
        qtDeRodadasPreso = 0;
        limparHistoricoDeDuplas();
    }

    public boolean faliu() {
		return faliu;
	}

	public void falir() {
		this.faliu = true;
	}

	public int getQtDeRodadasPreso() {
        return qtDeRodadasPreso;
    }

    /**
     *  Aumenta em 1 unidade o número de rodadas preso
     */
    public void aumentarQtDeRodadasPreso() {
        qtDeRodadasPreso++;
    }

    /**
     * 	@return Cópia da lista de monopólios do jogador
     */
    public ArrayList<CorDeLote> getMonopolios() {
        return new ArrayList<>(monopolios);
    }
    
    /**
     * 	@return Quantidade de monopólios do jogador
     */
    public int getQtDeMonopolios() {
    	return monopolios.size();
    }

    /**
     * 	@param cor - cor dos lotes sob monopólio
     * 	@return Se o monopólio foi adicionado com sucesso
     */
    public boolean adicionarMonopolio(CorDeLote cor) {
        return monopolios.add(cor);
    }
    
    /**
     * 	@param cor - cor dos lotes sob monopólio
     * 	@return Se o monopólio foi removido com sucesso
     */
    public boolean removerMonopolio(CorDeLote cor) {
    	return monopolios.remove(cor);
    }
    
    /**
     * 	@return Se o jogador tem monopólio sobre os lotes da cor informada
     */
    public boolean temMonopolio(CorDeLote cor) {
    	return monopolios.contains(cor);
    }

    /**
     *  @return Carta retiradas do ceck
     */
    public Carta retirarCarta(DeckDeCartas deck) {
        return deck.retirar();
    }

    /**
     * 	@return Nome do jogador
     */
    @Override
    public String toString() {
        return nome;
    }

}
