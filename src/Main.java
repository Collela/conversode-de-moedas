import model.CurrencyRequest;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        String apresentacao = """
        ******************************************************
        Bem-vindo(a) ao conversor de moedas.
        
        Escolha abaixo as moedas que deseja ver a conversao.
        
        Exemplos: USD - Dolar Americano
        BLR - Real Brasileiro
        ARS - Argentina Peso
        Etc...
        
        Digite 'moedas' a qualquer momento caso queira ver a lista de moedas!
        
        ******************************************************
        """;

        System.out.println(apresentacao);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe a moeda que deseja inciar a conversao: ");
        String mainCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Informe a moeda que deseja receber a conversao: ");
        String toConvertCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Informe o valor a ser convertido: ");
        double amount = scanner.nextDouble();

        CurrencyRequest request = new CurrencyRequest();
        request.getCurrency(mainCurrency, toConvertCurrency, amount);
    }
}
