import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    String title;
    double price;
    int cnt;
    int id;
    static int count = 0;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
        this.cnt = 0;
        this.id = count;
        count++;
    }

    public void print() {
        System.out.println(id + 1 + ". " + title + " " + price + " rub./pcs");
    }

    public static Product[] getProducts() {
        ArrayList<String> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            //чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                products.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Product[] prd = new Product[products.size()];
        Iterator<String> iter = products.iterator();
        int i = 0;
        while (iter.hasNext()) {
            String[] words = iter.next().split("\\|");
            prd[Integer.parseInt(words[0])] = new Product(words[1], Double.parseDouble(words[2]));
            i++;
        }
        return prd;
    }

}
