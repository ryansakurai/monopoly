package modelo.entidades;

import java.util.ArrayList;

import modelo.enums.CorDeLote;
import modelo.enums.NomeDeEspaco;
import modelo.enums.TipoDeCarta;
import modelo.espacos.*;

/**
 *  Tabuleiro do jogo, que também é responsável pela movimentação do jogador nele.
 
 * 	@author Caike Vinicius dos Santos  - 802629
 * 	@author Guilherme Campos Bortoletto - 801477
 * 	@author Marcelo Pirro - 800510
 * 	@author Ryan Guerra Sakurai - 802639
 * 	@author Vinicius Silva Castro - 802138
 */
public class Tabuleiro {

    private final ArrayList<Espaco> espacos;
    private final ArrayList<Lote> lotesMarrons;
    private final ArrayList<Lote> lotesAzuisClaros;
    private final ArrayList<Lote> lotesRosas;
    private final ArrayList<Lote> lotesLaranjas;
    private final ArrayList<Lote> lotesVermelhos;
    private final ArrayList<Lote> lotesAmarelos;
    private final ArrayList<Lote> lotesVerdes;
    private final ArrayList<Lote> lotesAzuis;

    /**
     *  Faz a configuração inicial dos espaços do tabuleiro
     *  e o agrupamento dos lotes por cor.
     */
    public Tabuleiro() {
        espacos = new ArrayList<>();
        espacos.add( new PontoDePartida(NomeDeEspaco.PONTO_PARTIDA) );
        espacos.add( new Lote(NomeDeEspaco.AV_SUMARE, 60, CorDeLote.MARROM, 50, 2, 10) );
        espacos.add( new EspacoDeCarta(NomeDeEspaco.COFRE, TipoDeCarta.COFRE) );
        espacos.add( new Lote(NomeDeEspaco.PRACA_SE, 60, CorDeLote.MARROM, 50, 4, 20) );
        espacos.add( new ImpostoDeRenda(NomeDeEspaco.IMPOSTO) );
        espacos.add( new EstacaoDeMetro(NomeDeEspaco.METRO_MARACANA, 200) );
        espacos.add( new Lote(NomeDeEspaco.RUA_25_MARCO, 100, CorDeLote.AZUL_CLARO, 50, 6, 30) );
        espacos.add( new EspacoDeCarta(NomeDeEspaco.SORTE, TipoDeCarta.SORTE) );
        espacos.add( new Lote(NomeDeEspaco.AV_SAO_JOAO, 100, CorDeLote.AZUL_CLARO, 50, 6, 30) );
        espacos.add( new Lote(NomeDeEspaco.AV_PAULISTA, 120, CorDeLote.AZUL_CLARO, 50, 8, 40) );
        espacos.add( new Cadeia(NomeDeEspaco.CADEIA) );
        espacos.add( new Lote(NomeDeEspaco.AV_VIEIRA_SOUTO, 140, CorDeLote.ROSA, 100, 10, 50) );
        espacos.add( new Utilidade(NomeDeEspaco.COMP_ELETRICA, 150) );
        espacos.add( new Lote(NomeDeEspaco.NITEROI, 140, CorDeLote.ROSA, 100, 10, 50) );
        espacos.add( new Lote(NomeDeEspaco.AV_ATLANTICA, 160, CorDeLote.ROSA, 100, 12, 60) );
        espacos.add( new EstacaoDeMetro(NomeDeEspaco.METRO_CARIOCA, 200) );
        espacos.add( new Lote(NomeDeEspaco.AV_PRES_JUSCELINO, 180, CorDeLote.LARANJA, 100, 14, 70) );
        espacos.add( new EspacoDeCarta(NomeDeEspaco.COFRE, TipoDeCarta.COFRE) );
        espacos.add( new Lote(NomeDeEspaco.AV_ENG_LUIS, 180, CorDeLote.LARANJA, 100, 14, 70) );
        espacos.add( new Lote(NomeDeEspaco.AV_BRIGADEIRO, 200, CorDeLote.LARANJA, 100, 16, 80) );
        espacos.add( new EstacionamentoGratis(NomeDeEspaco.ESTACIONAMENTO) );
        espacos.add( new Lote(NomeDeEspaco.IPANEMA, 220, CorDeLote.VERMELHO, 150, 18, 90) );
        espacos.add( new EspacoDeCarta(NomeDeEspaco.SORTE, TipoDeCarta.SORTE) );
        espacos.add( new Lote(NomeDeEspaco.LEBLON, 220, CorDeLote.VERMELHO, 150, 18, 90) );
        espacos.add( new Lote(NomeDeEspaco.COPACABANA, 240, CorDeLote.VERMELHO, 150, 20, 100) );
        espacos.add( new EstacaoDeMetro(NomeDeEspaco.METRO_CONSOLACAO, 200) );
        espacos.add( new Lote(NomeDeEspaco.AV_CIDADE_JARDIM, 260, CorDeLote.AMARELO, 150, 22, 110) );
        espacos.add( new Lote(NomeDeEspaco.PACAEMBU, 260, CorDeLote.AMARELO, 150, 22, 110) );
        espacos.add( new Utilidade(NomeDeEspaco.COMP_AGUA, 150) );
        espacos.add( new Lote(NomeDeEspaco.IBIRAPUERA, 280, CorDeLote.AMARELO, 150, 24, 120) );
        espacos.add( new VaParaCadeia(NomeDeEspaco.VA_PARA_CADEIA) );
        espacos.add( new Lote(NomeDeEspaco.BARRA_DA_TIJUCA, 300, CorDeLote.VERDE, 200, 26, 130) );
        espacos.add( new Lote(NomeDeEspaco.JARDIM_BOTANICO, 300, CorDeLote.VERDE, 200, 26, 130) );
        espacos.add( new EspacoDeCarta(NomeDeEspaco.COFRE, TipoDeCarta.COFRE) );
        espacos.add( new Lote(NomeDeEspaco.LAGOA_RODRIGO, 320, CorDeLote.VERDE, 200, 28, 150) );
        espacos.add( new EstacaoDeMetro(NomeDeEspaco.METRO_REPUBLICA, 200) );
        espacos.add( new EspacoDeCarta(NomeDeEspaco.SORTE, TipoDeCarta.SORTE) );
        espacos.add( new Lote(NomeDeEspaco.AV_MORUMBI, 350, CorDeLote.AZUL, 200, 35, 175) );
        espacos.add( new TaxaDeRiqueza(NomeDeEspaco.TAXA_RIQUEZA) );
        espacos.add( new Lote(NomeDeEspaco.RUA_OSCAR_FREIRE, 400, CorDeLote.AZUL, 200, 50, 200) );
    
        lotesMarrons = new ArrayList<Lote>();
        lotesMarrons.add( (Lote) espacos.get(1) );
        lotesMarrons.add( (Lote) espacos.get(3) );

        lotesAzuisClaros = new ArrayList<Lote>();
        lotesAzuisClaros.add( (Lote) espacos.get(6) );
        lotesAzuisClaros.add( (Lote) espacos.get(8) );
        lotesAzuisClaros.add( (Lote) espacos.get(9) );

        lotesRosas = new ArrayList<Lote>();
        lotesRosas.add( (Lote) espacos.get(11) );
        lotesRosas.add( (Lote) espacos.get(13) );
        lotesRosas.add( (Lote) espacos.get(14) );

        lotesLaranjas = new ArrayList<Lote>();
        lotesLaranjas.add( (Lote) espacos.get(16) );
        lotesLaranjas.add( (Lote) espacos.get(18) );
        lotesLaranjas.add( (Lote) espacos.get(19) );

        lotesVermelhos = new ArrayList<Lote>();
        lotesVermelhos.add( (Lote) espacos.get(21) );
        lotesVermelhos.add( (Lote) espacos.get(23) );
        lotesVermelhos.add( (Lote) espacos.get(24) );

        lotesAmarelos = new ArrayList<Lote>();
        lotesAmarelos.add( (Lote) espacos.get(26) );
        lotesAmarelos.add( (Lote) espacos.get(27) );
        lotesAmarelos.add( (Lote) espacos.get(29) );

        lotesVerdes = new ArrayList<Lote>();
        lotesVerdes.add( (Lote) espacos.get(31) );
        lotesVerdes.add( (Lote) espacos.get(32) );
        lotesVerdes.add( (Lote) espacos.get(34) );

        lotesAzuis = new ArrayList<Lote>();
        lotesAzuis.add( (Lote) espacos.get(37) );
        lotesAzuis.add( (Lote) espacos.get(39) );
    }
    
