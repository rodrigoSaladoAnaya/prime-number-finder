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

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static ch.obermuhlner.math.big.BigDecimalMath.log;
import static ch.obermuhlner.math.big.BigDecimalMath.sqrt;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.FLOOR;

/**
 * @author rodrigo_salado
 */
public class Number {

  public final static BigDecimal TOW = BigDecimal.valueOf(2);
  public final static BigDecimal THREE = BigDecimal.valueOf(3);
  public final static BigDecimal FOUR = BigDecimal.valueOf(4);
  public final static BigDecimal FIVE = BigDecimal.valueOf(5);
  public final static MathContext mathContext = new MathContext(15);


  public static boolean _isOddCompositeNumber(double number) {
    boolean isValid = false;
    double base = 2;
    double aLimit = Math.ceil(Math.log(number) + number / 2);
    double bLimit = Math.ceil(Math.sqrt(number - aLimit));
    double iLimit = Math.ceil(bLimit - (bLimit / 4));
    for(double i = 0; i < iLimit && !isValid; i++) {
      base += 4;
      double quotient = number / base;
      isValid = quotient % 0.5 == 0;
      if(isValid) {
        double j = Math.floor(number / base) - 1;
        double next = (2 * i + 3) * (2 * j + 3);
        isValid = next == number;
      }
    }
    return isValid;
  }

  public static boolean isOddCompositeNumber(BigDecimal number) {
    boolean isValid = false;
    BigDecimal base = TOW;
    BigDecimal aLimit = log(number, mathContext).add(number.divide(TOW)).setScale(0, CEILING);
    BigDecimal bLimit = sqrt(number.subtract(aLimit), mathContext).setScale(0, CEILING);
    BigDecimal iLimit = bLimit.subtract(bLimit.divide(FOUR)).setScale(0, CEILING);
    for(BigDecimal i = ZERO; i.compareTo(iLimit) == -1 && !isValid; i = i.add(ONE)) {
      base = base.add(FOUR);
      BigDecimal quotient = number.divide(base, 16, RoundingMode.CEILING);
      isValid = quotient.remainder(BigDecimal.valueOf(0.5)).compareTo(ZERO) == 0;
      if(isValid) {
        BigDecimal j = number.divide(base, FLOOR).subtract(ONE);
        BigDecimal next = TOW.multiply(i).add(THREE).multiply(TOW.multiply(j).add(THREE));
        if(i.compareTo(iLimit) != -1) {
          System.out.println("["+number+"]  bLimit: " + bLimit + ", iLimit: " + iLimit + ", i: " + i);
        }
        isValid = next.equals(number);
      }
    }
    return isValid;
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
