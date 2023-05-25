import java.util.Scanner;
/*  Esse método é o ponto de entrada do programa e é onde a interação com o usuário ocorre para executar diferentes opções relacionadas a um leilão. */
//Tarefa 05
public class Amain {
    public static void main(String[] args) {
        LeilaoMain leilao = new LeilaoMain();//Instanciamos um objeto da class LeilaoMain
        Scanner scanner = new Scanner(System.in);//Instanciamos um objeto da class Scanner

        while (true)//loop infinito
        {
            System.out.println("===== Leilão =====");
            System.out.println("1. Inserir lote");
            System.out.println("2. Dar lance");
            System.out.println("3. Mostrar os maiores lances");
            System.out.println("4. Mostrar lances não vendidos");
            System.out.println("5. Mostrar lotes");
            System.out.println("6. Remove Lote");
            System.out.println("7. Fecha Leilão");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a descrição do lote: ");
                    String descricao = scanner.nextLine();// a entrada de dados irar para descrição em "insiraLote"
                    leilao.insiraLote(descricao);
                    System.out.println("Lote inserido com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o número do lote: ");
                    int numeroDoLote = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.print("Digite o nome do licitante: ");
                    String nomeLicitante = scanner.nextLine();
                    PessoaMain licitante = new PessoaMain(nomeLicitante);

                    System.out.print("Digite o valor do lance: ");
                    long valorLance = scanner.nextLong();
                    leilao.verificaLance(numeroDoLote, licitante, valorLance);
                    System.out.print("Lance efetivado com sucesso!");
                    break;
                case 3:
                    System.out.println("Maiores lances: " + leilao.getMaiorLance());
                    leilao.mostraLotes();
                    break;
                case 4:
                    System.out.println("Lances não vendidos: " + leilao.getLancesNaoVendidos());
                    break;
                case 5:
                    leilao.mostraLotes();
                    break;
                case 6:
                    System.out.print("Digite o número do lote a ser removido: ");
                int numeroLote = scanner.nextInt();
                LoteMain loteRemovido = leilao.removeLote(numeroLote);
                if (loteRemovido != null) {
                    System.out.println("Lote " + numeroLote + " removido com sucesso!");
                } else {
                    System.out.println("Não foi possível remover o lote " + numeroLote + ". Verifique o número do lote.");
                } break;
                case 7:
                    leilao.close();
                    System.out.println("Leilão fechado. Detalhes dos arrematadores:");
                    leilao.mostraLotes();
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;

            }
          }
        }
      }
