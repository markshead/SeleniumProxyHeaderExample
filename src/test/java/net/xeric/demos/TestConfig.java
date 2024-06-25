package net.xeric.demos;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * This is the Spring configuration file that allows us to get the webdriver.
 */
@Configuration
public class TestConfig {

    private static final String MAC_CHROME_DRIVER = "./src/test/resources/bin/chromedriver";
    private static final String WINDOWS_CHROME_DRIVER = "./src/test/resources/bin/chromedriver.exe";

    @Bean(destroyMethod = "quit")
    @Lazy
    @Scope("singleton")
    public WebDriver getWebDriver() {

        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        int port = proxy.getPort();

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        proxy.addHeader("My-Header", "My-Header-Value");
        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);


        String driverPath = isWindows()? WINDOWS_CHROME_DRIVER : MAC_CHROME_DRIVER;
        System.setProperty("webdriver.chrome.driver", driverPath);
        //HtmlUnitDriver driver = new HtmlUnitDriver(capabilities);
      //  return driver;
        return new ChromeDriver(capabilities);
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}
