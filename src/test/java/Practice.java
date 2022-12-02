import net.bytebuddy.utility.RandomString;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Practice {

    public static void main(String[] args) {
        String productId = RandomString.make(12)+"_"+new Random().nextInt(100000);

        Map<String, String> params = new HashMap<>();
        params.put("quantity","12");
        System.out.println(" = " + Integer.parseInt(params.get("quantity")));
        System.out.println("productId = " + productId);
    }
}
