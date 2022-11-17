package modelo.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import modelo.cartas.*;
import modelo.enums.DescricaoDeCarta;
import modelo.enums.NomeDeEspaco;
import modelo.enums.TipoDeCarta;
import modelo.espacos.EstacaoDeMetro;
import modelo.espacos.Utilidade;

/**
 *  @author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class DeckDeCartas {

    private final TipoDeCarta tipo;
    private final Queue<Carta> cartas = new LinkedList<>();

    /**
     *  Faz a configuração do deck com as cartas em ordem aleatória
     * 
     *  @param tipo - tipo das cartas do deck
     */
    @SuppressWarnings("unchecked")
	public DeckDeCartas(TipoDeCarta tipo) {
    	this.tipo = tipo;
    	
        try {
            ArrayList<Carta> temp = new ArrayList<>();
            
	        if( tipo.equals( TipoDeCarta.COFRE ) ) {
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.COFRE_01, NomeDeEspaco.NITEROI) );
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.COFRE_02, NomeDeEspaco.RUA_OSCAR_FREIRE) );
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.COFRE_03, NomeDeEspaco.RUA_25_MARCO) );
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.COFRE_04, NomeDeEspaco.LEBLON) );
	            temp.add( new CartaDeMovimentoRelativo(DescricaoDeCarta.COFRE_05, 2) );
	            temp.add( new CartaDeMovimentoRelativo(DescricaoDeCarta.COFRE_06, -4) );
	            temp.add( new CartaDeMovimentoEspecial(DescricaoDeCarta.COFRE_07, (Class<Utilidade>) Class.forName("modelo.espacos.Utilidade")) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_08, 100) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_09, 10) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_10, 200) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_11, 25) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_12, -75) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_13, -100) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_14, 100) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.COFRE_15, -45) );
	            temp.add( new CartaVaParaCadeia(DescricaoDeCarta.COFRE_16) );
	        }
	        else {
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.SORTE_01, NomeDeEspaco.PONTO_PARTIDA) );
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.SORTE_02, NomeDeEspaco.JARDIM_BOTANICO) );
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.SORTE_03, NomeDeEspaco.AV_PRES_JUSCELINO) );
	            temp.add( new CartaDeMovimentoAbsoluto(DescricaoDeCarta.SORTE_04, NomeDeEspaco.PRACA_SE) );
	            temp.add( new CartaDeMovimentoRelativo(DescricaoDeCarta.SORTE_05, 5) );
	            temp.add( new CartaDeMovimentoRelativo(DescricaoDeCarta.SORTE_06, -3) );
	            temp.add( new CartaDeMovimentoEspecial(DescricaoDeCarta.SORTE_07, (Class<EstacaoDeMetro>) Class.forName("modelo.espacos.EstacaoDeMetro")) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_08, -50) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_09, 75) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_10, -50) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_11, 100) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_12, 50) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_13, 150) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_14, -40) );
	            temp.add( new CartaDeDinheiro(DescricaoDeCarta.SORTE_15, 200) );
	            temp.add( new CartaVaParaCadeia(DescricaoDeCarta.SORTE_16) );
	        }
	        
	        Random generator = new Random();

	        while( !temp.isEmpty() )
	            cartas.add( temp.remove( generator.nextInt( temp.size() ) ) );
        }
        catch(ClassNotFoundException e) {
        	e.printStackTrace();
        }
    }

    /**
     *  @return Tipo das cartas do deck
     */
    public TipoDeCarta getTipo() {
        return tipo;
    }

    /**
     *  Retira uma carta do topo do deck e a coloca no final dele
     * 
     *  @return Carta retirada do deck
     */
    public Carta retirar() {
        Carta retorno = cartas.poll();
        cartas.add(retorno);
        return retorno;
    }

}
