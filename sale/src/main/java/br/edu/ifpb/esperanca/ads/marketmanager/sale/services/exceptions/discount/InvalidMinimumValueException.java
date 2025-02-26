package br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount;

public class InvalidMinimumValueException extends RuntimeException {
  public InvalidMinimumValueException() {
    super("O valor mínimo do desconto deve ser maior que zero.");
  }
}
