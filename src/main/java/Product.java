public class Product {
    private String title;
    private Money price;

    public Product(String title, Money price) {
        this.title = title;
        this.price = price;
    }

    public String title() {
        return this.title;
    }

    public double price() {
        return this.price.toDouble();
    }
}
