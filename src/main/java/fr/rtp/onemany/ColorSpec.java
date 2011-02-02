package fr.rtp.onemany;

import java.awt.Color;

public class ColorSpec implements Spec {

    private Color targetColor;

    public ColorSpec(Color targetColor) {
        this.targetColor = targetColor;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getColor() == targetColor;
    }

}
