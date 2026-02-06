import { Page } from "@playwright/test";
import { getConfig } from '../../../utils/envLoader';
const config = getConfig().config;

const search = "#top-search";
const oTrianglesLoader = "#loading_screen";
const dataGroup = "a[data-group='Account'] span";

export async function enterAccountName(page: Page, accountName: string, time?: number | 50000 ): Promise<void> {
    const activityPage = page.locator(search);
    const trianglesLoader = page.locator(oTrianglesLoader);
    const dataGroupPage = page.locator(dataGroup);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await activityPage.fill(accountName);
    await dataGroupPage.first().waitFor({ state: 'visible', timeout: time });
    await dataGroupPage.first().click();
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
}