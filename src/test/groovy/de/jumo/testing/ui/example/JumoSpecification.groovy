package de.jumo.testing.ui.example

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Thorsten Schiffer on 20.08.18.
 */
class JumoSpecification extends Specification {

    @Unroll
    def "Teste dass die Klasse #text zurückliefert"() {

        given:
        def testClass = new HelloWorld()

        when:
        testClass.setHelloWorld(text)

        then:
        testClass.getHelloWorld() == text
        and:
        1 * testClass.mokecdMethod()

        where:
        text | text2
        "Hello World" | 1
        "Test" | 1
        "Test2" | 1

    }
}
