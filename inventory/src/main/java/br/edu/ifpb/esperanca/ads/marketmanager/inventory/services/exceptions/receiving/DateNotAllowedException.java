package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.receiving;

public class DateNotAllowedException extends RuntimeException{
    public DateNotAllowedException() {
        super("The provided date cannot be in the future.");
    }
}
