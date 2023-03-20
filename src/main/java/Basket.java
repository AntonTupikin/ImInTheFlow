import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Basket {
    private double sum = 0;
    protected Product[] basket = new Product[100];

    public void addToCart(Product product, int added) {
        if (basket[product.id] == null) {
            basket[product.id] = new Product(product.title, product.price);
        }
        basket[product.id].cnt += added;
    }

    public void showBasket() {
        String separator = "*********************";
        System.out.println("ВАША КОРЗИНА\n" + separator);
        for (Product product : basket) {
            if (product != null) {
                System.out.println(product.title + " " + product.cnt + " штук " + String.format("%.2f", product.cnt * product.price) + " руб");
                sum += product.cnt * product.price;

            }
        }
        System.out.println(separator);
        String result = String.format("%.2f", sum);
        sum = 0;
        System.out.println("Итого: " + result + " руб.\n");
    }

    public void saveBasketToTxt() {
        String string = "";
        try (FileWriter writer = new FileWriter("basket.txt", false)) {
            // запись всей строки
            for (Product product : this.basket) {
                if (product != null) {
                    string = product.id + "|" + product.cnt + "\n";
                    writer.write(string);
                }
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void loadBasketFromTxt(Product[] prd, Basket basket) {
        ArrayList<String> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("basket.txt"))) {
            //чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                products.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Iterator<String> iter = products.iterator();
        int i = 0;
        while (iter.hasNext()) {
            String[] words = iter.next().split("\\|");
            basket.addToCart(prd[Integer.parseInt(words[0])], Integer.parseInt(words[1]));
            i++;
        }
    }

    public Product[] getBasket() {
        return basket;
    }
}
