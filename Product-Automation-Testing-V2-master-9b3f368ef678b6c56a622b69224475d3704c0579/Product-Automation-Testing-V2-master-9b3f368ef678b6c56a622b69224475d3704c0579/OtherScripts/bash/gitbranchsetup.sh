#!/bin/bash

# Set command-line arguments to variables
BUILD_NUMBER="$1"
GIT_BRANCH="$2"
GIT_PASSWORD="$3"
SECURE_DIRECTORY="$4"
HOME_DIRECTORY="$5"
PEM="$6"

# Display IP address
ip addr

# Create directory
mkdir "QAAutomation$BUILD_NUMBER"
cd "QAAutomation$BUILD_NUMBER"

# Print current directory
pwd

# List contents of the directory
ls

# Remove existing directory
rm -rf Product-Automation-Testing-V2

# Clone git repository
git clone --branch "$GIT_BRANCH" "http://shamilya:$GIT_PASSWORD@git.datazoic.com/prism20/Product-Automation-Testing-V2.git"

# Navigate to the cloned repository
cd Product-Automation-Testing-V2

find . -mindepth 1 -maxdepth 1 ! -name 'playwright' -exec rm -rf {} +

# List contents of the directory
ls -l

echo "test"
# Navigate to the playwright directory
cd playwright

echo "test after playwright"

# Copy directory
IP_ADDRESS=$(hostname --ip-address | awk '{print $1}')
if [ "$IP_ADDRESS" = "127.0.1.1" ]; then
    cp -r "/home/datazoic/daniel/$SECURE_DIRECTORY" ./secure
else
    scp -i "$PEM" -r datazoic@192.168.3.66:/home/datazoic/daniel/"$SECURE_DIRECTORY" ./secure
fi

# cp -r "/home/$HOME_DIRECTORY/daniel/$SECURE_DIRECTORY" ./secure

# List contents of the directory
ls -l