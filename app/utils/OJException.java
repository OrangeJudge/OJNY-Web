package utils;

public class OJException extends Exception {
    private int code;
    public OJException(int code) {
        super("");
        this.code = code;
    }
    public OJException(int code, String message) {
        super(message);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
