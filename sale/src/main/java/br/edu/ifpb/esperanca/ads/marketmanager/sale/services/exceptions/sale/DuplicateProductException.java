package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale;

public class DuplicateProductException extends RuntimeException {
    public DuplicateProductException(String message) {
        super(message);
    }
}
