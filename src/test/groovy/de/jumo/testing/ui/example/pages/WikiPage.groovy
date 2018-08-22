package de.jumo.testing.ui.example.pages

import de.jumo.testing.ui.example.module.Headbar
import geb.Page
/**
 * Created by Thorsten Schiffer on 19.08.18.
 */
class WikiPage extends Page {

    String pageTitle

    static at = { title == "${pageTitle} – Wikipedia"}
    static content = {
        headline { $("#firstHeading").text() }
        weblinks { $('#Weblinks').parent().next("ul").find("li a") }
        headbar { module Headbar }

    }
    
}
