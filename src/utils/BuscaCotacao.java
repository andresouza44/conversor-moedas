package utils;

import utils.BuscaApikey;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;

public class BuscaCotacao {

    public static String buscarCotacao(String moedaDe, String moedaPara) {
        String apiKey = BuscaApikey.getApiKey("api.key");
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                + moedaDe + "/" + moedaPara;

       // String url = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}