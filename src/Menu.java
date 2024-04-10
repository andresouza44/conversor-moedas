import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.Format;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida.");

                }
                if (!sair) {
                    System.out.println("Digite o valor que deseja converter");
                    double valor = scan.nextDouble();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dataFormatada = formatter.format(LocalDate.now());


                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = BuscaCotacao.buscarCotacao(moedaDe, moedaPara);
                    Cotacao cotacao = gson.fromJson(json, Cotacao.class);
                    Double valorConvertido = valor * cotacao.conversion_rate();

                    System.out.printf("Cotação do dia: %s 1[%s] = %.4f [%s]\n",
                            dataFormatada,moedaDe, cotacao.conversion_rate(), moedaPara);
                    System.out.printf("Valor de %.2f [%s] corresponde ao valor final de => %.2f [%s]\n",
                            valor, moedaDe, valorConvertido, moedaPara );
                }

            } catch (InputMismatchException e) {
                System.out.println("Número inválido. Tente Novamente");
                scan.nextLine();


            }
        }

        scan.close();
    }

}
