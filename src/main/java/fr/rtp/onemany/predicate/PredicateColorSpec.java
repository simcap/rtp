package fr.rtp.onemany.predicate;

import java.awt.Color;

import fr.rtp.onemany.Product;
import fr.rtp.onemany.Spec;

public class PredicateColorSpec implements PredicateSpec {

  private Color targetColor;

  public PredicateColorSpec(Color targetColor) {
    this.targetColor = targetColor;
  }

  @Override
  public boolean apply(Product product) {
    return product.getColor() == targetColor;
  }

}
