package fr.rtp.onemany;

public class SizeSpec implements Spec {

  private ProductSize productSize;

  public SizeSpec(ProductSize productSize) {
    this.productSize = productSize;
  }

  @Override
  public boolean isSatisfiedBy(Product product) {
    return product.getProductSize() == productSize;
  }
}
