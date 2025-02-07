package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product not found.");
    }
}
