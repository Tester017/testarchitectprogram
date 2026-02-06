import { expect } from '@playwright/test';
import { test } from '@utils/testSetup';
import * as leftPane from '@prismpages/LeftPane';
import * as readership from '@prismreadershippages/ReadershipPage'
import { getConfig } from '@utils/envLoader';

const config = getConfig().config;

test('Readership performance test @performance', async ({ page }) => {

//   await test.step('Navigate to Login Page', async () => {
//     const urlBootStart = performance.now();
//     await login.loadUrl(page);
//     const urlBootEnd = performance.now();
//     const urlBootLoadTime = ((urlBootEnd - urlBootStart) / 1000).toFixed(2);
//     await test.step(`✅ Page Loaded (Start: ${urlBootStart.toFixed(0)} | End: ${urlBootEnd.toFixed(0)} | Time: ${urlBootLoadTime}s)`, async () => {
//       expect.soft(Number(urlBootLoadTime)).toBeLessThan(10);
//     });
//   });

//   await test.step('Logging into Prism', async () => {
//     await login.loginToPrism(page);
//     const loginStart = performance.now();
//     await login.waitForLoginSuccess(page)
//     const loginEnd = performance.now();
//     const loginLoadTime = ((loginEnd - loginStart) / 1000).toFixed(2);
//     console.log(`Login completed in ${loginLoadTime} seconds`);
//     await test.step(`✅ Login Completed (Start: ${loginStart.toFixed(0)} | End: ${loginEnd.toFixed(0)} | Time: ${loginLoadTime}s)`, async () => {
//       expect.soft(Number(loginLoadTime)).toBeLessThan(10);
//     });

//   });

 await test.step('Readership - clicking Readership module', async () => {
    await leftPane.clickModuleFromLeftPane(page, config.Readership);
  });

  await Promise.all([
    test.step('Readership - Load Readership page ', async () => {
      const loginStart = performance.now();
      await readership.waitForReadershipPage(page)
      const loginEnd = performance.now();
      const LoadTime = ((loginEnd - loginStart) / 1000).toFixed(2);
      console.log(`Login completed in ${LoadTime} seconds`);
      await test.step(`✅ Landed Readership (Start: ${loginStart.toFixed(0)} | End: ${loginEnd.toFixed(0)} | Time: ${LoadTime}s)`, async () => {
        expect.soft(Number(LoadTime)).toBeLessThan(10);
      });
    }),

    test.step('Readership - Wait for table to appear', async () => {
      const loginStart = performance.now();
      await readership.waitForReadershipTable(page)
      const loginEnd = performance.now();
      const LoadTime = ((loginEnd - loginStart) / 1000).toFixed(2);
      console.log(`Login completed in ${LoadTime} seconds`);
      await test.step(`✅ Readership table loaded (Start: ${loginStart.toFixed(0)} | End: ${loginEnd.toFixed(0)} | Time: ${LoadTime}s)`, async () => {
        expect.soft(Number(LoadTime)).toBeLessThan(10);
      });
    }),
  ]);
});
