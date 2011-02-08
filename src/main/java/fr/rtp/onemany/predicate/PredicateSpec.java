package fr.rtp.onemany.predicate;

import com.google.common.base.Predicate;

import fr.rtp.onemany.Product;

public interface PredicateSpec extends Predicate<Product>{

  public boolean apply(Product product);

}
