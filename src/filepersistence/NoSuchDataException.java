package filepersistence;

public class NoSuchDataException extends Exception {

    private String message;

    public NoSuchDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
