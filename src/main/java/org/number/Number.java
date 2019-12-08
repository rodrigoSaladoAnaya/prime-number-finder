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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

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
  public final static BigDecimal SIX = BigDecimal.valueOf(6);

  final static Logger log = LoggerFactory.getLogger("org.number.Number");

  public static boolean isOddComposite(BigDecimal number) {
    log.info("Number -> {}", number);
    BigDecimal z = ZERO;
    BigDecimal limit = ZERO;
    for(;limit.compareTo(number) < 0; z = z.add(BigDecimal.ONE)) {
      limit = TOW.multiply(z).add(THREE).multiply(TOW.multiply(z).add(THREE));
    }
    log.info("Limit: {}, Z: {}", limit, z);

    boolean result = false;
    BigDecimal j;
    BigDecimal base;
    BigDecimal quotient;
    for(BigDecimal i = ZERO; !result && i.compareTo(z) < 0; i = i.add(ONE)) {
      base = i.multiply(FOUR).add(SIX);
      quotient = number.divide(base, 16, CEILING).remainder(BigDecimal.valueOf(0.5));
      result = quotient.compareTo(ZERO) == 0;

      if(result) {
        j = number.divide(base, FLOOR).subtract(ONE);
        BigDecimal next = TOW.multiply(i).add(THREE).multiply(TOW.multiply(j).add(THREE));
        log.info("({},{}) -> {} :: {}", i, j, result?"<<OK>>": "", next);
      }
    }
    return !result;
  }
}
