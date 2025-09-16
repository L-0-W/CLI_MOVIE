package type.api.API.book;

public class RETURNS<T> {

    private int RETURN_CODE;
    private String RETURN_MESSAGE;
    private T data;

    public RETURNS(T data, String RETURN_MESSAGE, int RETURN_CODE) {
        this.data = data;
        this.RETURN_CODE = RETURN_CODE;
        this.RETURN_MESSAGE = RETURN_MESSAGE;
    }

    public int GET_RETURN_CODE() {
        return this.RETURN_CODE;
    }

    public String GET_RETURN_MESSAGE() {
        return this.RETURN_MESSAGE;
    }

    public T GET_RETURN_DATA() {
        return this.data;
    }
}
