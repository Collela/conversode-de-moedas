package model;

import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Scanner;

public class CurrencyRequest {
    String key = "";

    public CurrencyRequest(){
        File file = new File("C:\\Users\\dagma\\Alura curso java\\Desafio Conversor de Moedas\\src\\model\\Key.txt");
        try{
            Scanner sc = new Scanner(file);
            key = sc.next();
            System.out.println("Arquivo encontrado: " + key);

        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    public void getCurrency(String mainCurrency, String toConvertCurrency, double amount) throws IOException, InterruptedIOException {
        String url = "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + mainCurrency;

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonData = new Gson().fromJson(response.body(), JsonObject.class);
            JsonObject dataObj = jsonData.getAsJsonObject("conversion_rates");

            System.out.println("Deu certo: " + dataObj );

            if (mainCurrency != null && dataObj != null){
                System.out.println("Entrou no if");
            }

        }catch (Exception e){
            System.out.println("Algo nao esta certo: " + e);
        }

    }
}
