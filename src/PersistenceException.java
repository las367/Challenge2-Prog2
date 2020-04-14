public class PersistenceException extends Exception {

    private String message;

    public PersistenceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
