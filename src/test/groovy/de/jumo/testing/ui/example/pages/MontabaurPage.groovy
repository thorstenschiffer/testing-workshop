package de.jumo.testing.ui.example.pages


import geb.Page
/**
 * Created by Thorsten Schiffer on 19.08.18.
 */
class MontabaurPage extends Page {

    static url = "http://www.montabaur.de/"
    static at = { title == "Presseinformationen | Stadt Montabaur"}
    static content = {
        headline(wait: true) { $("#headline span").text() }
        navigation(wait: true) { $("ul.navigation li a") }


    }
    
}
