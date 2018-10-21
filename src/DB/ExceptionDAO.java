package DB;

public class ExceptionDAO extends Exception {
    ExceptionDAO(Throwable e) {
        initCause(e);
    }
}
