import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.BuscaApikey;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.net.http.HttpRequest.newBuilder;

public class BuscaListaMoedasTeste {
    public static Moedas buscarNomeMoeda() {
        String apiKey = BuscaApikey.getApiKey("api.key");
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/codes";


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Moedas moedas = gson.fromJson(json, Moedas.class);

            return  moedas;

//            for (List<String> supportedCode : moedas.supported_codes()) {
//                if (supportedCode.get(0).contains(silgaMoeda)) {
//                    return supportedCode.get(1);
//
//                }
//
//            }
        //    return null;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

