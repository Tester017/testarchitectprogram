import { Frame, Page } from "@playwright/test";

export async function enterToField(page:Page|Frame,data:string) {
    await page.locator("#search_include_listsearch input").fill(data);
    await page.locator(".Dropdown li").filter({hasText:data}).nth(0).click();
}

export async function clickSendNow(page:Page|Frame) {
    await page.locator("#sendNow").click()
}

export async function clickSendNowConfirmaton(page:Page|Frame) {
    await page.locator("#mailSendNowYes").click()
}