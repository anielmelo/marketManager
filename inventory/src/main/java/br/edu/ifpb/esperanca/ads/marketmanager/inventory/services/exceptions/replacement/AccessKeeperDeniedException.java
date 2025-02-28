package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.replacement;

public class AccessKeeperDeniedException extends RuntimeException {
    public AccessKeeperDeniedException() {
        super("UserID not found for stock keeper.");
    }
}
