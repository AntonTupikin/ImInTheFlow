import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Basket {
    static double sum = 0;
    protected Product[] basket = new Product[100];

    public Product[] addToCart(Product product, int added) {
        int i = product.id;
        this.basket[i] = product;
        this.basket[i].cnt = this.basket[i].cnt + added;
        return this.basket;
    }

    public void showBasket() {
        String separator = "*********************";
        System.out.println("ВАША КОРЗИНА\n" + separator);
        for (Product product : this.basket) {
            if (product != null) {
                System.out.println(product.title + " " + product.cnt + " psc " + product.cnt * product.price + " rub");
                sum = sum + product.cnt * product.price;

            }
        }
        System.out.println(separator);
        String result = String.format("%.2f", sum);
        System.out.println("Итого: " + result + " руб.\n");
    }

    public void loadBasketFromTxt() {
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
            basket.addToCart(prd[Integer.parseInt(words[0])],
                    Integer.parseInt(words[1]));
            i++;
        }
    }

    public Product[] getBasket() {
        return basket;
    }
}
