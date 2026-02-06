#!/bin/bash

# Assign command-line arguments to variables
PWD="$1"
BUILD_NUMBER="$2"
CLIENT="$3"
REPORTDIR="$4"
PROJECT="$5"
USER="$6"

# Function to set the password based on the user
set_password() {
    IP_ADDRESS=$(hostname --ip-address | awk '{print $1}')
    if [ "$USER" = "support" ]; then
        PASSWORD="${PWD:0:6}"
    elif [ "$USER" = "datazoic" ]; then
        if [ "$IP_ADDRESS" = "127.0.1.1" ]; then
            PASSWORD="$PWD"
            IP_ADDRESS="192.168.3.66"
        else
            PASSWORD="${PWD:0:6}"
        fi
    fi
}

# Set the password
set_password

echo "PWD: $PWD"
echo "BUILD_NUMBER: $BUILD_NUMBER"
echo "CLIENT: $CLIENT"
echo "REPORTDIR: $REPORTDIR"
echo "PROJECT: $PROJECT"
echo "USER: $USER"

APACHE_REPORT="/var/www/html/testreport/$PROJECT/result$BUILD_NUMBER"
echo "APACHE_REPORT: $APACHE_REPORT"

# Copy reports
if [ "$USER" = "support" ]; then
    echo "${PASSWORD}" | su -c "echo '${PASSWORD}' | sudo -S mkdir -p '$APACHE_REPORT'" datazoic
    echo "${PASSWORD}" | su -c "echo '${PASSWORD}' | sudo -S cp -r ${REPORTDIR}/* '$APACHE_REPORT/'" datazoic
elif [ "$USER" = "datazoic" ]; then
    echo "$PASSWORD" | sudo -S mkdir -p "$APACHE_REPORT"
    echo "$PASSWORD" | sudo -S cp -r "$REPORTDIR"/* "$APACHE_REPORT/"
fi
