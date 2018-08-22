package de.jumo.testing.api.example

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * Created by Thorsten Schiffer on 19.08.18.
 */
@Stepwise
public class ExampleApiSpecification extends Specification {

    @Shared
    def newProductUrl

    @Shared
    def newVendorUrl

    def "Lege Lieferant an Produktliste"() {
        given:
        def restClient = new RESTClient( "https://api.predic8.de")

        when:
        def response = restClient.post path: "/shop/vendors/",
            requestContentType: ContentType.JSON,
            body: [name: "JUMO"]

        then:
        response.status == 201
        and:
        response.data.name == "JUMO"

        cleanup:
        newVendorUrl = response.data.vendor_url
    }

    def "Lege Erdbeeren an Produktliste"() {
        given:
        def restClient = new RESTClient( "https://api.predic8.de")
        def strawberries = [
            "name": "Erdbeeren",
            "price": 3.99,
            "category_url": "/shop/categories/Fruits",
            "vendor_url": newVendorUrl
        ]

        when:
        def response = restClient.post path: "/shop/products/", requestContentType: ContentType.JSON, body: strawberries

        then:
        response.status == 201
        and:
        response.data.name == "Erdbeeren"

        cleanup:
        newProductUrl = response.data.product_url
    }

    def "Hole Erdbeeren"() {
        given:
        def restClient = new RESTClient( "https://api.predic8.de")

        when:
        def response = restClient.get path: "${newProductUrl}"

        then:
        response.status == 200
        and:
        response.data.name == "Erdbeeren"
        response.data.price == 3.99
        response.data.category_url == "/shop/categories/Fruits"

    }

    def "Hole Erdbeeren beim Lieferanten"() {
        given:
        def restClient = new RESTClient( "https://api.predic8.de")

        when:
        def response = restClient.get path: "${newVendorUrl}/products/"

        then:
        response.status == 200
        and:
        response.data.products[0].name == "Erdbeeren"
        response.data.products[0].product_url == newProductUrl

    }

    def "Lösche Erdbeeren"() {
        given:
        def restClient = new RESTClient( "https://api.predic8.de")

        when:
        def response = restClient.delete path: "${newProductUrl}"

        then:
        response.status == 200

    }

    def "Lieferant hat keine Produkte mehr"() {
        given:
        def restClient = new RESTClient( "https://api.predic8.de")

        when:
        def response = restClient.get path: "${newVendorUrl}/products/"

        then:
        response.status == 200
        and:
        response.data.products.size == 0

    }

    def "Lösche Lieferant"() {
        given:
        def restClient = new RESTClient( "https://api.predic8.de")

        when:
        def response = restClient.delete path: "${newVendorUrl}"

        then:
        response.status == 200

    }


}
