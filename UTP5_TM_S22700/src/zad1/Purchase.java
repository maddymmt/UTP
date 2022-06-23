/**
 *
 *  @author Turczyn Magdalena S22700
 *
 */

package zad1;


public class Purchase {

    private String id;
    private String name;
    private String article;
    private double price;
    private double count;

    public Purchase(String id, String name, String article, double price, double count) {
        this.id = id;
        this.name = name;
        this.article = article;
        this.price = price;
        this.count = count;
    }
    public double cost(){
        return price * count;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return id + ";" + name + ";" + article + ";" + price + ";" + count;
    }
}
