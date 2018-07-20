import static spark.Spark.get;

public class ShoppingApp {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            res.type("text/html");
            return "<h1>Hello</h1>";
        });
    }
}
