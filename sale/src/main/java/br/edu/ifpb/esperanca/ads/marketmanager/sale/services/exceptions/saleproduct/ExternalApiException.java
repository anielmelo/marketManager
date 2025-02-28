package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message) {
        super(message);
    }
}
