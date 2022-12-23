package com.instaimg.crawl;

import com.instaimg.crawl.config.UserProperty;
import com.instaimg.crawl.config.WebProperty;
import com.instaimg.crawl.login.InstagramLoginHandler;
import org.apache.http.client.fluent.Executor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class SeleniumTest {
    private static WebDriver driver;
    private static String url;

    // 드라이버 설치 경로
    @Autowired
    private WebProperty webProperties;
    @Autowired
    private UserProperty userProperties;

//    @BeforeAll
    public void setUp() {
        System.setProperty(webProperties.getId(), webProperties.getPath());

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");

        driver = new ChromeDriver(options);

        url = "https://www.instagram.com";
    }

//    @Test
    public void run() throws IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(url);

        driver.findElement(By.name("username")).sendKeys(userProperties.getId());
        driver.findElement(By.name("password")).sendKeys(userProperties.getPw());
        driver.findElement(By.cssSelector("._acan._aiit._acap._aijb._acas._aj1-")).submit();

        Thread.sleep(5000); // 3. 페이지 로딩 대기 시간
        Set<Cookie> cookies = driver.manage().getCookies();
//        driver.quit();
        for (Cookie cookie : cookies) {
            System.out.println("cookie = " + cookie.getName());
        }

        Executor executor = Executor.newInstance();
//        System.out.println(executor.use(cookieStore).execute(Request.Get(url)).returnContent().asString(Charset.forName("UTF-8")));
        String AppID = "X-IG-App-ID";
        String pageSource = driver.getPageSource();

        int startIndex = pageSource.indexOf(AppID);
        int endIndex = pageSource.indexOf(",", startIndex);
        char[] chars = pageSource.substring(startIndex, endIndex).toCharArray();
        StringBuilder sb= new StringBuilder();
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
        }
        System.out.println("App id = " + sb);
    }

    @Test
    void getRequired() throws InterruptedException {
        InstagramLoginHandler loginHandler = new InstagramLoginHandler(webProperties, userProperties);
        Map<String, String> loginData = loginHandler.getLoginData();
        for (Map.Entry entry : loginData.entrySet()) {
            System.out.println("KEY = " + entry.getKey() + "VALUE = " + entry.getValue());
        }
    }

    @Test
    void getDS() {
        String asdf = "asdf=\"1234asf";
        System.out.println(asdf.replaceAll("[^0-9]", ""));
    }
}
