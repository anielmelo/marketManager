package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.replacement;

public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException() {
        super("Insufficient quantity available.");
    }
}
