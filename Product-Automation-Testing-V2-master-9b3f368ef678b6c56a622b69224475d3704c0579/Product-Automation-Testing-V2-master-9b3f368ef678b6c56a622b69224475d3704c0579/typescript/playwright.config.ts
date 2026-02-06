import { defineConfig, devices } from '@playwright/test';
import { getConfig } from 'utils/envLoader';
import path from 'path';
import fs from 'fs';

/**
 * Read environment variables from file.
 * https://github.com/motdotla/dotenv
 */
// import dotenv from 'dotenv';
// import path from 'path';
// dotenv.config({ path: path.resolve(__dirname, '.env') });
const config = getConfig();

const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
const reportDir = path.join('playwright-report', `report-${config.env}-${timestamp}`);

// Save it for other scripts to use
// fs.writeFileSync('report-path1.json', JSON.stringify({ reportDir }), 'utf-8');
/**
 * See https://playwright.dev/docs/test-configuration.
 */
export default defineConfig({
  /**
   * The directory where tests are located.
   * See https://playwright.dev/docs/api/class-testconfig#testconfig-testdir
   */
  testDir: './tests',
  /* Run tests in files in parallel */
  fullyParallel: true,
  /* Fail the build on CI if you accidentally left test.only in the source code. */
  forbidOnly: !!process.env.CI,
  /* Retry on CI only */
  retries: process.env.CI ? 0 : 0,
  /* Opt out of parallel tests on CI. */
  // workers: process.env.CI ? 1 : undefined,
  workers: Number(process.env.WORKERS) || 1,
  /* Reporter to use. See https://playwright.dev/docs/test-reporters */
  reporter: [
    ['html', { outputFolder: reportDir, open: 'never' }],
    ['./utils/customReporter.ts', { outputFolder: reportDir }]
  ],
  timeout: 110000,

  /* Shared settings for all the projects below. See https://playwright.dev/docs/api/class-testoptions. */
  use: {
    /* Base URL to use in actions like `await page.goto('/')`. */
    // baseURL: 'http://127.0.0.1:3000',

    /* Collect trace when retrying the failed test. See https://playwright.dev/docs/trace-viewer */
    trace: 'on',
    video: 'on',
    headless: true,
    launchOptions: {
      args: ['--start-maximized']
    },

  },

  /* Configure projects for major browsers */
  projects: [
    {
      name: "outlookLoginSetup",
      testDir: "./Setups",
      testMatch: "outlookLoginSetup.ts",
      use: {
        browserName: 'chromium',
      }
    },
    {
      name: "prismLoginSetup",
      testDir: "./Setups",
      testMatch: "prismloginsetup.ts",
      use: {
        browserName: 'chromium',
      }
    },

    {
      name: 'performanceSuite',
      testMatch: ['tests/PerformanceTest.spec.ts'],
      // dependencies: ["outlookLogin"],
      use: {
        browserName: 'chromium',
        ...devices['Desktop Chrome'],
        storageState: config.config.environment.storageStatePath,
        launchOptions: {
          args: ['--start-maximized'],
          slowMo: 30,
        },
        viewport: null,
        deviceScaleFactor: undefined,

      },

    },

    {
      name: 'email',
      testMatch: /.*sendEmailReport\.spec\.ts/, // only runs the email test
    },





    /* Test against branded browsers. */
    // {
    //   name: 'Microsoft Edge',
    //   use: { ...devices['Desktop Edge'], channel: 'msedge' },
    // },
    // {
    //   name: 'Google Chrome',
    //   use: { ...devices['Desktop Chrome'], channel: 'chrome' },
    // },
  ],

  /* Run your local dev server before starting the tests */
  // webServer: {
  //   command: 'npm run start',
  //   url: 'http://127.0.0.1:3000',
  //   reuseExistingServer: !process.env.CI,
  // },
});
