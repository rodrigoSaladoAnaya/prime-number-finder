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
class NumberTest extends Specification {

  def "Test if a number is even"() {
    expect:
    Number.isEven(number) == response

    where:
    number | response
    0      | true
    1      | false
    2      | true
    3      | false
    4      | true
    5      | false
    6      | true
    7      | false
    8      | true
    9      | false
    10     | true
    11     | false
    12     | true
    13     | false
    14     | true
    15     | false
    16     | true
    17     | false
    18     | true
    19     | false
    20     | true
  }

  def "Test if a number is odd composite number"() {
    expect:
    Number.isOddComposite(number) == response

    where:
    number | response
    9      | true
    11     | false
    13     | false
    15     | true
    17     | false
    19     | false
  }

  def "Test if a number is prime number"() {
    expect:
    Number.isPrime(number) == response

    where:
    number | response
    1      | false
    2      | true
    3      | true
    5      | true
    7      | true
    9      | false
    11     | true
    13     | true
    15     | false
    17     | true
    19     | true
  }

  def "Test file with bigdecimal"() {
    setup:
    def file = this.getClass().getResource('/primes_1.txt')
    def primes = []
    file.eachLine { n ->
      primes << (n as int)
    }
    for(int i = 0; i < 100; i++) {
      boolean response = Number.isPrime(i)
      expect:
      assert primes.contains(i) == response
    }
  }
}
