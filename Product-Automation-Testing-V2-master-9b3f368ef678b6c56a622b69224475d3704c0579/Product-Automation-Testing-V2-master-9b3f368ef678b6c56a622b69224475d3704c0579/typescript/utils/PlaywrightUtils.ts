import { Frame, Page } from "@playwright/test";


export default class PlaywrightUtils {


    static async clickOnFrame(frameContext:Frame, btnName : string, xpath : string) : Promise<boolean> {
        try {
            await frameContext.click(xpath);
            return true;
        } catch (error) {
            if (error instanceof Error) {
                console.error("Unable to click element due to : " + error.message);
            }
            return false;
        }
        
    }

    static async clickOnPage(frameContext:Frame, btnName : string, xpath : string) : Promise<boolean> {
        try {
            await frameContext.click(xpath);
            return true;
        } catch (error) {
            if (error instanceof Error) {
                console.error("Unable to click element due to : " + error.message);
            }
            return false;
        }
        
    }
    
    static async waitForSelectorOnFrame(frameContext:Frame, eleName : string, xpath : string, time : number) : Promise<boolean> {
        try {
            await frameContext.waitForSelector(xpath, {timeout : time});
            return true;
        } catch (error) {
            if (error instanceof Error) {
                console.error("The element : " + eleName + " is not visible. Expection is " + error.message);
            }
            return false;
        }
        
    }

    static async waitForSelectorOnPage(page:Page, eleName : string, xpath : string, time : number) : Promise<boolean> {
        try {
            await page.waitForSelector(xpath, {timeout : time});
            return true;
        } catch (error) {
            if (error instanceof Error) {
                console.error("The element : " + eleName + " is not visible. Expection is " + error.message);
            }
            return false;
        }
    }
    
    static async fillOnFrame(frameContext:Frame, value : string, xpath : string) : Promise<void> {
        try {
            await frameContext.fill(xpath, value);
        } catch (error) {
            if (error instanceof Error) {
                console.error(`Unable to enter value on element due to : ${error.message}`);
            }
        }
        
    }

}

