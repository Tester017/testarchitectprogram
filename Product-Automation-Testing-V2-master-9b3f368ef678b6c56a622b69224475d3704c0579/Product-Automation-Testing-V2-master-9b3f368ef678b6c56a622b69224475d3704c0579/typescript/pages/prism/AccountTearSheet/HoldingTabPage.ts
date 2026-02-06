import { Page } from "@playwright/test";
import { getConfig } from '../../../utils/envLoader';
const config = getConfig().config;

const oTrianglesLoader = "#loading_screen";
const holdingsTab = ".srchtab";
const newTickerByPostionTable = "table[id*='getnewpositiontickers']";
const holdingsBySectorTable = "table[id*='getnewpositiontickers']";
const equityHoldings = "table[id*='equityHoldingTableAC']";

export async function clickHoldingsTab(page: Page, time?: number | 50000 ): Promise<void> {
    const holdingsTabPage = page.locator(holdingsTab, { hasText: "Holdings" });
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await holdingsTabPage.first().click(); 
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
}

export async function waitForNewTickerByPostionTable(page: Page, time?: number | 50000): Promise<void> {
    const newTickerByPostionTablePage = page.locator(newTickerByPostionTable);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await newTickerByPostionTablePage.waitFor({ state: 'visible', timeout: time });
}

export async function waitForHoldingsBySector(page: Page, time?: number | 50000): Promise<void> {
    const newTickerByPostionTablePage = page.locator(newTickerByPostionTable);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await newTickerByPostionTablePage.waitFor({ state: 'visible', timeout: time });
}

export async function waitForEquityHoldings(page: Page, time?: number | 50000): Promise<void> {
    const equityHoldingsTablePage = page.locator(equityHoldings);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await equityHoldingsTablePage.waitFor({ state: 'visible', timeout: time });
}