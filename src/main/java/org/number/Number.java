/*

Copyright (C) 2019 Rodrigo Salado Anaya

Permission is hereby granted, free of charge, to any person obtaining a
copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software
is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package org.number;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.FLOOR;
import static ch.obermuhlner.math.big.BigDecimalMath.sqrt;

/**
 * @author rodrigo_salado
 */
public class Number {

  public final static BigDecimal TOW = BigDecimal.valueOf(2);
  public final static BigDecimal THREE = BigDecimal.valueOf(3);
  public final static BigDecimal FOUR = BigDecimal.valueOf(4);
  public final static BigDecimal SIX = BigDecimal.valueOf(6);

  public final static MathContext mathContext = new MathContext(15);

  public static boolean isEven(BigDecimal number) {
    return number.remainder(TOW).compareTo(ZERO) == 0;
  }

  public static boolean isOddComposite(BigDecimal number) {
    BigDecimal W = number.add(THREE).divide(SIX, CEILING).subtract(TOW);
    BigDecimal L = sqrt(W, mathContext).setScale(0, CEILING).subtract(TOW);
    BigDecimal R = L;
    if(R.compareTo(ZERO) < 0) {
      R = L.add(TOW);
    }
    BigDecimal siblings = ZERO;
    boolean test = false;
    for(BigDecimal i = R; i.compareTo(W) <= 0 && !test && siblings.compareTo(R.add(SIX)) <= 0; i = i.add(ONE)) {
      for(BigDecimal j = ZERO; j.compareTo(W) <= 0 && !test && siblings.compareTo(R.add(SIX)) <= 0; j = j.add(ONE)) {
        //System.out.format("%s, %s\n", i, j);
        BigDecimal next = TOW.multiply(i).add(THREE).multiply(TOW.multiply(j).add(THREE));
        BigDecimal diff = next.subtract(number);
        if(diff.compareTo(ZERO) == 0) {
          test = true;
        } else if(diff.abs().compareTo(SIX) == -1) {
          siblings = siblings.add(ONE);
        }
      }
    }
    System.out.format("N: %s, W: %s, L: %s, R: %s, siblings: %s\n", number, W, L, R, siblings);
    return test;

    //boolean response = false;
    /*BigDecimal base;
    BigDecimal index;
    BigDecimal j = ONE;
    BigDecimal jLimit = number.add(THREE).divide(SIX, CEILING).subtract(TOW);
    System.out.format("l: %s\n", jLimit);
    for(BigDecimal i = ZERO; i.compareTo(j) == -1 && !response; i = i.add(ONE)) {
      base = i.multiply(FOUR).add(SIX);
      index = number.divide(base, 16, CEILING).remainder(BigDecimal.valueOf(0.5));
      j = number.divide(base, FLOOR).subtract(ONE);
      boolean test = index.compareTo(ZERO) == 0;
      System.out.format("[%s] %s, %s\n", number, i, j);
      response = test;
    }
    return response;/**/
  }

  public static boolean isPrime(BigDecimal number) {
    if(number.compareTo(THREE) == 0) return true;
    if(number.compareTo(TOW) == 0) return true;
    if(number.compareTo(ONE) == 0) return false;
    if(isEven(number)) return false;
    if(isOddComposite(number)) return false;
    return true;
  }

}
