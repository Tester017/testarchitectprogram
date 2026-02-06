import type { Reporter, TestCase, TestResult, FullResult } from '@playwright/test/reporter';
import * as fs from 'fs';
import * as path from 'path';

class CustomReporter implements Reporter {
  private results: string[] = [];
  private reportDir: string;

  constructor(options: any) {
    this.reportDir = options.outputFolder || 'playwright-report';
    fs.mkdirSync(this.reportDir, { recursive: true });
  }

  onTestEnd(test: TestCase, result: TestResult) {
    const outcome = result.status.toUpperCase();
    const durationMs = result.duration;
    const minutes = Math.floor(durationMs / 60000);
    const seconds = ((durationMs % 60000) / 1000).toFixed(2);
    const formattedDuration = `${minutes}m ${seconds}s`;

    this.results.push(
      `<tr>
        <td>${test.title}</td>
        <td>${test.parent.title}</td>
        <td class="${outcome}">${outcome}</td>
        <td>${formattedDuration}</td>
      </tr>`
    );
  }

  async onEnd(result: FullResult) {
  const link = process.env.PLAYWRIGHT_REPORT_LINK || 'No Link Provided';

    const html = `
      <html>
        <head>
          <style>
            table { border-collapse: collapse; width: 100%; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
            .PASSED { color: green; }
            .FAILED { color: red; }
            .SKIPPED { color: orange; }
          </style>
        </head>
        <body>
          <h2>Playwright Test Report</h2>
          <p>Report link🔗 <a href="${link}" target="_blank">${link}</a></p>
          <table>
            <tr>
              <th>Test Name</th>
              <th>Suite</th>
              <th>Status</th>
              <th>Duration</th>
            </tr>
            ${this.results.join('\n')}
          </table>
        </body>
      </html>
    `;
    const reportPath = path.join(this.reportDir, 'custom-report.html');
    fs.writeFileSync(reportPath, html, 'utf-8');
    console.log(`✅ Custom report generated: ${reportPath}`);
  }
}

export default CustomReporter;
