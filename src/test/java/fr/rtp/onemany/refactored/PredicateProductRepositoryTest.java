package fr.rtp.onemany.refactored;

import java.awt.Color;
import java.util.List;

import junit.framework.TestCase;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import fr.rtp.onemany.BelowPriceSpec;
import fr.rtp.onemany.Product;
import fr.rtp.onemany.ProductSize;
import fr.rtp.onemany.SizeSpec;
import fr.rtp.onemany.predicate.PredicateColorSpec;
import fr.rtp.onemany.predicate.PredicateProductRepository;

public class PredicateProductRepositoryTest extends TestCase {

  private Product fireTruck = new Product("f1234", "Fire Truck", Color.red, 8.95f, ProductSize.MEDIUM);
  private Product barbieClassic = new Product("b7654", "Barbie Classic", Color.yellow, 15.95f, ProductSize.SMALL);
  private Product frisbee = new Product("f4321", "Frisbee", Color.pink, 9.99f, ProductSize.LARGE);
  private Product baseball = new Product("b2343", "Baseball", Color.white, 8.95f, ProductSize.NOT_APPLICABLE);
  private Product toyConvertible = new Product("p1112", "Toy Porsche Convertible", Color.red, 230.00f,
      ProductSize.NOT_APPLICABLE);

  private PredicateProductRepository repository;

  protected void setUp() {
    repository = new PredicateProductRepository();
    repository.add(fireTruck);
    repository.add(barbieClassic);
    repository.add(frisbee);
    repository.add(baseball);
    repository.add(toyConvertible);
  }

  public void testFindByColor() {
    List<Product> foundProducts = repository.selectBy(new PredicateColorSpec(Color.red));
    assertEquals("found 2 red products", 2, foundProducts.size());
    assertTrue("found fireTruck", foundProducts.contains(fireTruck));
    assertTrue("found Toy Porsche Convertible", foundProducts.contains(toyConvertible));
  }

  public void testFindByColorSizeAndBelowPrice() {

    Predicate<Product> andPredicate = Predicates.and( //
        new PredicateColorSpec(Color.red) //
        , new PredicateColorSpec(Color.black));

    List<Product> foundProducts = repository.selectBy(andPredicate);
    assertEquals("small red products below $10.00", 0, foundProducts.size());
  }
}
