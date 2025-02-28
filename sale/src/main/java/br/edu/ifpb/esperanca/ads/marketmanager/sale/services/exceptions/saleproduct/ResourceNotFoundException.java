package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
