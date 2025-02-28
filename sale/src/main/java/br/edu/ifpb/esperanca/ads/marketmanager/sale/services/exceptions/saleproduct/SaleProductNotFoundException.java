package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct;

public class SaleProductNotFoundException extends RuntimeException {
    public SaleProductNotFoundException(Long id) {
        super("Produto da venda não encontrado com o ID: " + id);
    }
}

