#!/bin/bash

# Set command-line arguments to variables
SUBJECT="$1"
JOB_NAME="$2"
BUILD_NUMBER="$3"
BUILD_STATUS="$4"
BUILD_DURATION="$5"
NODE="$6"
RECIPIENT="$7"
free_space=$(df -h / | grep / | awk '{print $4}')
CURRENT_USER=$(whoami)

# Set your SendGrid API token
TOKEN=$(grep -oP '^sendgridapi=\K.*' /home/$CURRENT_USER/workspace/$JOB_NAME/QAAutomation$BUILD_NUMBER/Product-Automation-Testing-V2/playwright/secure/.auth)

# Set the sender email address and name
FROM_EMAIL="testautomation@datazoic.com"
FROM_NAME="Test Automation"

# Set the content for the email
CONTENT="Job Name: $JOB_NAME \nBuild Number: $BUILD_NUMBER\nBuild Status: $BUILD_STATUS\nBuild Duration: $BUILD_DURATION\nNode: $NODE\nRemaining storage: $free_space"

# Compose the JSON data for the email
DATA=$(cat <<EOF
{
  "personalizations": [
    {
      "to": [
        {
          "email": "daniel@datazoic.com"
        },
	{
          "email": "$RECIPIENT"
        }
      ]
    }
  ],
  "from": {
    "email": "$FROM_EMAIL",
    "name": "$FROM_NAME"
  },
  "subject": "$SUBJECT",
  "content": [
    {
      "type": "text/plain",
      "value": "$CONTENT"
    }
  ]
}
EOF
)

# Send the email using cURL
curl -X POST -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" --data "$DATA" "https://api.sendgrid.com/v3/mail/send"
