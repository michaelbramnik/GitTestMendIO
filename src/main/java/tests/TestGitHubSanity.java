package tests;

import bl.LoginPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.*;


public class TestGitHubSanity extends BaseTest {

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