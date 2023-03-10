package com.instaimg.crawl.login;

import com.instaimg.crawl.config.UserProperty;
import com.instaimg.crawl.config.WebProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InstagramLoginHandler {
    private WebDriver driver;
    private String instagramURI;

    public static final String APP_ID_KEY = "X-IG-App-ID";
    public static final String SESSION_ID_KEY = "sessionid";

    public Map<String, String> getLoginData() throws InterruptedException {
        login();
        // 페이지 로딩 대기 시간
        Thread.sleep(5000);

        Map<String, String> require = new ConcurrentHashMap<>();
        String sessionid = getSessionId(driver.manage().getCookies());
        require.put("sessionid", sessionid);

        String AppID = "X-IG-App-ID";
        String xIgAppId = getXIgAppId(driver.getPageSource(), AppID);
        require.put(AppID, xIgAppId);

        driver.quit();
        return require;
    }

    private void login() {
        System.setProperty("webdriver.chrome.driver", "C:/chromeDriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        this.instagramURI = "https://www.instagram.com";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(instagramURI);

        driver.findElement(By.name("username")).sendKeys("kiwee012");
        driver.findElement(By.name("password")).sendKeys("xxibgdr5658!");
        driver.findElement(By.cssSelector("._acan._aiit._acap._aijb._acas._aj1-")).submit();
    }

    private String getSessionId(Set<Cookie> cookies) throws RuntimeException{
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionid")) {
                return cookie.getValue();
            }
        }
        throw new RuntimeException("sessionid 를 가져오지 못했습니다.");
    }

    private String getXIgAppId(String pageSource, String appID) {
        int startIndex = pageSource.indexOf(appID);
        int endIndex = pageSource.indexOf(",", startIndex);
        return pageSource.substring(startIndex, endIndex).replaceAll("[^0-9]", "");
    }
}
