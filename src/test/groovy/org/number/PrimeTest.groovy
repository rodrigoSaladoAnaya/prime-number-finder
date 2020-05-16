package org.number

import spock.lang.Specification

class PrimeTest extends Specification {

  /*def "Test prime numer"() {
    def number = 162259276829213363391578010288127
    expect: PrimeNumber.test(number)
  }/**/

  def "Test file prime numbers"() {
    setup:
    def file = this.getClass().getResource('/prime-numbers-list.txt')
    def primes = []
    file.eachLine { n ->
      primes << (n as int)
    }
    for(def i = 11; i <= primes.last(); i+=2) {
      boolean response = PrimeNumber.test(i)
      //boolean response = !OddCompositeNumber.test(i)
      println "TEST -> i: ${i}, response: ${response}"
      expect:
      assert primes.contains(i) == response
    }
  }/**/

}
