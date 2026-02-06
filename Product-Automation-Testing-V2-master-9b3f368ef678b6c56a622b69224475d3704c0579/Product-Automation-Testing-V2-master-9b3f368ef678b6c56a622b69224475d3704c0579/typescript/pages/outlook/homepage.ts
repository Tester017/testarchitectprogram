import { Frame, Page } from "@playwright/test";

const oEmailIcon="[data-icon-name='MailRegularLight']"
const smartSendBtn='button[id="msgSmartSend"]'
const sendBulkEmail='button[id="msgSendBulkEmail"]'

export async function waitforhomePageNewIcon(page:Page,time:number){
    await page.waitForSelector(oEmailIcon, {timeout : time});
}

export async function clickNewEmailIcon(page:Page){
    await page.locator(oEmailIcon).click()
}

export async function clickMessageTab(page:Page){
    await page.getByRole('tab', { name: 'Message' }).click()
}

export async function clickAppsIcon(page:Page){
    await page.getByLabel("Apps",{exact:true}).click()
}

export async function clickWebAddinIcon(page:Page){
    await page.getByLabel("Prism QA").click()
}

export async function selectSendBulkMail(page:Page,time?:number){
    await page.getByText("Send Bulk Mail").waitFor({state:"visible",timeout:time||5000})
    await page.getByText("Send Bulk Mail").click()

}

export async function getPrismFrame(page:Page,time?:number):Promise<Frame>{
    const prismFrame = await page.waitForSelector("iframe[title*='Office Add-in Prism']")
    const frame = await prismFrame.contentFrame()
    if (!frame) {
        throw new Error("Frame content not available for the selector.");
      }
    frame.locator("#email").waitFor({state:"visible",timeout:time})
    return frame;
}

export async function fillSubject(page:Page,data:string,time?:number){
    await page.getByLabel("Add a subject").fill(data);
}

export async function fillbody(page:Page,data:string,time?:number){
    await page.locator("[role='textbox'][aria-label^='Message body']").clear();
    await page.locator("[role='textbox'][aria-label^='Message body']").fill(data);
}