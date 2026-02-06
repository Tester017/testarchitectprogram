#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Assign command-line arguments to variables
offlinePath="$1"
BUILD_NUMBER="$2"
CLIENT="$3"
PROJECT="$4"

# Validate required arguments
if [ -z "$offlinePath" ] || [ -z "$CLIENT" ] || [ -z "$PROJECT" ]; then
    echo "❌ Missing required arguments. Usage: $0 <offlinePath> <BUILD_NUMBER> <CLIENT> <PROJECT>"
    exit 1
fi

# Move to the test directory
cd typescript/ || { echo "❌ 'typescript/' directory not found"; exit 1; }

# Validate offline dependencies
if [ ! -d "${offlinePath}/node_modules" ]; then
    echo "❌ node_modules not found in offline path!"
    exit 1
fi

if [ ! -d "${offlinePath}/.playwright" ]; then
    echo "❌ .playwright (browsers) not found in offline path!"
    exit 1
fi

# Set browser path
export PLAYWRIGHT_BROWSERS_PATH="${offlinePath}/.playwright"
echo "✅ Browsers path set to: $PLAYWRIGHT_BROWSERS_PATH"

# Copy cached dependencies
echo "🔄 Copying offline node_modules and secure folders..."
rsync -a --delete "${offlinePath}/node_modules/" ./node_modules/
rsync -a --delete "${offlinePath}/secure/" ./secure/ || echo "⚠️ Secure folder not found in offline path"

# Run Playwright tests
echo "🚀 Starting Playwright Tests..."
TEST_ENV="${CLIENT}" npx playwright test --project="${PROJECT}" || true

# Store exit code of test execution
echo $? > exit_code.txt
