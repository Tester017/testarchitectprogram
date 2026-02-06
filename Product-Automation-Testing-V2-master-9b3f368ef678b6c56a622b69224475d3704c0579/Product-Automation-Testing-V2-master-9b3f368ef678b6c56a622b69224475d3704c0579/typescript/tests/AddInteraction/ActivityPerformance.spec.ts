import { expect } from '@playwright/test';
import { test } from '@utils/testSetup';
import * as leftPane from '@prismpages/LeftPane';
import * as activity from '@prismactivitypages/ActivityPage'


test(' Activity performance test @performance', async ({ page , config}) => {

    await test.step('Activity - clicking Activity module', async () => {
    await leftPane.clickModuleFromLeftPane(page,config.Activity);
  });

  await Promise.all([
    test.step('Activity - Loading for Activity page', async () => {
      const loginStart = performance.now();
      await activity.waitForActivityPage(page)
      const loginEnd = performance.now();
      const LoadTime = ((loginEnd - loginStart) / 1000).toFixed(2);
      console.log(`Login completed in ${LoadTime} seconds`);
      await test.step(`✅ Landed Activity (Start: ${loginStart.toFixed(0)} | End: ${loginEnd.toFixed(0)} | Time: ${LoadTime}s)`, async () => {
        expect.soft(Number(LoadTime)).toBeLessThan(10);
      });
    }),

    test.step('Activity - Wait for table to appear', async () => {
      const loginStart = performance.now();
      await activity.waitForActivityTable(page)
      const loginEnd = performance.now();
      const LoadTime = ((loginEnd - loginStart) / 1000).toFixed(2);
      console.log(`Login completed in ${LoadTime} seconds`);
      await test.step(`✅ Activity table loaded (Start: ${loginStart.toFixed(0)} | End: ${loginEnd.toFixed(0)} | Time: ${LoadTime}s)`, async () => {
        expect.soft(Number(LoadTime)).toBeLessThan(10);
      });
    }),
  ]);
});
