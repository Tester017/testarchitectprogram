import fs from 'fs';
import path from 'path';
import sgMail from '@sendgrid/mail';

// Set your SendGrid API key
sgMail.setApiKey(process.env.SENDGRID_API_KEY || 'token');
const latestDir = getLatestReportDir();

// Set the path to your HTML report
// const reportDir = fs.readdirSync('custom-report')
//   .filter(name => fs.statSync(path.join('custom-report', name)).isDirectory())
//   .sort() // latest timestamped report will be last
//   .pop();

if (!latestDir) {
  console.error('❌ No report folder found in playwright-report');
  process.exit(1);
}

// const reportPath = path.join('custom-report', reportDir || '', 'report.html');
const reportPath = path.join(process.env.PLAYWRIGHT_REPORT_PATH || latestDir, 'custom-report.html');
console.log(`Using report path: ${reportPath}`);


// Read the HTML content
const htmlContent = fs.readFileSync(reportPath, 'utf-8');

// Compose the email
const msg = {
  to: ['shamilya@datazoic.com'], // 👈 change this
  from: 'sender@datazoic.com',  // 👈 verified sender with SendGrid
  subject: 'Playwright Custom Test Report',
  html: htmlContent,
};

// Send the email
sgMail.send(msg)
  .then(() => console.log('✅ Email sent successfully'))
  .catch(error => console.error('❌ Email failed to send:', error));

function getLatestReportDir(): string | null {
const baseReportDir = 'playwright-report';
const folders = fs.readdirSync(baseReportDir)
  .filter(name => {
    const fullPath = path.join(baseReportDir, name);
    return fs.statSync(fullPath).isDirectory();
  })
  .sort((a, b) => {
    const timeA = fs.statSync(path.join(baseReportDir, a)).mtime.getTime();
    const timeB = fs.statSync(path.join(baseReportDir, b)).mtime.getTime();
    return timeB - timeA;
  });

return folders.length > 0 ? path.join(baseReportDir, folders[0]) : null;
}