    /**
     * 	@param cor - cor dos lotes a serem retornados
     *  @return Lista com todos os lotes da cor especificada
     */
    public ArrayList<Lote> getLotes(CorDeLote cor) {
    	ArrayList<Lote> lotes;
    	
    	if(cor == CorDeLote.AMARELO)
    		lotes = lotesAmarelos;
    	else if(cor == CorDeLote.AZUL)
    		lotes = lotesAzuis;
    	else if(cor == CorDeLote.AZUL_CLARO)
    		lotes = lotesAzuisClaros;
    	else if(cor == CorDeLote.LARANJA)
    		lotes = lotesLaranjas;
    	else if(cor == CorDeLote.MARROM)
    		lotes = lotesMarrons;
    	else if(cor == CorDeLote.ROSA)
    		lotes = lotesRosas;
    	else if(cor == CorDeLote.VERDE)
    		lotes = lotesVerdes;
    	else
    		lotes = lotesVermelhos;
    	
    	return new ArrayList<>(lotes);
    }

    /**
     * 	@return Espaço cujo nome é o dado
     */
    public Espaco getEspaco(NomeDeEspaco nome) {
    	int i;
    	
    	for(i=0; i < espacos.size(); i++)
    		if(espacos.get(i).getNome() == nome)
    			break;
    	
    	return espacos.get(i);
    }
    
