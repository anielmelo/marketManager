package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct;

public class EmptyProductListException extends RuntimeException {
    public EmptyProductListException() {
        super("A lista de produtos não pode estar vazia.");
    }
}
