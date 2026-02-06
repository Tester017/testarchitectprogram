import { test, expect } from '@playwright/test';
import * as outlookLogin from '../pages/outlook/loginpage';
import * as outlookHomepage from '../pages/outlook/homepage';
import * as prismLogin from '../pages/prismaddin/LoginPage';
import * as prismBulkMailPage from '../pages/prismaddin/BulkmailPage'
import * as emailContent from '../data/EmailContent'

test('Outlook Login', async ({ page }) => {

  await outlookLogin.navigateToOutlook(page)
  await outlookHomepage.clickNewEmailIcon(page)
  await outlookHomepage.clickMessageTab(page)
  await outlookHomepage.clickAppsIcon(page)
  await outlookHomepage.clickWebAddinIcon(page)
  await outlookHomepage.selectSendBulkMail(page)
  const prismFrame = await outlookHomepage.getPrismFrame(page)
  await prismLogin.enterUserName(prismFrame);
  await prismLogin.enterPassword(prismFrame);
  await prismLogin.clickSubmitBtn(prismFrame);
  await prismBulkMailPage.enterToField(prismFrame,"Testautomationstaticlist")
  await outlookHomepage.fillSubject(page,emailContent.emailSubject)
  await outlookHomepage.fillbody(page,emailContent.emailBody);
  await prismBulkMailPage.clickSendNow(prismFrame)
  await prismBulkMailPage.clickSendNowConfirmaton(prismFrame)

  // await bulkMailPage.searchAndClickList(prismFrame, bulkMailTestData.listName);
  // await bulkMailPage.enterSubject(page, "From Shamily Automation mail");
  // await bulkMailPage.clickSendNow(prismFrame);
  // await bulkMailPage.clickConfirmButton(prismFrame)
  
  

  await page.goto("https://qa1.datazoic.com/prismST/home.html");
  await page.locator("//div[contains(@class,'sideMenubtn')]//span[contains(@class,'icon-three ')]").click();
  await page.locator("//span[text()='My Lists']").click();
  await page.locator("//a[text()='My Lists V3']").click();

  await page.waitForSelector("//div[@id='loading_screen' and contains(@style,'block')]", {state: 'hidden', 
                                                                                          timeout: 50000});
  await page.locator("button[class*='bulkMailHistory']").click();

  });