import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Product[] prd = Product.getProducts();
        Basket basket = loadFromBinFile();
        basket.showBasket();

        int userChoice;
        while (true) {

            System.out.println("Добавьте в корзину");
            for (int i = 0; i < prd.length; i++) {
                prd[i].print();
            }
            System.out.println("Для выхода введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
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
                basket.showBasket();
                saveBin(basket);
            }
        }

    }

    public static void saveBin(Basket basket) {
        // откроем выходной поток для записи в файл
        try (FileOutputStream fos = new FileOutputStream("basket.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(basket);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Basket loadFromBinFile() {
        Basket basket = null;
        // откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream("basket.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            basket = (Basket) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return basket;
    }
}
