import { test, expect } from '@playwright/test';
import * as outlookLogin from '../pages/outlook/loginpage';
import * as outlookhome from '../pages/outlook/homepage';
import * as prismdata from "../data/prismCreds.json";



test('prism Login', async ({ page }) => {

  await page.goto(prismdata.url);
  await page.locator('#login_type').selectOption({ label: "Prism Login" });
  await page.fill("#email", prismdata.userName);
  await page.fill("#paswd", prismdata.password);
  await page.locator('button:has-text("Login")').click();

  const commissionBySectorsContainer = page.locator("div.ibox.panel-content").nth(1);
  await commissionBySectorsContainer.waitFor({ state: 'visible', timeout: 10000 });
  await page.context().storageState({ path: "./PrismLoginAuth.json" });


});