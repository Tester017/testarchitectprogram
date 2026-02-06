import { Page } from "@playwright/test";
import { getConfig } from '../../../utils/envLoader';
const config = getConfig().config;

const widget = 'div.ibox.panel-content';
const equityTilesContainer = "#equity_tiles";
const equityTiles = 'div.ibox-content';
const spinner = 'div.sk-spinner';
const fadingContactsContainer = "div#fading-contacts-contents"
const fadingContactsContent = "div.fading-small-widget,div.no-data"
const topSummaryContainer = ".topSummarycontainer"
const topSummaryActiveTable = ".tab-content > .active"

export async function waitForEquityTiles(page: Page, time?: number | 50000): Promise<void> {
    const equityTilesBox = page.locator(equityTilesContainer);
    const oSpinner = equityTilesBox.locator(spinner);
    await oSpinner.waitFor({ state: 'detached', timeout: time });
    const oEquityTiles = equityTilesBox.locator(equityTiles);
    await oEquityTiles.waitFor({ state: 'visible', timeout: time });
}

export async function waitForCommissionBySectors(page: Page, time?: number | 50000): Promise<void> {
    const commissionBySectorsContainer = page.locator(widget, { hasText: config.Dashboard.commissionBySectors.name });
    await commissionBySectorsContainer.waitFor({ state: 'visible', timeout: time });
    const oSpinner = commissionBySectorsContainer.locator(spinner);
    await oSpinner.waitFor({ state: 'detached', timeout: time });
    const svgChart = commissionBySectorsContainer.locator("svg");
    await svgChart.waitFor({ state: 'visible', timeout: 50000 }).catch(() => console.log('Loading text did not appear.'));
}

export async function waitForCommissionByRegion(page: Page, time?: number | 50000): Promise<void> {
    const commissionBySectorsContainer = page.locator(widget, { hasText: config.Dashboard.commissionByRegion.name });
    await commissionBySectorsContainer.waitFor({ state: 'visible', timeout: time });
    const oSpinner = commissionBySectorsContainer.locator(spinner);
    await oSpinner.waitFor({ state: 'detached', timeout: time });
    const svgChart = commissionBySectorsContainer.locator("svg");
    await svgChart.waitFor({ state: 'visible', timeout: 50000 }).catch(() => console.log('Loading text did not appear.'));
}

export async function waitForTopsummaryActiveTable(page: Page, time?: number | 50000): Promise<void> {
    const TopsummaryBox = page.locator(topSummaryContainer);
    await TopsummaryBox.waitFor({ state: 'visible', timeout: 50000 });
    const data = TopsummaryBox.locator(topSummaryActiveTable);
    await data.waitFor({ state: 'visible', timeout: 50000 });
}
export async function waitForFadingContactWidget(page: Page, time?: number | 50000): Promise<void> {
    const fadingContactBox = page.locator(fadingContactsContainer);
    await fadingContactBox.waitFor({ state: 'visible', timeout: 50000 });
    const data = fadingContactBox.locator(fadingContactsContent);
    await data.waitFor({ state: 'visible', timeout: 50000 });
}
