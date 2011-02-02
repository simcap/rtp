package fr.rtp.onemany;

public class BelowPriceSpec implements Spec {

  private double maxPrice;

  public BelowPriceSpec(double maxPrice) {
    this.maxPrice = maxPrice;
  }

  @Override
  public boolean isSatisfiedBy(Product product) {

    return product.getPrice() < maxPrice;
  }
}
