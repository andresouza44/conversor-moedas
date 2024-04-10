import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImprimeCotacao {
    static List<Log> logs = new ArrayList<>();

    public static void geraCotacao(String moedaDe, String moedaPara, double valor) {

        String nomeMoedaDe = NomeMoeda.valueOf(moedaDe).getNome();
        String nomeMoedaPara = NomeMoeda.valueOf(moedaPara).getNome();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = formatter.format(LocalDate.now());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = BuscaCotacao.buscarCotacao(moedaDe, moedaPara);
        Cotacao cotacao = gson.fromJson(json, Cotacao.class);
        Double valorConvertido = valor * cotacao.conversion_rate();

        System.out.printf("Cotação do dia: %s 1[%s] = %.4f [%s]\n",
                dataFormatada, nomeMoedaDe, cotacao.conversion_rate(), nomeMoedaPara);
        System.out.printf("Valor de %.2f [%s] corresponde ao valor final de => %.2f [%s]\n",
                valor, nomeMoedaDe, valorConvertido, nomeMoedaPara);

        Log log = new Log(valorConvertido, cotacao);
        logs.add(log);

        };

    public static void imprimiLog () {
        System.out.println("Histórico de Conversões da sessão");

        if (logs.isEmpty()) {
            System.out.println("A Lista está vazia");
        } else {
            logs.forEach(e -> {
                System.out.printf("Cotação: 1[%s] = %.4f [%s] - Valor de %.2f %s = %.2f %s\n",
                        e.getCotacao().base_code(),e.getCotacao().conversion_rate(),
                        e.getCotacao().target_code(), e.getValor()/e.getCotacao().conversion_rate(),
                        e.getCotacao().base_code(), e.getValor() ,e.getCotacao().target_code()
                       );
            }) ;

}
    }

}
