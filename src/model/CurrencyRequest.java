package model;

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

            if (dataObj != null && dataObj.has(toConvertCurrency)){

                double rate = dataObj.get(toConvertCurrency).getAsDouble();
                System.out.println("Cotacao de " +mainCurrency + " para " + toConvertCurrency + " e de " + rate);

                if ((rate != 0) && (amount != 0)){
                    double resultConversion = amount * rate;
                    System.out.println("O Valor da conversao para " + toConvertCurrency + " e de " + resultConversion);
                }
                else {
                    System.out.println("Informe o valor a ser convertido.");
                }

            }else{
                System.out.println("Erro ao conectar a API");
            }

        }catch (Exception e){
            System.out.println("Erro no sitema: " + e);
        }

    }
}
