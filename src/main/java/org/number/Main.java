package org.number;

import java.math.BigDecimal;

public class Main {

  public static void main(String... args) {
    var isPrime = PrimeNumber.test(args[0], args[1]);
    System.out.printf("\n\t\t\tNumber %s is prime? -> %s", args[0], isPrime);
  }

}
