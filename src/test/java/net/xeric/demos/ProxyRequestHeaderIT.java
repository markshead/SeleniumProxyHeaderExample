package net.xeric.demos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProxyRequestHeaderIT {
    @Autowired
    WebDriver webdriver;
    @Test
    public void testHeaders() throws Exception {
        webdriver.get("http://pi.xeric.net:10000/test");
//        webdriver.get("https://manytools.org/http-html-text/http-request-headers/");
//        assertTrue(webdriver.getPageSource().contains("Chrome"));
//        assertTrue(webdriver.getPageSource().contains("My-Header"));
//        assertTrue(webdriver.getPageSource().contains("My-Header-Value"));


    }

}
