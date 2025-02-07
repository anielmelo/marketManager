package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product;

public class InvalidProductQuantityException extends RuntimeException {
    public InvalidProductQuantityException() {
        super("Cannot register a product with quantity less than or equal to 0.");
    }
}
