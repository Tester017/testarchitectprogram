import { Page } from "@playwright/test";

const readershipPageName = "li[data-pagename='Readership']";
const oTrianglesLoader = "#loading_screen";
const oReadershipTableData = ".dataTables_scrollBody td.dt-center,.dataTables_scrollBody td.dataTables_empty";

export async function waitForReadershipPage(page: Page, time?: number | 50000): Promise<void> {
    const readershipPage = page.locator(readershipPageName);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await readershipPage.waitFor({ state: 'visible', timeout: time });
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
}
export async function waitForReadershipTable(page: Page, time?: number | 50000): Promise<void> {
    const readershipTableData = page.locator(oReadershipTableData);
    const trianglesLoader = page.locator(oTrianglesLoader);
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });
    await readershipTableData.first().waitFor({ state: 'visible', timeout: time });
    await trianglesLoader.waitFor({ state: 'hidden', timeout: time });

}
