package tests;

import bl.LoginPage;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.*;


public class TestGitHubSanity {
    Playwright playwright;
    Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeClass
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    }

    @AfterClass
    void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://github.com/");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    @AfterMethod
    void closeContext() {
        context.close();
    }

    @Test
    void loginToGithub() {
        LoginPage loginPage = new LoginPage(page);
        // Pass on valid credentials
        loginPage.signIn("aaa", "bbb", false);
    }

    @Test
    void loginToGithubNegative() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.signIn("111", "222", true);
    }
}