import java.io.*;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
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

}
