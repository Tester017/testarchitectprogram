import { Frame, Page } from "@playwright/test";
import * as prismdata from "../../data/prismCreds.json";

export async function enterUserName(page:Page|Frame) {
    await page.locator("#email").fill(prismdata.userName);
}

export async function enterPassword(page:Page|Frame) {
    await page.locator("#paswd").fill(prismdata.password);
}

export async function clickSubmitBtn(page:Page|Frame) {
    await page.getByRole('button', { name: 'Login' }).click();
}