import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

environments {

    chrome {
        System.properties["webdriver.chrome.driver"] = "/usr/local/lib/node_modules/appium-chromedriver/chromedriver/mac/chromedriver"
        driver = { new ChromeDriver() }
    }

    chromeHeadless {
        System.properties["webdriver.chrome.driver"] = "/usr/local/lib/node_modules/appium-chromedriver/chromedriver/mac/chromedriver"
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments('headless')
            new ChromeDriver(o)
        }
    }

    baseUrl = "http://de.wikipedia.org"
}