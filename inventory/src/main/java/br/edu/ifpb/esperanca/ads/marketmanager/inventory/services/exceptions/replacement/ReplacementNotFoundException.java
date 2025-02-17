package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.replacement;

public class ReplacementNotFoundException extends RuntimeException {
    public ReplacementNotFoundException() {
        super("Replacement not found.");
    }
}
