#!/bin/bash

# Set command-line arguments to variables
SUBJECT="$1"
BUILD_DURATION="$2"
NODE="$3"
RECIPIENT="$6"
free_space="$4"
swap_storage="$5"
CURRENT_USER=$(whoami)

# Set your SendGrid API token
TOKEN=token

# Set the sender email address and name
FROM_EMAIL="testautomation@datazoic.com"
FROM_NAME="Test Automation"

# Set the content for the email
CONTENT="Build Duration: $BUILD_DURATION\nNode: $NODE\nRemaining storage: $free_space\nRemaining storage: $swap_storage"

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
