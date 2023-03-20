import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

public class MainTest {

    @Test
    public void checkBasketLoading() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("basket.json"));
        JSONObject jsonObject = (JSONObject) obj;
        String s = jsonObject.toJSONString();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Basket basket = gson.fromJson(s, Basket.class);

        Assert.assertNotNull(basket);
    }

    @Test
    public void checkBasketJsonFileNotEmpty() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("basket.json"));
        Assert.assertNotNull(obj);

    }
}