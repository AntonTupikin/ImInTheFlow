import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket();
        Product[] prd = Product.getProducts();
        basket.loadBasketFromTxt(prd, basket);
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
                basket.loadBasketFromTxt();
            }
        }

    }

}
