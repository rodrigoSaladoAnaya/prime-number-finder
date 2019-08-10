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

/**
 * @author rodrigo_salado
 */
public class Number {

  public static boolean isOddCompositeNumber(double number) {
    boolean isValid = false;
    double jLimit = Math.ceil(Math.log(number) + number / 2);
    double iLimit = Math.ceil(Math.sqrt(number - jLimit) - 2);
    for(float i = 0; i <= iLimit && !isValid; i++) {
      for(double j = i; j <= jLimit && !isValid; j++) {
        double next = (2 * i + 3) * (2 * j + 3);
        if(number + iLimit < next) { break; }
        if(next == number) {
          isValid = true;
        }
      }
    }
    return isValid;
  }

  public static double nextPrimeFrom(double previous) {
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
      if(modHead == 0 && modTail == 1 && !Number.isOddCompositeNumber(number)) {
        next = number;
      }
    }
    return next;
  }

}
