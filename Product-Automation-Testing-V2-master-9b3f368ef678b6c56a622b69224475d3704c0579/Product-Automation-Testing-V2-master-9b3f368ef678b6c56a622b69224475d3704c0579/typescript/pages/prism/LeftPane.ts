import { Page, Locator } from '@playwright/test';

export async function clickSideMenuIfExpanded(page: Page, timeout: number = 10000): Promise<void> {
  await page.locator('.sidebar-collapse').waitFor({ state: 'attached', timeout });
  const expandedButton: Locator = page.locator('.sidebar-collapse.sideMenuExpand > div > button');
  const expandButton: Locator = page.locator('.sidebar-collapse > div > button');

  if (!(await expandedButton.count() > 0 && await expandedButton.first().isVisible())) {
    await expandButton.first().click();
    console.log('Clicked expanded sidebar button.');
  } else {
    console.log('Sidebar already expanded. No action taken.');
  }
}

export async function clickModuleFromLeftPane(page: Page, modulesConfig: any) {
  // const module = modulesConfig[moduleName];
  if (!modulesConfig || !modulesConfig.isApplicable) {
    console.log(`❌ Module "${modulesConfig.name}" is not applicable or not found.`);
    return;
  }

  const parentSelector = page.locator(`li[data-module='${modulesConfig.parentModule}']`).first();

  // Collapse left pane if needed

  // If there's a parent module, click it first
  if (modulesConfig.parentModule) {
    await clickSideMenuIfExpanded(page, 50000);
    console.log(`📂 Clicking parent module: ${modulesConfig.parentModule}`);
    await parentSelector.click();
    await parentSelector.locator(`li[data-module='${modulesConfig.name}']`).click();

  }
  else {
    console.log(`📄 Clicking module: ${modulesConfig.name}`);
    await page.locator(`li[data-module='${modulesConfig.name}']`).click();
  }
}