    /**
     * 	Avança o jogador para um espaço específico
     * 
     * 	@return Se o jogador deu uma volta no tabuleiro
     */
    public boolean avancarPara(Jogador j, NomeDeEspaco destino) {
    	if(j.getPosicao() == null)
    		j.setPosicao( getEspaco(NomeDeEspaco.PONTO_PARTIDA) );
    	
    	boolean deuVolta = false;
    	
    	while( j.getPosicao().getNome() != destino )
    		if( avancar(j, 1) )
    			deuVolta = true;
    	
    	return deuVolta;
    }
    
    /**
     * 	Avança o jogador para o próximo espaço de um certo tipo
     * 
     * 	@return Se o jogador deu uma volta no tabuleiro
     */
    public boolean avancarParaOProximo(Jogador j, Class<? extends Espaco> tipoDoDestino) {
    	if(j.getPosicao() == null)
    		j.setPosicao( getEspaco(NomeDeEspaco.PONTO_PARTIDA) );
    	
    	boolean deuVolta = false;
    	
    	while( !j.getPosicao().getClass().equals(tipoDoDestino) )
    		if( avancar(j, 1) )
    			deuVolta = true;
    	
    	return deuVolta;
    }

    /**
     *  Avança o jogador em uma quantidade de posicões no tabuleiro
     * 
     *  @param j
     *  @param qt - quantidade de casas a serem avançadas
     *  @return Se o jogador deu uma volta no tabuleiro
     */
    public boolean avancar(Jogador j, int qt) {
    	if(qt < 0)
    		throw new IllegalArgumentException("Um jogador não pode avançar uma quantidade negativa de casas!");
    	
    	if(j.getPosicao() == null)
    		j.setPosicao( getEspaco(NomeDeEspaco.PONTO_PARTIDA) );
    	
        int indiceJogador = espacos.indexOf( j.getPosicao() );

        boolean deuVolta = false;

        for(int i=0; i<qt; i++) {
            indiceJogador++;

            //pois o tabuleiro é circular
            if(indiceJogador == espacos.size()) {
                indiceJogador = 0;
                deuVolta = true;
            }
        }

        moverPara(j, indiceJogador);

        return deuVolta;
    }

    /**
     *  Volta o jogador em uma quantidade de posicões no tabuleiro
     * 
     *  @param j
     *  @param qt - quantidade de casas a serem voltadas
     */
    public void voltar(Jogador j, int qt) {
    	if(qt < 0)
    		throw new IllegalArgumentException("Um jogador não pode voltar uma quantidade negativa de casas!");
    	
    	if(j.getPosicao() == null)
    		j.setPosicao( getEspaco(NomeDeEspaco.PONTO_PARTIDA) );
    	
        int indiceJogador = espacos.indexOf( j.getPosicao() );

        for(int i=0; i<qt; i++) {
            indiceJogador--;

            //pois o tabuleiro é circular
            if(indiceJogador == -1)
                indiceJogador = 39;
        }

        moverPara(j, indiceJogador);
    }
    
    /**
     *  Move o jogador para uma posição específica no tabuleiro
     */
    private void moverPara(Jogador j, int posicao) {
        j.setPosicao( espacos.get(posicao) );
    }

}
