import java.util.*;

public class Menu {
    public static void menu() {
        Scanner scan = new Scanner(System.in);
        Boolean sair = false;
        String moedaDe = "";
        String moedaPara = "";

        List<Integer> conversoesValidas = Arrays.asList(1, 2, 3, 4, 5, 6);

        final int MENU_LISTA_COTACAO=7;
        final int MENU_LISTA_LOG = 8;
        final int OUTRAS_MOEDAS = 9;
        final int FINALIZAR = 10;

        while (!sair) {
            String menu = """
                                        
                    Seja bem vindo(a) ao Conversor de Moeda
                    ****************************************
                    1)  Dólar            => Peso Argentino
                    2)  Peso Argentino   => Dólar
                    3)  Dólar            => Real
                    4)  Real             => Dólar
                    5)  Euro             => Real
                    6)  Real             => Euro
                    7)  Listar Cotações da sessão
                    8)  Listar Log
                    9)  Outras Moedas
                    10) Sair do Sistema
                    Escolha uma opção válida:
                    ****************************************""";

            System.out.println(menu);

            try {
                int opcao = scan.nextInt();

                switch (opcao) {
                    case (1):
                        moedaDe = "USD";
                        moedaPara = "ARS";
                        break;
                    case (2):
                        moedaDe = "ARS";
                        moedaPara = "USD";
                        break;
                    case (3):
                        moedaDe = "USD";
                        moedaPara = "BRL";
                        break;
                    case (4):
                        moedaDe = "BRL";
                        moedaPara = "USD";
                        break;
                    case (5):
                        moedaDe = "EUR";
                        moedaPara = "BRL";
                        break;
                    case (6):
                        moedaDe = "BRL";
                        moedaPara = "EUR";
                        break;
                    case MENU_LISTA_COTACAO:
                        ImprimeCotacao.imprimiLog();
                        break;
                    case MENU_LISTA_LOG:
                        LogUtil.loadLog();
                        break;
                    case OUTRAS_MOEDAS:
                        Menu2.menu();
                        break;
                    case FINALIZAR:
                        sair = true;
                        break;

                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida.");

                }
                if(sair){
                    System.out.println("Programa Finalizado");
                    System.exit(0);
                }

                if (conversoesValidas.contains(opcao) ){
                //if ((!sair && (opcao != MENU_LISTA_COTACAO && opcao != MENU_LISTA_LOG))) {
                    System.out.println("Digite o valor que deseja converter");
                    double valor = scan.nextDouble();

                    ImprimeCotacao.geraCotacao(moedaDe, moedaPara, valor);
                }

            } catch ( NoSuchElementException e) {
                System.out.println("Número inválido. Tente Novamente");
                scan.nextLine();

            }
        }

        scan.close();
        System.out.println("Programa Finalizado");
        System.exit(0);


    }

}
