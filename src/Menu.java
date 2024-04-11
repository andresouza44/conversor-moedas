import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menu() {
        Scanner scan = new Scanner(System.in);
        Boolean sair = false;
        String moedaDe = "";
        String moedaPara = "";

        while (!sair) {
            String menu = """
                                        
                    Seja bem vindo(a) ao Conversor de Moeda
                    ****************************************
                    1)  Dólar            => Peso Argentino
                    2)  Peso Argentino   => Dólar
                    3)  Dólar            => Real
                    4)  Real             => Dólar
                    5)  Dólar            => Peso Colombiano
                    6)  Peso Colombiano  => Dólar
                    7)  Euro             => Real
                    8)  Real             => Euro
                    9)  Listar Cotações da sessão
                    10) Listar Log
                    11) Sair do Sistema
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
                        moedaDe = "USD";
                        moedaPara = "COP";
                        break;
                    case (6):
                        moedaDe = "COP";
                        moedaPara = "USD";
                        break;

                    case (7):
                        moedaDe = "EUR";
                        moedaPara = "BRL";
                        break;
                    case (8):
                        moedaDe = "BRL";
                        moedaPara = "EUR";
                        break;
                    case (9):
                        ImprimeCotacao.imprimiLog();
                        break;
                    case (10):
                        LogUtil.loadLog();
                        break;
                    case (11):
                        sair = true;
                        break;

                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida.");

                }
                if ((!sair && (opcao != 9 && opcao != 10))) {
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
    }

}
