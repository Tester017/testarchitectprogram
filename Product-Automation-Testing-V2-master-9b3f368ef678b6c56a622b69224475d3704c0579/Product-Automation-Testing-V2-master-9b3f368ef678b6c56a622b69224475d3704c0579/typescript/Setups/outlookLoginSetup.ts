import { test, expect } from '@playwright/test';
import * as outlookLogin from '../pages/outlook/loginpage';
import * as outlookhome from '../pages/outlook/homepage';



test('Outlook Login', async ({ page }) => {

    await outlookLogin.navigateToOutlook(page)
    await outlookLogin.enterUserName(page)
    await outlookLogin.clickSubmitBtn(page)
    await outlookLogin.enterPassword(page);
    await outlookLogin.clickSubmitBtn(page);
    // await outlookhome.waitforhomePageNewIcon(page,100000);
    await page.waitForTimeout(90000);
    await page.context().storageState({ path: "./OutlookLoginAuth.json" });
    
  });