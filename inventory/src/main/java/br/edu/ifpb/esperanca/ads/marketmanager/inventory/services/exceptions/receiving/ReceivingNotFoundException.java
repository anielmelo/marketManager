package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.receiving;

public class ReceivingNotFoundException extends RuntimeException{
    public ReceivingNotFoundException() {
        super("Receiving not found.");
    }
}
