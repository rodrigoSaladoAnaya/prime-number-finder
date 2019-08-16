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

/**
 * @author rodrigo_salado
 */
public class Main {

  public static void main(String[] args) {
    float tStart = System.currentTimeMillis();
    BigDecimal previous = BigDecimalMath.toBigDecimal(args[0]);
    System.out.format("1) Finding next prime nextPrimeFrom [%s]\n", previous);
    BigDecimal next = Number.nextPrimeFrom(previous);
    float tDelta = (System.currentTimeMillis() - tStart);
    System.out.println("2) The next prime number nextPrimeFrom ["+previous+"] is ["+next+"]  ("+tDelta+"milliseconds)  ("+(tDelta/1000)+"seconds)  ("+(tDelta/1000/60)+"minutes)");
  }
}
