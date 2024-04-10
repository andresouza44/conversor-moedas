import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ImprimeCotacao {

    public static void geraCotacao(String moedaDe, String moedaPara, double valor) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = formatter.format(LocalDate.now());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = BuscaCotacao.buscarCotacao(moedaDe, moedaPara);
        Cotacao cotacao = gson.fromJson(json, Cotacao.class);
        Double valorConvertido = valor * cotacao.conversion_rate();

        System.out.printf("Cotação do dia: %s 1[%s] = %.4f [%s]\n",
                dataFormatada, moedaDe, cotacao.conversion_rate(), moedaPara);
        System.out.printf("Valor de %.2f [%s] corresponde ao valor final de => %.2f [%s]\n",
                valor, moedaDe, valorConvertido, moedaPara);
    }
}
