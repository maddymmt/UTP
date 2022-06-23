/**
 * @author Turczyn Magdalena S22700
 */

package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomersPurchaseSortFind {

    List<Purchase> customersList = new ArrayList<>();

    public void readFile(String path) {
        try {
            List<String> customers = Files.readAllLines(Paths.get(path));
            customers.forEach(customer -> {
                String[] fields = customer.split(";");
                customersList.add(new Purchase(fields[0], fields[1], fields[2], Double.parseDouble(fields[3]), Double.parseDouble(fields[4])));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String field) {
        Collator collator = Collator.getInstance(new Locale("pl", "PL"));
        System.out.println(field);
        switch (field) {
            case "Nazwiska": {
                customersList.stream()
                        .sorted((o1, o2) -> {
                            if (o1.getName().compareTo(o2.getName()) != 0) {
                                return collator.compare(o1.getName(), o2.getName());
                            }
                            return o1.getId().compareTo(o2.getId());
                        })
                        .forEach(System.out::println);
                break;
            }
            case "Koszty": {
                customersList.stream()
                        .sorted((o1, o2) -> {
                            if (Double.compare(o1.cost(), o2.cost()) != 0) {
                                return Double.compare(o2.cost(), o1.cost());
                            }
                            return o1.getName().compareTo(o2.getName());
                        })
                        .forEach(p -> System.out.println(p + " (koszt: " + p.cost() + ")"));
                break;
            }
        }
        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for (Purchase p : customersList)
            if (p.getId().equals(id))
                System.out.println(p);
        System.out.println();
    }
}
