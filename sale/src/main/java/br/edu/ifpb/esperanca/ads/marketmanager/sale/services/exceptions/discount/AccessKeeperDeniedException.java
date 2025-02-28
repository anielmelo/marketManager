package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount;

public class AccessKeeperDeniedException extends RuntimeException {
    public AccessKeeperDeniedException() {
        super("UserID not found for stock keeper.");
    }
}
