package org.number;

import java.math.BigDecimal;
import java.math.MathContext;
import static org.number.Number.*;
import static java.math.RoundingMode.*;
import static java.math.BigDecimal.*;
import static java.lang.System.out;

public class OddCompositeNumber {

  public static boolean test(double N, double i) {
    var isOddComNum = false;
    var L = Math.ceil( N / 6 ) - 2;
    double j = 0, B = 0, Q = 0, V = 0, C = 0;
    for(; i <= L; i++) {
      B = (i * 4) + 6;
      Q = N / B;
      V = Q % 0.5;
      isOddComNum = V == 0;
      j = Math.floor(N / B) - 1;
      C = (2 * i + 3) * (2 * j + 3);
      if(i >= j || isOddComNum) {
        break;
      }
    }
    return isOddComNum;
  }

  public static boolean test(double N) {
    return test(N, 0);
  }

  public static boolean test(BigDecimal N, BigDecimal i) {
    var isOddComNum = false;
    var _scale_ = 5;
    var _mc_half_down_ = new MathContext(_scale_, HALF_DOWN);
    //out.printf("Number: [%s]\n", N);
    BigDecimal L = N.divide(SIX, CEILING).subtract​(TWO);
    BigDecimal j = ZERO, B = ZERO, Q = ZERO, V = ZERO, C = ZERO;
    for(; i.compareTo(L) <= 0; i = i.add(ONE)) {
      B = i.multiply(FOUR, _mc_half_down_).add(SIX);
      V = N.divide(B, _scale_, HALF_DOWN).remainder​(A_HALF);
      j = N.divide(B, 0, FLOOR).subtract​(ONE);

      if(i.compareTo(ZERO) != 0 && i.remainder(BigDecimal.valueOf(1_000_000)).compareTo(ZERO) == 0) {
        var percent = i.multiply(BigDecimal.valueOf(1_000)).divide(L, CEILING);
        out.printf("(%%%s) -> i: %s, j: %s, diff: %s, scale: %s\n", percent, i, j, j.subtract(i), _scale_);
      }

      isOddComNum = V.compareTo(ZERO) == 0;
      if(isOddComNum) {
        C = TWO.multiply(i, _mc_half_down_).add(THREE).multiply(TWO.multiply(j, _mc_half_down_).add(THREE), _mc_half_down_);
        if(C.compareTo(N) != 0) {
          _mc_half_down_ = new MathContext(++_scale_, HALF_DOWN);
          i = i.subtract(ONE);
        } else {
          break;
        }
      } else if(i.compareTo(j) >= 0) {
        break;
      }

    }
    //out.printf("   2) B: %f, Q: %f, V: %f -> (%s,%s) -> [%s:%s] == [%f]\n", B, Q, V, i, j, isOddComNum, C.compareTo(N) == 0, C);
    return isOddComNum;
  }

  public static boolean test(BigDecimal N) {
    return test(N, BigDecimal.ZERO);
  }

}
