package jaskell.util;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TryTest {
    @Test
    public void testOr() {
        // Create two Success instances
        Try<String> success1 = Try.success("hello");
        Try<String> success2 = Try.success("world");

        // Call the `or` method with these arguments
        Try<String> result = success1.or(success2);

        // Assert that the returned value is a Success instance
        assertTrue(result.isOk());
    }

    @Test
    public void testRecover() throws Exception {
        // Create a Failure instance with an Exception
        Try<String> failure = Try.failure(new RuntimeException());

        // Call the recover method with a Function that returns a String
        Try<String> result = failure.recover(e -> "hello");

        // Assert that the returned value is a Success instance
        assertTrue(result.isOk());

        // Assert that the returned value contains the expected String
        assertEquals("hello", result.get());
    }

    @Test
    public void testRecoverToTry() throws Exception {
        // Create a Failure instance with an Exception
        Try<String> failure = Try.failure(new Exception());

        // Call the recoverToTry method with a Function that returns a Try<String>
        Try<String> result = failure.recoverToTry(e -> Try.success("hello"));

        // Assert that the returned value is a Success instance
        assertTrue(result.isOk());

        // Assert that the returned value contains the expected String
        assertEquals("hello", result.get());
    }

    @Test
    public void testGet() throws Exception {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Call the get method
        String result = success.get();

        // Assert that the returned value is the expected String
        assertEquals("hello", result);
    }

    @Test
    public void testOrElse() {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Call the orElse method with an alternative String value
        String result = success.orElse("world");

        // Assert that the returned value is the expected String
        assertEquals("hello", result);
    }

    @Test
    public void testOrElseGet() throws Exception {
        // Create a Failure instance with an Exception
        Try<String> failure = Try.failure(new RuntimeException());

        // Call the orElseGet method with a Success instance that contains a String value
        String result = failure.orElseGet(Try.success("hello"));

        // Assert that the returned value contains the expected String
        assertEquals("hello", result);
    }

    @Test
    public void testGetOr() throws Exception {
        // Create a Failure instance with an Exception
        Try<String> failure = Try.failure(new RuntimeException());

        // Call the getOr method with a Function that returns a String value
        String result = failure.getOr(e -> "hello");

        // Assert that the returned value contains the expected String
        assertEquals("hello", result);
    }

    @Test
    public void testGetRecovery() throws Exception {
        // Create a Failure instance with an Exception
        Try<String> failure = Try.failure(new RuntimeException());

        // Call the getRecovery method with a Function that returns a Try<String>
        String result = failure.getRecovery(e -> Try.success("hello"));


        // Assert that the returned value contains the expected String
        assertEquals("hello", result);
    }

    @Test
    public void testIsOk() {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Assert that the isOk method returns true
        assertTrue(success.isOk());
    }

    @Test
    public void testIsErr() {
        // Create a Failure instance with an Exception
        Try<String> failure = Try.failure(new RuntimeException());

        // Assert that the isErr method returns true
        assertTrue(failure.isErr());
    }

    @Test
    public void testStream() {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Call the stream method
        Stream<String> result = success.stream();

        // Assert that the returned value is a Stream bundle "hello"
        Optional<String> opt = result.findFirst();
        assertTrue(opt.isPresent());
        assertEquals("hello", opt.get());
    }

    @Test
    public void testMap() throws Exception {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Call the map method with a Function that converts a String to an Integer
        Try<Integer> result = success.map(String::length);

        // Assert that the returned value is a Success instance
        assertTrue(result.isOk());

        // Assert that the returned value contains the expected Integer
        assertEquals(5, result.get());
    }

    @Test
    public void testFlatMap() throws Exception {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Call the flatMap method with a Function that converts a String to a Try<Integer>
        Try<Integer> result = success.flatMap(s -> Try.success(s.length()));

        // Assert that the returned value is a Success instance
        assertTrue(result.isOk());

        // Assert that the returned value contains the expected Integer
        assertEquals(5, result.get());
    }

    @Test
    public void testMap2() throws Exception {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Call the map2 method with a Function that converts a String to an Integer and another Strinto a Double
        Try<String> result = success.map2(Try.success(" world"), (s1, s2) -> s1 + s2);

        // Assert that the returned value is a Success instance
        assertTrue(result.isOk());

        // Assert that the returned value contains the expected Double
        assertEquals("hello world", result.get());
    }

    @Test
    public void testFlatMap2() throws Exception {
        // Create a Success instance with a String value
        Try<String> success = Try.success("hello");

        // Call the flatMap2 method with a Function that converts a String to a Try<Integer> and
        // another String to a Try<Double>
        Try<String> result = success.flatMap2(Try.success("world"), (s1, s2) -> {
            return Try.success(String.format("%s %s", s1, s2));
        });

        // Assert that the returned value is a Success instance
        assertTrue(result.isOk());

        // Assert that the returned value contains the expected Double
        assertEquals("hello world", result.get());
    }

    @Test
    public void testForeach() {
        // Create a mock Consumer<T> object
        Consumer<Integer> consumer = value -> assertEquals(42, value, "value should be 42");

        // Call the foreach method with a mock Consumer object
        Try.success(42).foreach(consumer);
    }

    @Test
    public void testAnyMatch() {
        // Create a mock Predicate<T> object
        java.util.function.Predicate<Integer> predicate = value -> true;

        // Call the anyMatch method with a mock Predicate object
        assertTrue(Try.success(50).anyMatch(predicate));
    }

    @Test
    public void testError() {
        // Create an exception to be thrown
        Exception exception = new Exception("Test exception");

        // Call the error method with the created exception
        assertInstanceOf(Exception.class, Try.failure(exception).error());
    }

    @Test
    public void testSuccess() throws Exception {

        // Call the success method with a mock Supplier object
        Try<Integer> try1 = Try.success(42);

        // Assert that the result is a Success<T> object
        assertTrue(try1.isOk(), "Try should be a Success");

        // Assert that the value inside the Success object is 42
        assertEquals(try1.get(), 42, "Value should be 42");
    }

    @Test
    public void testFailure() {

        // Call the failure method with a mock Supplier object
        Try<Integer> try1 = Try.failure(new Exception("Test exception"));

        // Assert that the result is a Failure<T> object
        assertTrue(try1.isErr(), "Try should be a Failure");


        // Assert that the exception inside the Failure object is not null
        assertNotNull(try1.error(), "Exception should not be null");

        // Assert that the exception message matches the expected message
        assertEquals("Test exception", try1.error().getMessage(), "Exception message should match");
    }

    @Test
    public void testTryIt() throws Exception {
        // Create a mock Supplier<T> object
        Supplier<Integer> supplier = () -> 42;

        // Call the tryIt method with a mock Supplier object
        Try<Integer> try1 = Try.tryIt(supplier);

        // Assert that the result is a Success<T> object
        assertTrue(try1.isOk(), "Try should be a Success");

        // Assert that the value inside the Success object is 42
        assertEquals(try1.get(), 42, "Value should be 42");
    }

    @Test
    public void testApply() throws Exception {
        // Create a mock Function<T, U> object
        Function<Integer, String> func = value -> "Hello, " + value;

        // Call the apply method with a mock Function object and an input argument
        Try<? extends String> try1 = Try.apply(func, 42);

        // Assert that the result is a Success<U> object
        assertTrue(try1.isOk(), "Try should be a Success");


        // Assert that the value inside the Success object is "Hello, 42"
        assertEquals(try1.get(), "Hello, 42", "Value should be 'Hello, 42'");
    }

    @Test
    public void testJoinMapSuccess() throws Exception {
        Try<String> t1 = Try.success("hello");
        Try<String> t2 = Try.success("world");
        BiFunction<String, String, String> func = (a, b) -> a + " " + b;
        Try<String> result = Try.joinMap(t1, t2, func);
        assertEquals("hello world", result.get());
    }

    @Test
    public void testJoinMapFailure() {
        Try<String> t1 = Try.failure(new Exception("error"));
        Try<String> t2 = Try.success("world");
        BiFunction<String, String, String> func = (a, b) -> a + " " + b;
        Try<String> result = Try.joinMap(t1, t2, func);
        assertThrowsExactly(Exception.class, result::get);
    }

    @Test
    public void testJoinFlatMapSuccess() throws Exception {
        Try<String> t1 = Try.success("hello");
        Try<String> t2 = Try.success("world");
        Try<String> result = Try.joinFlatMap(t1, t2, (a, b) -> Try.success(a + " " + b));
        assertEquals("hello world", result.get());
    }

    @Test
    public void testJoinFlatMapFailure() {
        Try<String> t1 = Try.failure(new Exception("error"));
        Try<String> t2 = Try.success("world");
        Try<String> result = Try.joinFlatMap(t1, t2, (a, b) -> Try.success(a + " " + b));
        assertThrowsExactly(Exception.class, result::get);
        assertEquals("error", result.error().getMessage());
    }

    @Test
    void testJoinMap3Success() throws Exception {
        // Given
        Try<String> t1 = Try.success("test");
        Try<Integer> t2 = Try.success(42);
        Try<Double> t3 = Try.success(3.14);
        TriFunction<String, Integer, Double, String> func =
                (String s, Integer i, Double d) -> s + " " + i.toString() + " " + d.toString();

        // When
        Try<String> result = Try.joinMap3(t1, t2, t3, func);

        // Then
        assertEquals("test 42 3.14", result.get());
    }

    @Test
    void testJoinMap3Failure() throws Exception {
        // Given
        Try<String> t1 = Try.success("test");
        Try<Integer> t2 = Try.failure(new Exception("Error"));
        Try<Double> t3 = Try.success(3.14);
        TriFunction<String, Integer, Double, String> func =
                (String s, Integer i, Double d) -> s + " " + i.toString() + " " + d.toString();
        // When
        Try<String> result = Try.joinMap3(t1, t2, t3, func);

        // Then
        assertThrowsExactly(Exception.class, result::get);
    }

    @Test
    void testJoinFlatMap3Success() throws Exception {
        // Given
        Try<String> t1 = Try.success("test");
        Try<Integer> t2 = Try.success(42);
        Try<Double> t3 = Try.success(3.14);

        // When
        Try<String> result =
                Try.joinFlatMap3(t1, t2, t3,
                        (s, i, d) -> Try.success("test " + s + " " + i.toString() + " " + d.toString()));

        // Then
        assertEquals("test test 42 3.14", result.get());
    }

    @Test
    void testJoinFlatMap3Failure() {
        // Given
        Try<String> t1 = Try.success("test");
        Try<Integer> t2 = Try.failure(new Exception("Error"));
        Try<Double> t3 = Try.success(3.14);


        // When
        Try<String> result = Try.joinFlatMap3(t1, t2, t3, (s, i, d) -> Try.failure(new Exception("Error")));

        // Then
        assertThrowsExactly(Exception.class, result::get);
        assertEquals("Error", result.error().getMessage());
    }
}
