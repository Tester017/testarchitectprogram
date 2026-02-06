import { Page } from "@playwright/test";
import { getConfig } from '../../../utils/envLoader';
const config = getConfig().config;

const oTrianglesLoader = "#loading_screen";
const fundsTab = ".srchtab";
const fundsTable = "table[id*='dataTable_accountfundlist']";

export async function clickFundsTab(page: Page, time?: number | 50000 ): Promise<void> {
    const holdingsTabPage = page.locator(fundsTab, { hasText: "Funds" });
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await holdingsTabPage.first().click(); 
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
}

export async function waitForFundsTableTable(page: Page, time?: number | 50000): Promise<void> {
    const newTickerByPostionTablePage = page.locator(fundsTable);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await newTickerByPostionTablePage.waitFor({ state: 'visible', timeout: time });
}