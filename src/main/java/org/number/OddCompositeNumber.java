package org.number;

import java.math.BigDecimal;
import java.math.MathContext;
import static org.number.Number.*;
import static java.math.RoundingMode.*;
import static java.math.BigDecimal.*;
import static java.lang.System.out;

public class OddCompositeNumber {

  public static boolean test(double N, double i) {
    out.printf("Number: [%s]\n", N);
    var isOddCompositeNumber = false;
    var L = Math.ceil(N/6) - 2;
    out.printf("+Limit: %s\n", L);
    double j = 0, B = 0, Q = 0, V = 0, C = 0;
    for(; i <= L; i++) {
      B = (i * 4) + 6;
      Q = N / B;
      V = Q % 0.5;
      isOddCompositeNumber = V == 0;
      j = Math.floor(N / B) - 1;
      C = (2 * i + 3) * (2 * j + 3);
      if((i != 0) && (i % 1_000_000  == 0)) {
        var percent = (i * 100) / L;
        out.printf("(%%%s) [%s]\n", percent, i);
        out.printf("   1) B: %s, Q: %s, V: %s -> (%s,%s) -> [%s:%s] == [%f]\n", B, Q, V, i, j, isOddCompositeNumber, C == N, C);
      }
      if(i >= j || isOddCompositeNumber) {
        break;
      }
    }
    out.printf("   2) B: %s, Q: %s, V: %s -> (%s,%s) -> [%s:%s] == [%f]\n", B, Q, V, i, j, isOddCompositeNumber, C == N, C);
    return isOddCompositeNumber;
  }

  public static boolean test(BigDecimal N, BigDecimal i) {
    var isOddComNum = false;
    var scale = 5;
    var MC_HALF_DOWN = new MathContext(scale, HALF_DOWN);
    var MC_FLOOR = new MathContext(scale, FLOOR);
    out.printf("Number: [%s]\n", N);
    BigDecimal L = N.divide(SIX, CEILING).subtract​(TWO);
    BigDecimal j = ZERO, B = ZERO, Q = ZERO, V = ZERO, C = ZERO;
    for(; i.compareTo(L) <= 0; i = i.add(ONE)) {
      B = i.multiply(FOUR, MC_HALF_DOWN).add(SIX);
      Q = N.divide(B, scale, HALF_DOWN);
      V = Q.remainder​(A_HALF);
      j = N.divide(B, 0, FLOOR).subtract​(ONE);

      if(i.compareTo(ZERO) != 0 && i.remainder(BigDecimal.valueOf(1_000_000)).compareTo(ZERO) == 0) {
        var percent = i.multiply(BigDecimal.valueOf(1_000)).divide(L, CEILING);
        out.printf("(%%%s) -> [%s,%s] %s %s\n", percent, i, j, j.subtract(i), scale);
      }

      isOddComNum = V.compareTo(ZERO) == 0;
      if(isOddComNum) {
        C = TWO.multiply(i, MC_HALF_DOWN).add(THREE).multiply(TWO.multiply(j, MC_HALF_DOWN).add(THREE), MC_HALF_DOWN);
        if(C.compareTo(N) != 0) {
          MC_HALF_DOWN = new MathContext(++scale, HALF_DOWN);
          i = i.subtract(ONE);
          out.printf("--Se incrementa la escala del contexto %s--\n", scale);
        } else {
          break;
        }
      } else if(i.compareTo(j) >= 0) {
        break;
      }

    }
    out.printf("   2) B: %f, Q: %f, V: %f -> (%s,%s) -> [%s:%s] == [%f]\n", B, Q, V, i, j, isOddComNum, C.compareTo(N) == 0, C);
    return isOddComNum;
  }

}
