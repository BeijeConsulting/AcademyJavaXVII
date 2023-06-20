package utils;

//classe generica per fare i test e provare vari casi di array
public class TestCase<T, R> {
    private final T value;
    private final R expectedValue;

    public TestCase(T value, R expectedValue) {
        this.value = value;
        this.expectedValue = expectedValue;
    }


    public T getValue() {
        return value;
    }

    public R getExpectedValue() {
        return expectedValue;
    }
}
