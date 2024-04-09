import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menu() {
        Scanner scan = new Scanner(System.in);
        Boolean sair = false;
        while (!sair) {
            String menu = """
                    Seja bem vindo/a ao Conversor de Moeda
                    ****************************************
                    1) Dólar            => Peso Argentino
                    2) Peso Argentino   => Dólar
                    3) Dólar            => Real
                    4) Real             => Dólar
                    5) Dólar            => Peso Colombiano
                    6) Peso Colombiano  => Dólar
                    7) Euro             => Real
                    8) Real             => Euro
                    9) Sair do Sistema
                    Escolha uma opção válida:
                    ****************************************""";

            System.out.println(menu);

            try {
                int opcao = scan.nextInt();
                switch (opcao) {
                    case (1):
                        System.out.println(BuscaCotacao.buscarCotacao("USD", "ARS"));
                        break;
                    case (2):
                        System.out.println(BuscaCotacao.buscarCotacao("ARS", "USD"));
                        break;
                    case (3):
                        System.out.println(BuscaCotacao.buscarCotacao("USD", "BRL"));
                        break;
                    case (4):
                        System.out.println(BuscaCotacao.buscarCotacao("BRL", "USD"));
                        break;
                    case (5):
                        System.out.println(BuscaCotacao.buscarCotacao("USD", "COP"));
                    case (6):
                        System.out.println(BuscaCotacao.buscarCotacao("COP", "USD"));
                        break;
                    case (7):
                        System.out.println(BuscaCotacao.buscarCotacao("EUR", "BRL"));
                        break;
                    case (8):
                        System.out.println(BuscaCotacao.buscarCotacao("BRL", "EUR"));
                        break;
                    case (9):
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                scan.nextLine();
            }
        }


    }

}
