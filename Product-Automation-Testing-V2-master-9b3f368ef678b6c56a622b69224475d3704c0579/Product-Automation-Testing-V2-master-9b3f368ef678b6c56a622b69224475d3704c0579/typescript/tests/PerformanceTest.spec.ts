import { expect } from '@playwright/test';
import { test } from '@utils/testSetup';
import * as leftPane from '@prismpages/LeftPane';
import * as dashboard from '@prismdashboardpages/DashboardPage';
import * as activity from '@prismactivitypages/ActivityPage';
import * as readership from '@prismreadershippages/ReadershipPage';
import * as revenue from '@prismrevenuepages/RevenuePage';
import * as globalSearch from '@prismglobalsearchpages/GlobalSearchPage';
import * as atsholdingstab from '@prismaccounttearsheetpages/HoldingTabPage';
import * as atsfundstab from '@prismaccounttearsheetpages/FundsTab';

// Helper to measure performance and validate timing
async function measurePerformance(stepLabel: string, action: () => Promise<void>) {
  return test.step(stepLabel, async () => {
    const start = performance.now();
    await action();
    const end = performance.now();
    const loadTime = ((end - start) / 1000).toFixed(2);
    console.log(`${stepLabel} completed in ${loadTime} seconds`);
    await test.step(`✅ ${stepLabel} (Start: ${start.toFixed(0)} | End: ${end.toFixed(0)} | Time: ${loadTime}s)`, async () => {
      expect.soft(Number(loadTime)).toBeLessThan(10);
    });
  });
}

test('Complete performance suite @performance', async ({ page, config }) => {
  // DASHBOARD
  const dashboardSteps = [
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
      name: 'Top Summary Active Table',
      isApplicable: config.Dashboard.TopSummary?.isApplicable,
      action: () => dashboard.waitForTopsummaryActiveTable(page),
    },
    {
      name: 'Fading Contact Widget',
      isApplicable: config.Dashboard.fadingContacts?.isApplicable,
      action: () => dashboard.waitForFadingContactWidget(page),
    },
  ];

  await Promise.all(
    dashboardSteps.map(({ name, isApplicable, action }) => {
      return isApplicable ? measurePerformance(`Dashboard - wait for ${name} to load`, action) : Promise.resolve();
    })
  );

  // ACTIVITY
  if (config.Activity?.isApplicable) {
    await test.step('Activity - Clicking Activity module', async () => {
      await leftPane.clickModuleFromLeftPane(page, config.Activity);
    });

    await Promise.all([
      measurePerformance('Activity - Load Activity page', () => activity.waitForActivityPage(page)),
      measurePerformance('Activity - Load Activity Tile', () => activity.waitForActivityTiles(page)),
      measurePerformance('Activity - Wait for Activity table', () => activity.waitForActivityTable(page)),
    ]);
  }

  // READERSHIP
  if (config.Readership?.isApplicable) {

    await test.step('Readership - Clicking Readership module', async () => {
      await leftPane.clickModuleFromLeftPane(page, config.Readership);
    });

    await Promise.all([
      measurePerformance('Readership - Load Readership page', () => readership.waitForReadershipPage(page)),
      measurePerformance('Readership - Wait for Readership table', () => readership.waitForReadershipTable(page)),
    ]);
  }

  // REVENUE
  if (config.Revenue?.isApplicable) {

    await test.step('Revenue - Clicking Revenue module', async () => {
      await leftPane.clickModuleFromLeftPane(page, config.Revenue);
    });

    await Promise.all([
      measurePerformance('Revenue - Wait for Revenue by Account table', () => revenue.waitForRevenueByAccountTable(page)),
    ]);
  }

await Promise.all([
      measurePerformance('Account Tear Sheet - Open Account Tear', () => globalSearch.enterAccountName(page, config.AccountTearSheet.investorAccountName)),
      measurePerformance('Account Tear Sheet - Click Holdings Tab', () => atsholdingstab.clickHoldingsTab(page)),
      measurePerformance('Holdings Tab - New Ticker Position Holdings', () => atsholdingstab.waitForNewTickerByPostionTable(page)),
      measurePerformance('Holdings Tab - Equity Quality', () => atsholdingstab.waitForEquityHoldings(page)),
      measurePerformance('Account Tear Sheet - Click Holdings Tab', () => atsfundstab.clickFundsTab(page)),
      measurePerformance('Funds Tab - Funds Table', () => atsfundstab.waitForFundsTableTable(page))
    ]);

});
