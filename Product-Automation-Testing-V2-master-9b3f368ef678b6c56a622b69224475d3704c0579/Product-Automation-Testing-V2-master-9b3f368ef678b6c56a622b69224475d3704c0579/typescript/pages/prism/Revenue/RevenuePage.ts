import { Page } from "@playwright/test";

const oRevByAccountTable = ".DTFC_ScrollWrapper";
const oTrianglesLoader = "#loading_screen";

export async function waitForRevenueByAccountTable(page: Page, time?: number | 50000): Promise<void> {
    const trianglesLoader = page.locator(oTrianglesLoader);
    const revByAccountTable = page.locator(oRevByAccountTable);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await revByAccountTable.waitFor({ state: 'visible', timeout: time });
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
}