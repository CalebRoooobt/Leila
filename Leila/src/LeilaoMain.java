/*Como está presente a agregação e composição nessa classe? Ex:

A classe LeilaoMain possui uma lista de LoteMain (ArrayList) chamada lotes.
Essa lista representa uma relação de agregação, onde um leilão contém vários lotes.
 Os lotes podem existir independentemente do leilão.

 A classe LeilaoMain possui um atributo proximoNumeroDeLote,
  sendo um número inteiro utilizado para gerar o próximo número de lote.
  Esse atributo é específico do leilão sendo criado com a instância do objeto LeilaoMain.
  Portanto, há uma relação de composição, indicando que o número de lote é uma parte essencial do leilão.
 */
// Tarefa 01
import java.util.ArrayList;
public class LeilaoMain {
    private ArrayList<LoteMain> lotes;
    //A classe LeilaoMain possui uma lista de objetos LoteMainchamados lotes.
    private int proximoNumeroDeLote;

    public LeilaoMain() {
        lotes = new ArrayList<LoteMain>();
        proximoNumeroDeLote = 1;
        /*É o construtor da classe LeilaoMain que inicializa a
        lista de lotes vazia e define o valor inicial do próximo número de lote como 1.*/
    }
    //A lista lotes é inicializada no construtor da classe LeilaoMain e é inserida por meio do método insiraLote(), onde novos lotes são adicionados à lista.
    public void insiraLote(String descricao) {
        lotes.add(new LoteMain(proximoNumeroDeLote, descricao));
        proximoNumeroDeLote++;
        /* Cria um objeto LoteMain com o próximo número de lote disponível
         e a descrição fornecida como parâmetro. Em seguida, adiciona o lote à lista de lotes do leilão
          e incrementa o próximo número de lote.
         */
    }
    public void mostraLotes() {
        for (LoteMain lotes : lotes) {
            System.out.println(lotes.toString());
            /* Itera sobre todos os lotes na lista de lotes e imprime os detalhes de cada
             lote no formato definido pelo método toString() da classe LoteMain.
             */
        }
    }
    public void verificaLance(int numeroDoLote, PessoaMain licitante, long valor) {
        LoteMain selecionaLote = getLote(numeroDoLote);
        if (selecionaLote != null) {
            boolean sucesso = selecionaLote.verificaLance(new LanceMain(licitante, valor));
            if (sucesso) {
                System.out.println("A proposta para o lote número " + numeroDoLote + " foi bem sucedida.");
            } else {
                LanceMain maiorLance = selecionaLote.getMaiorLance();
                System.out.println("Número do lote: " + numeroDoLote + " já tem um lance de: " + maiorLance.getValor());
            }
        }
        /*Verifica se é possível fazer um lance para o lote com o número fornecido. Se o lote existir, verifica se o lance
         é válido chamando o método verificaLance() do objeto LoteMain. Se o lance for bem-sucedido, imprime uma mensagem
          informando que a proposta foi aceita. Caso contrário, imprime uma mensagem informando que já existe um
          lance maior para o lote.
         */
    }
    public LoteMain getLote(int numDoLote) {
        if ((numDoLote >= 1) && (numDoLote < proximoNumeroDeLote)) {
            LoteMain selecionaLote = lotes.get(numDoLote - 1);
            if (selecionaLote.getNumero() != numDoLote) {
                System.out.println(
                        "Erro interno: Lote número " + selecionaLote.getNumero() +
                                " foi devolvido ao invés de " + numDoLote);
                selecionaLote = null;
            }
            return selecionaLote;
        } else {
            System.out.println("Numero do lote: " + numDoLote + " não existe.");
            return null;
        }
        /*Retorna o objeto LoteMain correspondente ao número do lote fornecido.
         Primeiro, verifica se o número do lote está dentro dos limites válidos.
         */
    }
    // Tarefa 02
    public void close() {
        for (LoteMain lote : lotes){ //Ele vai iterar pela coleção de lotes
            String arrematador = "Não há lance";
            LanceMain maiorLance = lote.getMaiorLance();
            if (maiorLance != null) {
                arrematador = maiorLance.getLicitante().getNome();//Para lotes que não foram vendidos, imprima uma mensagem que indica este fato.
            }
            System.out.println("Lote.: " + lote.getNumero() + ", Arrematador.: " + arrematador + ", Valor.: "
                    + (maiorLance != null ? maiorLance.getValor() : 0)); // imprimir detalhes de todos os lotes.
        }
        /*O Close é método que percorre todos os lotes na lista de lotes do leilão. Para cada lote, ele verifica se há um maior lance
         registrado. Se tiver, ele recebe o arrematador desse lance usando o método getLicitante() do objeto LanceMain.
          Em seguida, imprime o número do lote, o arrematador e o valor do lance (ou 0 se não houver lance registrado).
           Também é usado para encerrar o leilão e exibir os detalhes dos arrematadores de cada lote.

           Basicamente, o método close() percorre todos os lotes e imprime informações sobre os arrematadores e os
            valores dos maiores lances registrados em cada lote. Ele é chamado quando o usuário escolhe a opção
            "Fechar leilão" no menu principal.
         */
    }
    public LanceMain getMaiorLance(){
        LanceMain maiorLance = null;// Inicializo maior lance a "Vazio"
        for (LoteMain lote : lotes) { // Esse método percorre todos os lotes na lista de lotes do leilão.
            LanceMain lance = lote.getMaiorLance(); // Para cada lote, obtém o maior lance registrado usando o método getMaiorLance() do objeto LoteMain.
            if (lance != null && (maiorLance == null || lance.getValor() > maiorLance.getValor())) { // Se o lance atual for maior que o maior lance registrado até o momento, ele atualiza o maior lance
                maiorLance = lance;
            }
        }
        return maiorLance;
        //No final, o método retorna o maior lance encontrado no leilão.

    }
    // Tarefa 03
    public String getLancesNaoVendidos(){
        ArrayList<LanceMain> lancesNaoVendidos = new ArrayList<>();
        //Cria um objeto `ArrayList chamado lancesNaoVendidos para armazenar os lançes não vendidas.
        for (LoteMain lote : lotes)
        // Itera sobre cada LoteMain presente na lista lotes.
        {
            LanceMain maiorLance = lote.getMaiorLance();
            //Para cada lote, obtém o maiorgetMaiorLance() e o armazena em uma variável chamada maiorLance.
            if (maiorLance != null) {
                //  Se o maiorLance não for nulo, ele vai itera sobre cada LanceMain presente getLances().
                for (LanceMain lance : lote.getLances()) {
                    if (lance != maiorLance) {
                        //Para cada lance no lote, verifica o maiorLance atual.
                        lancesNaoVendidos.add(lance);
                        //Adiciona o ArrayList de lancesNaoVendidos.
                    }
                }
            }
        }
        return lancesNaoVendidos.toString();
        //Após percorrer todos os lotes e seus lançes, o método retorna uma representação ArrayList lancesNaoVendidos usando o método `toString().
    }
    /* Como o nome de todos os outros métodos foi bem autoexplicativos, esse não é diferente o método "getLancesNaoVendidos"
    irá retornar lances que não receberam nenhum lance do licitante.
     */
    public LoteMain removeLote(int numero) {
        for (LoteMain lote : lotes) {
            if (lote.getNumero() == numero) {
                lotes.remove(lote);
                return lote;
            }
        }
        return null;
    }
}