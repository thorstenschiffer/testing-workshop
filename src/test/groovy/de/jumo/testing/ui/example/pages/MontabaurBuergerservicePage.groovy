package de.jumo.testing.ui.example.pages


import geb.Page
/**
 * Created by Thorsten Schiffer on 19.08.18.
 */
class MontabaurBuergerservicePage extends Page {

    static url = "http://www.montabaur.de/montabaur/de/BÜRGERSERVICE/"
    static at = { title == "BÜRGERSERVICE | Stadt Montabaur"}
    static content = {
        headline(wait: true) { $("#headline span").text() }
        navigation(wait: true) { $("ul.navigation li a") }


    }
    
}
