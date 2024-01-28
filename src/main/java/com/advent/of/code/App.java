package com.advent.of.code;

import java.util.function.Function;
import java.util.logging.Logger;

/** Hello world! */
public class App {

  public abstract static class Result<T, E extends Throwable> {
    @FunctionalInterface
    public interface ThrowingRunnable<T, E extends Throwable> {
      T run() throws E;
    }

    private static final Logger LOGGER = Logger.getLogger(Result.class.getName());

    public static <T, E extends Throwable> Result<T, E> success(T value) {
      return new Success<>(value);
    }

    public static <T, E extends Throwable> Result<T, E> failure(E error) {
      return new Failure<>(error);
    }

    public static <T> Result<T, Throwable> tryCatch(ThrowingRunnable<T, Throwable> supplier) {
      try {
        return success(supplier.run());
      } catch (Throwable e) {
        LOGGER.fine(e.getMessage());
        return failure(e);
      }
    }

    public abstract T getOrElse(T defaultValue);

    public abstract <U> Result<U, E> map(Function<? super T, ? extends U> mapper);

    public abstract <U extends Throwable> Result<T, U> mapFailure(
        Function<? super E, ? extends U> mapper);

    public abstract <U> Result<U, E> flatMap(Function<? super T, Result<U, E>> mapper);

    public abstract T getOrElseThrow() throws E;

    private static class Success<T, E extends Throwable> extends Result<T, E> {
      private final T value;

      private Success(T value) {
        this.value = value;
      }

      @Override
      public T getOrElse(T defaultValue) {
        return value;
      }

      @Override
      public <U> Result<U, E> map(Function<? super T, ? extends U> mapper) {
        return new Success<>(mapper.apply(value));
      }

      @Override
      public <U extends Throwable> Result<T, U> mapFailure(
          Function<? super E, ? extends U> mapper) {
        return new Success<>(value);
      }

      @Override
      public <U> Result<U, E> flatMap(Function<? super T, Result<U, E>> mapper) {
        return mapper.apply(value);
      }

      @Override
      public T getOrElseThrow() {
        return value;
      }
    }

    private static class Failure<T, E extends Throwable> extends Result<T, E> {
      private final E error;

      private Failure(E error) {
        this.error = error;
      }

      @Override
      public T getOrElse(T defaultValue) {
        return defaultValue;
      }

      @Override
      public <U> Result<U, E> map(Function<? super T, ? extends U> mapper) {
        return new Failure<>(error);
      }

      @Override
      public <U extends Throwable> Result<T, U> mapFailure(
          Function<? super E, ? extends U> mapper) {
        return new Failure<T, U>(mapper.apply(error));
      }

      @Override
      public <U> Result<U, E> flatMap(Function<? super T, Result<U, E>> mapper) {
        return new Failure<>(error);
      }

      @Override
      public T getOrElseThrow() throws E {
        throw error;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("Hello World!");
    Result.success("Hello World!").map(s -> s.length());
    Result.failure(new RuntimeException("Hello World!"));
    var temp = Result.tryCatch(() -> extracted()).map(s -> s.length());
    System.out.println(temp);
  }

  private static String extracted() throws Exception {
    throw new Exception();
  }
}
