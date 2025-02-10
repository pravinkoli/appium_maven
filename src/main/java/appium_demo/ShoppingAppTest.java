package appium_demo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class ShoppingAppTest {
    private static AndroidDriver driver;

    public static void main(String[] args) {
        try {
            setUp();
            performVisibleSwipe();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            tearDown();
        }
    }

    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 9 Pro Fold API 35");
        capabilities.setCapability("appPackage", "com.example.shoppingapp");
        capabilities.setCapability("appActivity", "com.example.shoppingapp.MainActivity");
        capabilities.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    public static void performVisibleSwipe() throws InterruptedException {
        System.out.println("Performing visible swipe...");
        WebElement recyclerView = driver.findElement(AppiumBy.className("androidx.recyclerview.widget.RecyclerView"));

        int startX = recyclerView.getLocation().getX() + (int) (recyclerView.getSize().getWidth() * 0.8);
        int endX = recyclerView.getLocation().getX() + (int) (recyclerView.getSize().getWidth() * 0.2);
        int y = recyclerView.getLocation().getY() + recyclerView.getSize().getHeight() / 2;

        System.out.println("startX: " + startX + " endX: " + endX + " y: " + y);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));

        System.out.println("Swipe completed.");
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}