package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount;

public class DiscountInactiveException extends RuntimeException {
    public DiscountInactiveException() {
        super("O desconto está desativado e não pode ser aplicado.");
    }
}
