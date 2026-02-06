import { expect } from '@playwright/test';
import { test } from '@utils/testSetup';
import * as dashboard from '@prismdashboardpages/DashboardPage';

test('Dashboard performance test @performance', async ({ page, config }) => {
  const steps = [
    {
      name: 'Equity Tiles',
      isApplicable: config.Dashboard.equityTiles?.isApplicable,
      action: () => dashboard.waitForEquityTiles(page),
    },
    {
      name: 'Commission by Sectors',
      isApplicable: config.Dashboard.commissionBySectors?.isApplicable,
      action: () => dashboard.waitForCommissionBySectors(page),
    },
    {
      name: 'Commission by Region',
      isApplicable: config.Dashboard.commissionByRegion?.isApplicable,
      action: () => dashboard.waitForCommissionByRegion(page),
    },
    {
      name: 'Fading Contact Widget',
      isApplicable: config.Dashboard.fadingContacts?.isApplicable,
      action: () => dashboard.waitForFadingContactWidget(page),
    },
  ];

  await Promise.all(steps.map(({ name, isApplicable, action }) => {
    if (!isApplicable) return Promise.resolve(); // skip if not applicable

    return test.step(`Dashboard - Appearance of ${name}`, async () => {
      const start = performance.now();
      await action();
      const end = performance.now();
      const loadTime = ((end - start) / 1000).toFixed(2);
      console.log(`${name} loaded in ${loadTime} seconds`);
      await test.step(`✅ ${name} loaded (Start: ${start.toFixed(0)} | End: ${end.toFixed(0)} | Time: ${loadTime}s)`, async () => {
        expect.soft(Number(loadTime)).toBeLessThan(10);
      });
    });
  }));
});
