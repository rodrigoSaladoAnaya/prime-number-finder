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
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.FLOOR;

/**
 * @author rodrigo_salado
 */
public class Number {

  public final static BigDecimal TOW = BigDecimal.valueOf(2);
  public final static BigDecimal THREE = BigDecimal.valueOf(3);
  public final static BigDecimal FOUR = BigDecimal.valueOf(4);
  public final static BigDecimal SIX = BigDecimal.valueOf(6);

  public static boolean isEven(BigDecimal number) {
    return number.remainder(TOW).compareTo(ZERO) == 0;
  }

  public static boolean isOddComposite(BigDecimal number) {
    boolean response = false;
    BigDecimal j = ONE, base, index;
    for(BigDecimal i = ZERO; i.compareTo(j) == -1 && !response; i = i.add(ONE)) {
      base = i.multiply(FOUR).add(SIX);
      index = number.divide(base, 16, RoundingMode.CEILING).remainder(BigDecimal.valueOf(0.5));
      j = number.divide(base, FLOOR).subtract(ONE);
      response = index.compareTo(ZERO) == 0;
    }
    return response;
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
