import * as fs from 'fs';
import * as path from 'path';

import { merge } from 'lodash';

export function getConfig(envName?: string): { env: string; config: any } {
  const env = envName || process.env.TEST_ENV || 'qa1';

  const baseConfigPath = path.resolve(__dirname, `../config/base.json`);
  const envConfigPath = path.resolve(__dirname, `../config/${env}.json`);

  let baseConfig = {};
  let envConfig = {};

  if (fs.existsSync(baseConfigPath)) {
    baseConfig = JSON.parse(fs.readFileSync(baseConfigPath, 'utf-8'));
  } else {
    console.warn(`⚠️ base.json config file not found at ${baseConfigPath}`);
  }
  if (fs.existsSync(envConfigPath)) {
    envConfig = JSON.parse(fs.readFileSync(envConfigPath, 'utf-8'));
  } else {
    console.warn(`⚠️ Environment config file not found at ${envConfigPath}. Proceeding with base config only.`);
  }


  const mergedConfig = merge({}, baseConfig, envConfig);

  return { env, config: mergedConfig };
}
