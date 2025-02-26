package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount;

public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException() {
        super("Invalid discount: percentage must be between 0 and 100.");
    }
}
