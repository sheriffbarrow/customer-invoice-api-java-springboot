package sheriff.customer.invoice.management.Exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
