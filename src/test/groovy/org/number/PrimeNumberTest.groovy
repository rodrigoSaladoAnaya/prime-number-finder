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

package org.number

import spock.lang.Specification

/**
 * @author rodrigo_salado
 */
class PrimeNumberTest extends Specification {

  def "Test all number from 104743 to 104759"() {
    setup:
    BigDecimal fromN = 104743
    BigDecimal toN = 104759

    (fromN..toN).each { number ->
      def t1 = System.currentTimeMillis()
      BigDecimal result = Number.nextPrimeFrom(number)
      def t2 = System.currentTimeMillis() - t1
      println "${number} -> ${result} == ${toN} (${t2}ms)"
      expect:
      result == toN
    }
  }
}
