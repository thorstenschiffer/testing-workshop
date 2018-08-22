package de.jumo.testing.ui.example

import de.jumo.testing.ui.example.pages.MontabaurBuergerservicePage
import de.jumo.testing.ui.example.pages.MontabaurPage
import de.jumo.testing.ui.example.pages.WikipediaStartPage
import de.jumo.testing.ui.example.pages.WikiPage
import geb.spock.GebSpec
import spock.lang.Unroll

/**
 * Created by Thorsten Schiffer on 19.08.18.
 */
@User("karl", "password123")
class ExampleSpecification extends GebSpec {

    def "text"() {
        given:
        to WikipediaStartPage

        when:
        namensEingabfeld.value("Thorsten")
        and:
        submitButton.click()

        then:
        ueberschrift.text() "Thorsten"
    }

    def "Prüfe Montabaur Seite"() {
        given:
        to MontabaurPage

        expect:
        headline == "AKTUELLES"
        navigation[0].text() == "BÜRGERSERVICE"
        and:
        navigation[0].click()
        at MontabaurBuergerservicePage

    }

    def "Gehe zur Wikipedia Seite"() {
        given:"Ich öffne die Wikipedia Startseite"
        to WikipediaStartPage

        when:"Ich gebe in das Suchfeld den Begriff Montabaur ein"
        headbar.input.value("Montabaur")

        and:"Klicke auf den Such Button"
        headbar.submit.click()

        then:"Soll ich zur Wikipedia Seite von Montabaur wechseln"
        at new WikiPage(url: "/wiki/Montabaur", pageTitle: "Montabaur")

        and:"Auf diese Seite soll der erste Weblink auf die Homepage von Montabaur verweisen"
        weblinks[0].attr("href") == "http://www.montabaur.de/"

    }


    def "Gehe zur Wikipedia Seite und suche nach Montabaur"() {
        given:"Ich öffne die Wikipedia Startseite"

        when:"Ich gebe in das Suchfeld den Begriff Montabaur ein"
        and:"Klicke auf den Such Button"

        then:"Soll ich zur Wikipedia Seite von Montabaur wechseln"
        and:"Auf diese Seite soll der erste Weblink auf die Homepage von Montabaur verweisen"
    }

    @Unroll
    def "Prüfe die Wikipedia Seite #pageTitle"() {

        given:
        to new WikiPage(url: url, pageTitle: pageTitle)

        expect:
        headline == pageTitle

        where:
        url                                 | pageTitle
        "/wiki/Java_(Programmiersprache)"   | "Java (Programmiersprache)"
        "/wiki/Mount_Everest"               | "Mount Everest"
        "/wiki/Groovy"                      | "Groovy"



    }
}
