import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu2 {

    public static void menu() {
        Scanner scan = new Scanner(System.in);
        boolean finalizar = false;

        final int LISTAR_MOEDAS = 1;
        final int COTACAO = 2;
        final int MENU_LISTA_COTACAO = 3;
        final int MENU_LISTA_LOG = 4;
        final int VOLTAR = 5;
        final int SAIR = 6;

        while (true) {
            String menu = """
                                        
                    COTAÇÃO OUTRAS MOEDAS
                    ****************************************
                    1)  Listar Moedas
                    2)  Cotação
                    3)  Listar Cotações da sessão
                    4)  Listar Log
                    5)  Voltar ao Menu Principal
                    6) Sair do Sistema
                    Escolha uma opção válida:
                    ****************************************""";

            System.out.println(menu);

            try {
                int opcao = scan.nextInt();

                switch (opcao) {
                    case LISTAR_MOEDAS:
                      BuscaListaMoedas.buscarNomeMoeda()
                              .supported_codes().forEach(System.out::println);
                        break;
                    case COTACAO:
                        break;
                    case MENU_LISTA_COTACAO:
                        ImprimeCotacao.imprimiLog();
                        break;
                    case MENU_LISTA_LOG:
                        LogUtil.loadLog();
                        break;
                    case VOLTAR:
                        Menu.menu();
                        break;
                    case SAIR:
                        finalizar = true;
                        break;

                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida.");

                }
                if (finalizar) {
                    break;

                }
                if (opcao == COTACAO) {
                    scan.nextLine();
                    System.out.println("Digite a sigla da primeira moeda");
                    String moedaDe = scan.nextLine().toUpperCase().trim();

                    System.out.println("Digite a sigla da segunda moeda");
                    String moedaPara = scan.nextLine().toUpperCase().trim();

                    System.out.println("Digite o valor que deseja converter");
                    double valor = scan.nextDouble();

                     ImprimeCotacao.geraCotacao(moedaDe, moedaPara, valor);
                }

            } catch (InputMismatchException e) {
                System.out.println("Número inválido. Tente Novamente");
                scan.nextLine();

            }
        }
        scan.close();
        System.out.println("Programa Finalizado");
        System.exit(0);
    }

}

