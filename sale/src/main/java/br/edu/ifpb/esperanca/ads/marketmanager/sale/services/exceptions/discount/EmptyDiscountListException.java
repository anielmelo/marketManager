package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount;

public class EmptyDiscountListException extends RuntimeException {
    public EmptyDiscountListException() {
        super("The discount list cannot be empty.");
    }
}
