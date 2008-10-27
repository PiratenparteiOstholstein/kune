package org.ourproject.kune.platf.integration.selenium;

import org.junit.Before;
import org.ourproject.kune.workspace.client.signin.RegisterForm;
import org.ourproject.kune.workspace.client.signin.RegisterPanel;
import org.ourproject.kune.workspace.client.signin.SignInForm;
import org.ourproject.kune.workspace.client.signin.SignInPanel;
import org.ourproject.kune.workspace.client.sitebar.sitesign.SiteSignInLinkPanel;
import org.ourproject.kune.workspace.client.sitebar.sitesign.SiteSignOutLinkPanel;
import org.ourproject.kune.workspace.client.sitebar.siteusermenu.SiteUserMenuPanel;
import org.ourproject.kune.workspace.client.title.EntityTitlePanel;

public class KuneSeleniumTestHelper extends SeleniumTestHelper {

    @Before
    public void before() {
        selenium.deleteAllVisibleCookies();
        selenium.refresh();
    }

    protected void ifLoggedSigOut() {
        if (selenium.getText(gid(SiteSignOutLinkPanel.SITE_SIGN_OUT)).indexOf("admin") > 0) {
            signOut();
        }
    }

    protected void openDefPage() throws Exception {
        open("/kune/?locale=en#site");
        waitForTextInside(gid(EntityTitlePanel.ENTITY_TITLE_RIGHT_TITLE), "Welcome to kune");
    }

    protected void register(String shortName, String longName, String passwd, String passwdDup, String email,
            String country, String language, String tz, boolean wantHomepage) {
        click(gid(SiteSignInLinkPanel.SITE_SIGN_IN));
        click(gid(SignInPanel.CREATE_ONE));
        type(RegisterForm.NICK_FIELD, shortName);
        type(RegisterForm.LONGNAME_FIELD, longName);
        type(RegisterForm.PASSWORD_FIELD, passwd);
        type(RegisterForm.PASSWORD_FIELD_DUP, passwdDup);
        type(RegisterForm.EMAIL_FIELD, email);
        type(RegisterForm.LANG_FIELD, language);
        type(RegisterForm.COUNTRY_FIELD, country);
        type(RegisterForm.TIMEZONE_FIELD, tz);
        // div[6]/div[1]/div/img
        click("//div[6]/div[1]/div/img");
        click("//div[text()='" + language + "']");
        click("//div[7]/div[1]/div/img");
        click("//div[text()='" + country + "']");
        click("//div[8]/div[1]/div/img");
        click("//div[text()='" + tz + "']");
        // "xpath=//div\[contains(@style,'visible')\]/div\[@class='x-combo-list-inner']/div[text()='"ItemTextValue"'\]";
        type("//div[@id='k-regp-p']/div/div/div/div/div/div/div/div/form/div[6]/div/div/input[2]", language);
        type("//div[@id='k-regp-p']/div/div/div/div/div/div/div/div/form/div[7]/div/div/input[2]", country);
        type("//div[@id='k-regp-p']/div/div/div/div/div/div/div/div/form/div[8]/div/div/input[2]", tz);

        if (wantHomepage) {
            click(RegisterForm.WANNAPERSONALHOMEPAGE_ID);
        } else {
            click(RegisterForm.NOPERSONALHOMEPAGE_ID);
        }
        click(RegisterPanel.REGISTER_BUTTON_ID);
    }

    protected void signIn() {
        signIn("admin", "easyeasy");
    }

    protected void signIn(String nick, String passwd) {
        click(gid(SiteSignInLinkPanel.SITE_SIGN_IN));
        type(SignInForm.NICKOREMAIL_FIELD, nick);
        type(SignInForm.PASSWORD_FIELD, passwd);
        click(SignInPanel.SIGN_IN_BUTTON_ID);
    }

    protected void signOut() {
        click("gwt-debug-k-ssolp-lb");
    }

    protected void verifyLoggedUserShorName(String userShortName) throws Exception {
        waitForTextInside(gid(SiteUserMenuPanel.LOGGED_USER_MENU), userShortName);
    }
}
