package DB;

public class ExceptionDAO extends Exception{
    public ExceptionDAO(Throwable e){
        initCause(e);
    }
}
