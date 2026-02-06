import { Page } from '@playwright/test';
import { getConfig } from '../../utils/envLoader';

const config = getConfig().config;


export async function loadUrl(page: Page): Promise<void> {
    await page.goto(config.environment.prismUrl, { waitUntil: 'load' });
    await page.waitForLoadState('networkidle');
}

export async function loginToPrism(page: Page): Promise<void> {
    const loginType = config.login.loginType;
    if (loginType.toLowerCase().includes('sso')) {
        await page.locator('#login_type').selectOption({ label: loginType });
        await page.locator("button.login-page-btn").click();

    }
}

export async function waitForLoginSuccess(page: Page): Promise<void> {
    page.locator("div.ibox.panel-content").first().waitFor({ state: 'visible', timeout: 50000 });
}