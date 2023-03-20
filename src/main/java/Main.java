import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Product[] prd = Product.getProducts();
        Basket basket = loadBasketFromJson();

        basket.showBasket();

        ClientLog log = new ClientLog();
        int userChoice;
        while (true) {
            System.out.println("Добавьте в корзину");
            for (int i = 0; i < prd.length; i++) {
                prd[i].print();
            }
            System.out.println("Для выхода введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                log.exportAsCSV();
                break;
            } else {
                userChoice = Integer.parseInt(input);
                if (userChoice > prd.length) {
                    System.out.println("Нет такого продукта");
                    continue;
                }
                System.out.println("Сколько добавить?");
                int added = Integer.parseInt(scanner.nextLine());
                basket.addToCart(prd[userChoice - 1], added);
                log.log(userChoice - 1, added);
                basket.showBasket();
                log.exportAsCSV();
                saveBasketToJson(basket);
            }
        }
    }

    public static void saveBasketToJson(Basket basket) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String obj = gson.toJson(basket);

        try (FileWriter file = new
                FileWriter("basket.json")) {
            file.write(String.valueOf(obj));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Basket loadBasketFromJson() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("basket.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String s = jsonObject.toJSONString();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Basket basket = gson.fromJson(s, Basket.class);
            return basket;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Basket basket = new Basket();
            return basket;
        }


    }

}
