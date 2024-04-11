import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import utils.ApiGetRequest;
import utils.BuscaApikey;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImprimeCotacao {
    private static List<LogEntity> logs = new ArrayList<>();
    private static String apiKey = BuscaApikey.getApiKey("api.key");

    public static void geraCotacao(String moedaDe, String moedaPara, double valor) {

        if (moedaDe.isEmpty() || moedaPara.isEmpty()){
            System.out.println("Entrada Inválida! Tente Novamente.");
            Menu2.menu();
        }

        String nomeMoedaDe = buscaNomeMoeda(moedaDe);
        String nomeMoedaPara = buscaNomeMoeda(moedaPara);


        if (nomeMoedaDe == null) {
            System.out.println("Moeda [" + moedaDe + "] Inválida ou Não encontrada.");
        }
        else if (nomeMoedaPara == null) {
            System.out.println("Moeda [" + moedaPara + "] Inválida ou Não encontrada.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = formatter.format(LocalDate.now());

            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                    + moedaDe + "/" + moedaPara;

            String json = ApiGetRequest.apiGetRequest(url);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Cotacao cotacao = gson.fromJson(json, Cotacao.class);

            if(cotacao.result().equals("error")) {
                System.out.println("RESULT: " + cotacao.result());
                System.out.println("error-type: " + cotacao.errorType());
                Menu.menu();
            }

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

    public static Moedas buscaNomeMoedas(){
        try {
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/codes";
            String json = ApiGetRequest.apiGetRequest(url);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Moedas result = gson.fromJson(json, Moedas.class);

            if(result.result().equals("error")){
            System.out.println("error-type: " + result.errorType());

            }
            return  gson.fromJson(json, Moedas.class);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());

        }
            return null;
    }
    public static String buscaNomeMoeda(String siglaMoeda) {
        Moedas listaMoedas = buscaNomeMoedas();

        for (List<String> supportedCode : listaMoedas.supported_codes()) {
            if (supportedCode.get(0).contains(siglaMoeda)) {
                return supportedCode.get(1);
            }
        }
        return null;
    }
}

