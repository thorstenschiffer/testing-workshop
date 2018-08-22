package de.jumo.testing.ui.example.module

import geb.Module

/**
 * Created by Thorsten Schiffer on 19.08.18.
 */
class Headbar extends Module {

    static content = {
        input { $("#searchInput") }
        submit { $("#searchButton") }
    }
}
