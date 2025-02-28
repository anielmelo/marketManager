package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale;


public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(Long id) {
        super("Venda não encontrada com o ID: " + id);
    }
}

