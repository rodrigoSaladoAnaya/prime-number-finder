package org.number;

import java.math.BigDecimal;
import static org.number.Number.*;
import static java.math.RoundingMode.*;
import static java.math.BigDecimal.*;
import static java.lang.System.out;

public class PrimeNumber {

  public static boolean test(BigDecimal N, BigDecimal i) {
    var result = false;
    if(N.remainder(TWO).compareTo(ZERO) != 0) {
      result = !OddCompositeNumber.test(N, i);
    }
    return result;
  }

  public static boolean test(BigDecimal N) {
    var result = false;
    if(N.remainder(TWO).compareTo(ZERO) != 0) {
      result = !OddCompositeNumber.test(N, BigDecimal.ZERO);
    }
    return result;
  }

  public static boolean test(String N, String i) {
    return test(new BigDecimal(N), new BigDecimal(i));
  }

  public static boolean test(String N) {
    return test(new BigDecimal(N));
  }
}
