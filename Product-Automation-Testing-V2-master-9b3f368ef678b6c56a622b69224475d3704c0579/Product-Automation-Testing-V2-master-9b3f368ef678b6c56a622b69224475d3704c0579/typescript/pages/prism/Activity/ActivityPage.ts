import { Page } from "@playwright/test";

const activityPageName = "li[data-pagename='Activity']";
const oTrianglesLoader = "#loading_screen";
const oActivityTableData = "[data-fixedid='tcactivitytabledata'][id*=activitylist]";
const oActivityTiles = ".activitytiles > .activity_slider_ibox"

export async function waitForActivityPage(page: Page, time?: number | 50000): Promise<void> {
    const activityPage = page.locator(activityPageName);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await activityPage.waitFor({ state: 'visible', timeout: time });
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });

}

export async function waitForActivityTiles(page: Page, time?: number | 50000): Promise<void> {
    const activityTiles = page.locator(oActivityTiles);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await activityTiles.waitFor({ state: 'visible', timeout: time });
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });

}

export async function waitForActivityTable(page: Page, time?: number | 50000): Promise<void> {
    const activityTable = page.locator(oActivityTableData);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await activityTable.waitFor({ state: 'visible', timeout: time });
}
