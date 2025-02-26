package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct;

public class ProductAlreadyInSaleException extends RuntimeException {
    public ProductAlreadyInSaleException() {
        super("Product is already associated with a sale.");
    }
}
