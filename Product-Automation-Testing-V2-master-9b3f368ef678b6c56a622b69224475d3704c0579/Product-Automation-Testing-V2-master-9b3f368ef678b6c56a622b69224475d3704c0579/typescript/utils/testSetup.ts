import { test as baseTest, expect } from '@playwright/test';
import * as login from '@prismpages/Login';
import { getConfig } from '@utils/envLoader';

const config = getConfig().config;

type TestFixtures = {
    config: typeof config;
};

export const test = baseTest.extend<TestFixtures>({
    config: [async ({ }, use) => {
        await use(config);
    }, { scope: 'worker' }],
});

test.beforeEach(async ({ page }) => {
    await test.step('Navigate to Login Page', async () => {
        const urlBootStart = performance.now();
        await login.loadUrl(page);
        const urlBootEnd = performance.now();
        const loadTime = ((urlBootEnd - urlBootStart) / 1000).toFixed(2);
        await test.step(`✅ Page Loaded (Start: ${urlBootStart.toFixed(0)} | End: ${urlBootEnd.toFixed(0)} | Time: ${loadTime}s)`, async () => {
            expect.soft(Number(loadTime)).toBeLessThan(10);
        });
    });

    await test.step('Logging into Prism', async () => {
        await login.loginToPrism(page);
        const loginStart = performance.now();
        await login.waitForLoginSuccess(page);
        const loginEnd = performance.now();
        const loginLoadTime = ((loginEnd - loginStart) / 1000).toFixed(2);
        console.log(`Login completed in ${loginLoadTime} seconds`);
        await test.step(`✅ Login Completed (Start: ${loginStart.toFixed(0)} | End: ${loginEnd.toFixed(0)} | Time: ${loginLoadTime}s)`, async () => {
            expect.soft(Number(loginLoadTime)).toBeLessThan(10);
        });
    });
});