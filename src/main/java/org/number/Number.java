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

  public static boolean _isOddCompositeNumber(double number) {
    boolean result = false;
    double j = 1;
    for(double i = 0; i < j && !result; i++) {
      double base = ( i * 4 ) + 6;
      double quotient = number / base;
      result = quotient % 0.5 == 0;
      j = Math.floor(number / base) - 1;
    }
    return result;
  }

  public static boolean isOddCompositeNumber(BigDecimal number) {
    boolean result = false;
    BigDecimal j = ONE;
    for(BigDecimal i = ZERO; i.compareTo(j) == -1 && !result; i = i.add(ONE)) {
      BigDecimal base = i.multiply(FOUR).add(SIX);
      BigDecimal quotient = number.divide(base, 16, RoundingMode.CEILING);
      result = quotient.remainder(BigDecimal.valueOf(0.5)).compareTo(ZERO) == 0;
      j = number.divide(base, FLOOR).subtract(ONE);
    }
    return result;
  }

  public static double _nextPrimeFrom(double previous) {
    if(previous % 2 == 0) {
      previous--;
    }
    double next = 0;
    double head = previous - 2;
    double tail = 2;
    for(double number = previous + 1; next == 0; number++) {
      int modHead = (int)(number - head) % 2;
      int modTail = (int)(number - tail) % 2;
      if(number < 0) {
        throw new RuntimeException("Posible number overflow.");
      }
      if(modHead == 0 && modTail == 1 && !Number._isOddCompositeNumber(number)) {
        next = number;
      }
    }
    return next;
  }

  public static BigDecimal nextPrimeFrom(BigDecimal previous) {
    if(previous.remainder(TOW).compareTo(ZERO) == 0) {
      previous = previous.subtract(ONE);
    }
    BigDecimal next = ZERO;
    BigDecimal head = previous.subtract(TOW);
    BigDecimal tail = TOW;
    for(BigDecimal number = previous.add(ONE); next.compareTo(ZERO) == 0; number = number.add(ONE)) {
      int modHead = number.subtract(head).remainder(TOW).intValue();
      int modTail = number.subtract(tail).remainder(TOW).intValue();
      if(modHead == 0 && modTail == 1 && !Number.isOddCompositeNumber(number)) {
        next = number;
      }
    }
    return next;
  }

}
