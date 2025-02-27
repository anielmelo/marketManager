package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.supplier;

public class SupplierAlreadyExistsException extends RuntimeException {
    public SupplierAlreadyExistsException() {
        super("Supplier with this CNPJ already exists.");
    }
}
