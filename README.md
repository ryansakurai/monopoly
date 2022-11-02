# Monopoly
Versão simplificada do jogo de tabuleiro Monopoly feita como trabalho para a matéria de Programação Orientada a Objetos. Monopoly é um jogo de compra e venda de propriedades em que o objetivo é conseguir monopólios e fazer outros jogadores irem à falência.

## Regras
- De 2 a 4 jogadores;
- A ordem dos jogadores é definida por meio do lançamento de dados;
- Todos os jogadores começam com $1500;
- O jogo possui um tabuleiro linear e circular, sendo que a movimentação dos jogadores é definida pela soma do resultado do lançamento de dois dados;
- Se o jogador tirar dupla (dois dados iguais) no lançamento de dados, ele jogará de novo na próxima rodada.
- Se o saldo do jogador chegar ao negativo, ele chega à falência, tendo que entregar todas as propriedades as banco e ser removido do jogo. Se isso ocorrer durante o pagamento a outro jogadores, suas propriedades vão para sua posse. Todas as construções são destruídas nesse processo.

## Espaços
### Ponto de Partida
É onde os jogadores começam. Sempre que o jogador der uma volta completa no tabuleiro e passar por este espaço novamente, ele ganha $200.

### Taxa de Riqueza & Imposto de Renda
Fazem com que o jogador pague uma quantidade de dinheiro. No caso da taxa de riqueza, o valor é fixo e, no caso do imposto de renda, o jogador pode escolher entre pagar um valor fixo ou uma porcentagem de sua fortuna (dinheiro, propriedades e construções).

### Espaço de Carta
Fazem o jogador tirar uma carta de um dos deques do jogo (cofre e sorte), que são aleatorizados e ditam uma ação que o jogador deve tomar.

### Propriedades
Espaços que podem ser comprados e negociados pelos jogadores e têm um aluguel a ser pago pelos jogadores que caem neles ao proprietário. Possuem três tipos:
- Lote: possuem uma cor. Quando todos os lotes de uma cor pertencerem ao mesmo jogador, ele ganha o monopólio daquela cor, fazendo com que o aluguel desses lotes dobre. Quando o monopólio é conquistado, o jogador pode construir uma casa nos seus lotes (que aumenta o aluguel) e, quando todos tiverem uma casa, um hotel que substituirá a casa.
- Utilidade: agrupa a companhia elétrica e a de distribuição de água. O aluguel depende do valor tirado nos dados e da quantidade de utilidades que o jogador possui.
- Estação de Metrô: o aluguel depende da quantidade de estações que o jogador possui.

Em seu turno, o jogador poderá fazer quantas ofertas pela propriedade alheia ele quiser. Se uma oferta sobre um lote com monopólio for aceita, o jogador perde o monopólio e também a construção (caso tenha).

### Vá para a Cadeia
Faz com que o jogador sejá preso.

### Cadeia
Onde os jogadores ficam quando estão presos (se um jogador livre cair nesse espaço, nada acontece).

### Estacionamento Grátis
Nada acontece nesse espaço

## Prisão
O jogador pode ser preso caso:
- Tire três duplas seguidas;
- Tire a carta "vá para a cadeia";
- Caia no espaço "vá para a cadeia".

Enquanto estiver preso, ele não pode receber aluguéis e, para conseguir soltura:
- 1ª rodada preso: pode pagar fiança ou tentar tirar dupla;
- 2ª rodada preso: pode pagar fiança ou tentar tirar dupla;
- 3ª rodada preso: deverá tentar tirar dupla;
- 4ª rodada preso: deverá pagar fiança (se não for possível, o jogador falirá).

## Vitória
O jogador é declarado o vencedor caso:
- todos os outros falirem;
- conseguir dois monopólios;
- conseguir construir um hotel.

## Autores
- [Caike dos Santos](https://github.com/CaikeSantos)
- [Guilherme Bortoletto](https://github.com/guilherme-bortoletto)
- [Marcelo Pirro](https://github.com/marcelopirro)
- [Ryan Sakurai](https://github.com/ryansakurai)
- [Vinicius Castro](https://github.com/vinciuscastro)

(Estudantes de Ciência da Computação na UFSCAR)
