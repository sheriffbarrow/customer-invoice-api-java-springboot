package sheriff.customer.invoice.management.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
