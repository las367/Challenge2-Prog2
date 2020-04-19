package filepersistence;

public class EmptyDataException extends Exception {

    String message;

    public EmptyDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
