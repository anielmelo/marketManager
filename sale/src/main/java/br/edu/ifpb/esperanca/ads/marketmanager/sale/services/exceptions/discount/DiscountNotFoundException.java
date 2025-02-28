package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount;

public class DiscountNotFoundException extends RuntimeException {
    public DiscountNotFoundException(Long id) {
        super("Discount with ID " + id + " not found.");
    }
}
