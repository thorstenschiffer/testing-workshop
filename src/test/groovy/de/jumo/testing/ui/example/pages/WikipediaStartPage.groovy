package de.jumo.testing.ui.example.pages

import de.jumo.testing.ui.example.module.Headbar
import geb.Page

/**
 * Created by Thorsten Schiffer on 19.08.18.
 */
class WikipediaStartPage extends Page {

    static url = "/wiki/Wikipedia:Hauptseite"
    static at = { title == "Wikipedia – Die freie Enzyklopädie"}
    static content = {
        headbar {module Headbar}
        navigation {}
        ueberschrift {$("#ueberschrift")}
        namensEingeabefeld{ $("#name")}
        submitButton{$("input.submitName")}

    }
    
}
