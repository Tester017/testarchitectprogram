import { Page } from "@playwright/test";
import * as outlookdata from "../../data/outlookCreds.json";

export async function navigateToOutlook(page:Page) {
    await page.goto(outlookdata.url)
  }

  export async function enterUserName(page:Page) {
    await page.locator("[name='loginfmt']").fill(outlookdata.userName)
  }

  export async function enterPassword(page:Page) {
    await page.locator("[name='passwd']").fill(outlookdata.password)
  }

  export async function clickSubmitBtn(page:Page) {
    await page.locator("[type='submit']").click()
  }

