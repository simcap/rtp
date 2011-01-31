package fr.rtp.onemany.refactored;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fr.rtp.onemany.Product;
import fr.rtp.onemany.Spec;

public class SimpleProductRepository {

  private List<Product> products = new ArrayList<Product>();

  public Iterator<Product> iterator() {
    return products.iterator();
  }

  public List<Product> selectBy(Spec spec) {
    Spec[] specs = { spec };
    return selectBy(Arrays.asList(specs));
  }

  public List<Product> selectBy(List<Spec> specs) {
    List<Product> foundProducts = new ArrayList<Product>();
    Iterator<Product> products = iterator();
    while (products.hasNext()) {
      Product product = (Product) products.next();

      Iterator<Spec> specifications = specs.iterator();
      boolean satisfiesAllSpecs = true;
      while (specifications.hasNext()) {
        Spec productSpec = specifications.next();
        satisfiesAllSpecs &= productSpec.isSatisfiedBy(product);
      }
      if (satisfiesAllSpecs)
        foundProducts.add(product);
    }
    return foundProducts;
  }

  public void add(Product product) {
    products.add(product);

  }
}
