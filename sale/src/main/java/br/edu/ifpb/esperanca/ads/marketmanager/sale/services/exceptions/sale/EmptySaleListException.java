package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale;

public class EmptySaleListException extends RuntimeException {
    public EmptySaleListException() {
        super("Sale product list cannot be empty.");
    }
}
