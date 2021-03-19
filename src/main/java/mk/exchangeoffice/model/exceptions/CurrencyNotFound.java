package mk.exchangeoffice.model.exceptions;

public class CurrencyNotFound extends RuntimeException{
    public CurrencyNotFound() {
        super("Currency not found");
    }
}
