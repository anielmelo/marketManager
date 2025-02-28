package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}

