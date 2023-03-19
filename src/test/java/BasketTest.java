import org.junit.Test;

public class BasketTest {

    @Test
    public void addToCart_cnt_was_added_Test() {
        Basket basket = new Basket();
        Product product = new Product("АЙАЙАЙ", 25.03);

        basket.basket[0] = product;
        basket.basket[0].cnt = 10;

        basket.addToCart(product, 25);
        int ex = 35;

        junit.framework.Assert.assertEquals(ex, basket.basket[0].cnt);
    }
}