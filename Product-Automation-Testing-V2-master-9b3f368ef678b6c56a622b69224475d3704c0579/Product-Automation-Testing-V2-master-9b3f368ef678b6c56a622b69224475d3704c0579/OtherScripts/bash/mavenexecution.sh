#!/bin/bash

# Assign command-line arguments to variables
PWD="$1"
BUILD_NUMBER="$2"
CLIENT="$3"
TYPES="$4"
MODE="$5"
PROJECT="$6"
USER="$7"
SECURE_DIRECTORY="$8"
ROLE="$9"
ENV_ROLE="${10}"
PASSWORD=""

if [ "$MODE" = true ]; then
    ENVIRONMENT="PRODUCTION"
else
    ENVIRONMENT="UAT"
fi

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

cd /home/$USER/workspace/$PROJECT/QAAutomation$BUILD_NUMBER/Product-Automation-Testing-V2/playwright

# Define file paths
BASE_DIR="/home/$USER/workspace/$PROJECT/QAAutomation$BUILD_NUMBER/Product-Automation-Testing-V2/playwright"
TRANSITION_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/transition.properties"
PRODUCTCONFIG_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/productConfig.properties"
EMAILCONFIG_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/emailConfig.properties"
ENVCONFIG_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/EnvironmentConfig/$ENVIRONMENT/$CLIENT/$CLIENT.properties"
APACHE_REPORT="/var/www/html/testreport/$PROJECT/result$BUILD_NUMBER"

echo "ENVCONFIG_PROPERTIES_FILE located in $ENVCONFIG_PROPERTIES_FILE"

# Users for email configurations
SD_USERS="shamilya@datazoic.com,karthikrajg@datazoic.com,nasgath@datazoic.com,agnalraymend@datazoic.com,aishwaryak@datazoic.com,dhilipbm@datazoic.com,arasu@datazoic.com,vigneshm@datazoic.com,nageshwari@datazoic.com,gnanatilak@datazoic.com,sasibooshan@datazoic.com,kowsalya@datazoic.com,nallendhiran@datazoic.com,salmankhan@datazoic.com"
# SD_USERS="shamilya@datazoic.com,kowsalya@datazoic.com"
QA_USERS="shamilya@datazoic.com,kowsalya@datazoic.com,nallendhiran@datazoic.com,salmankhan@datazoic.com"

# Function to write a property to the properties file
write_property() {
    local property_key=$1
    local property_value=$2
    echo "${property_key}=${property_value}" >> "$TRANSITION_PROPERTIES_FILE"
}

# Write variables to transition.properties
write_property "ip_address" "$IP_ADDRESS"
write_property "jobName" "$PROJECT"
write_property "jenkinsBuildNumber" "$BUILD_NUMBER"

cat "$TRANSITION_PROPERTIES_FILE"

# Split comma-separated string into an array
IFS=',' read -ra TYPES_ARRAY <<< "$TYPES"

# Iterate over the array and run mvn test command
for TYPE in "${TYPES_ARRAY[@]}"; do
    if [ "$USER" = "support" ]; then
        echo "${PASSWORD}" | su -c "echo '${PASSWORD}' | sudo -S sync && echo 3 | sudo -S tee /proc/sys/vm/drop_caches" datazoic
    elif [ "$USER" = "datazoic" ]; then
        echo "${PASSWORD}" | sudo -S sync && echo 3 | sudo -S tee /proc/sys/vm/drop_caches
    fi

    # Update configuration files
    sed -i "s/^HeadLess.*/HeadLess=true/" "$PRODUCTCONFIG_PROPERTIES_FILE"
    sed -i "s/^SendEmail.*/SendEmail=true/" "$PRODUCTCONFIG_PROPERTIES_FILE"
    sed -i "s/^ROLE.*/ROLE=$ROLE/" "$PRODUCTCONFIG_PROPERTIES_FILE"

    # Update environment configuration files
    sed -i "s/^ROLES.*/ROLES=$ENV_ROLE/" "$ENVCONFIG_PROPERTIES_FILE"
    
    if [ "$SECURE_DIRECTORY" = "securesd" ]; then
        sed -i "s/^TO.*/TO=$SD_USERS/" "$EMAILCONFIG_PROPERTIES_FILE"
    else
        sed -i "s/^TO.*/TO=$QA_USERS/" "$EMAILCONFIG_PROPERTIES_FILE"
    fi

    export DISPLAY=:99

    echo "mvn test -DClient=$CLIENT -Dtype=$TYPE -DProductionMode=$MODE"

    mvn test -DClient="$CLIENT" -Dtype="$TYPE" -DProductionMode="$MODE"

    # Function to get a property value
    get_property() {
        local property_key=$1
        grep "^${property_key}=" "$TRANSITION_PROPERTIES_FILE" | cut -d'=' -f2-
    }

    REPORTFOLDERNAME=$(get_property "reportfoldername")
    REPORTFILEPATH=$(get_property "reportFilePath")
    EMAILREPORTFILEPATH=$(get_property "emailReportFilePath")

    cat "$TRANSITION_PROPERTIES_FILE"

    EXTENT_REPORT="$BASE_DIR/$REPORTFOLDERNAME/$REPORTFILEPATH"
    CUSTOM_REPORT="$BASE_DIR/$REPORTFOLDERNAME/$EMAILREPORTFILEPATH"
    echo $APACHE_REPORT
    echo $EXTENT_REPORT
    echo $CUSTOM_REPORT

    # Move reports
    if [ "$USER" = "support" ]; then
        echo "${PASSWORD}" | su -c "echo '${PASSWORD}' | sudo -S mkdir -p $APACHE_REPORT" datazoic
        echo "${PASSWORD}" | su -c "echo '${PASSWORD}' | sudo -S mv $EXTENT_REPORT $APACHE_REPORT" datazoic
        echo "${PASSWORD}" | su -c "echo '${PASSWORD}' | sudo -S mv $CUSTOM_REPORT $APACHE_REPORT" datazoic
    elif [ "$USER" = "datazoic" ]; then
        echo "$PASSWORD" | sudo -S mkdir -p "$APACHE_REPORT"
        echo "$PASSWORD" | sudo -S mv "$EXTENT_REPORT" "$APACHE_REPORT"
        echo "$PASSWORD" | sudo -S mv "$CUSTOM_REPORT" "$APACHE_REPORT"
    fi
done