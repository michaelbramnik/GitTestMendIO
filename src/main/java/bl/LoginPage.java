package bl;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private Page page = null;
    private Locator loginForm = null;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void openLoginForm() {
        // Click sign-in button to navigate to user credentials form
        Locator signInButton = page.locator(".HeaderMenu-link--sign-in");
        assertThat(signInButton).isVisible();
        signInButton.click();

        // Make sure the form is visible to continue
        loginForm = page.locator(".auth-form-body");
        assertThat(loginForm).isVisible();
    };

    public void signIn(String username, String password, Boolean expectFail) {
        this.openLoginForm();
        this.loginForm.locator("#login_field").fill(username);
        this.loginForm.locator("#password").fill(password);
        this.loginForm.locator(".btn-primary").click();

        // Handle Device verification form
        if (expectFail) {
            assertThat(this.page.locator(".js-flash-alert")).hasText(
                    "Incorrect username or password.",
                    new LocatorAssertions.HasTextOptions().setUseInnerText(true));
        } else {
            assertThat(this.page.locator(".AppHeader")).isVisible();
            // Check the name of the logged-in user matched the provided credentials
        }

    }
}