import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.BuscaCotacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImprimeCotacao {
    static List<LogEntity> logs = new ArrayList<>();

    public static void geraCotacao(String moedaDe, String moedaPara, double valor) {

        String nomeMoedaDe = buscaNomeMoeda(moedaDe);
        String nomeMoedaPara = buscaNomeMoeda(moedaPara);

        if (nomeMoedaDe == null ) {
            System.out.println("Moeda [" + moedaDe + "] Não encontrada.");
        }
        else if (nomeMoedaPara == null) {
            System.out.println("Moeda [" + moedaPara + "] Não encontrada.");
        } else {
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

            LogEntity log = new LogEntity(valorConvertido, cotacao);
            logs.add(log);
            LogUtil.salvarLog(log);
        }
    }
    public static void imprimiLog() {
        System.out.println("Histórico de Conversões da sessão");
        System.out.println("---------------------------------");

        if (logs.isEmpty()) {
            System.out.println("A Lista está vazia");
        } else {
            logs.forEach(System.out::print);

        }
    }
    public static String buscaNomeMoeda(String siglaMoeda) {
        Moedas listaMoedas = BuscaListaMoedas.buscarNomeMoeda();

        for (List<String> supportedCode : listaMoedas.supported_codes()) {
            if (supportedCode.get(0).contains(siglaMoeda)) {
                return supportedCode.get(1);
            }
        }
        return null;
    }
}

