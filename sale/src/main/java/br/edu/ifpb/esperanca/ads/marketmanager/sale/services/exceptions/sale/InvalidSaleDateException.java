package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale;

public class InvalidSaleDateException extends RuntimeException {
    public InvalidSaleDateException() {
        super("Invalid sale date. The sale date cannot be in the future or have an incorrect format.");
    }
}
