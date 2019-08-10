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
class OddCompositeNumberTest extends Specification {

  def "Test file from 3"() {
    setup:

    BigDecimal number = BigDecimal.valueOf(195)
    def isOddComNum1 = Number.isOddCompositeNumber(number)
    def isOddComNum2 = Number._isOddCompositeNumber(number)
    expect:
    isOddComNum1 == isOddComNum2
  }

  def "Test file from 4"() {
    setup:

    BigDecimal number = BigDecimal.valueOf(195)
    def isOddComNum1 = Number.nextPrimeFrom(number)
    def isOddComNum2 = Number._nextPrimeFrom(number)
    expect:
    isOddComNum1 == isOddComNum2
  }
}
