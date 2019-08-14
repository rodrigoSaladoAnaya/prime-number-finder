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
class FileWithPrimesTest extends Specification {

  def "Test file with double"() {
    setup:
    def file = this.getClass().getResource('/primes_5_999983.txt')
    def primes = []
    file.eachLine { n ->
      primes << (n as int)
    }
    for(int i = 0; i < primes.size(); i++) {
      def t1 = System.currentTimeMillis()
      if(primes[i+1] != null) {
        def next = primes[i+1]
        def previous = primes[i]
        def result = Number._nextPrimeFrom(previous)
        def t2 = System.currentTimeMillis() - t1
        if(next != result) {
          println "previous: ${previous},  next: ${next} -> result: ${result} == ${next == result} (${t2}ms)"
        }
        expect:
        next == result
      }
    }
  }

  def "Test file with bigdecimal"() {
    setup:
    def file = this.getClass().getResource('/primes_5_999983.txt')
    def primes = []
    file.eachLine { n ->
      primes << (n as int)
    }
    for(int i = 0; i < primes.size(); i++) {
      def t1 = System.currentTimeMillis()
      if(primes[i+1] != null) {
        def next = primes[i+1]
        def previous = primes[i]
        def result = Number.nextPrimeFrom(previous)
        def t2 = System.currentTimeMillis() - t1
        if(next != result) {
          println "previous: ${previous},  next: ${next} -> result: ${result} == ${next == result} (${t2}ms)"
        }
        expect:
        next == result
      }
    }
  }
}
