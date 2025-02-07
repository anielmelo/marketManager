package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.supplier;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException() {
        super("Supplier not found.");
    }
}
