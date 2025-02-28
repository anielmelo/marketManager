package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct;

public class InvalidProductQuantityException extends RuntimeException {
    public InvalidProductQuantityException() {
        super("Invalid product quantity.");
    }
}
