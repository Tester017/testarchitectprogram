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
EXECUTION_MODE="${10}"
ENV_ROLE="${11}"
EXECUTION_MODE_VALUE="${12}"
PASSWORD=""

if [ "$MODE" = true ]; then
    ENVIRONMENT="PRODUCTION"
else
    ENVIRONMENT="UAT"
fi

echo "PWD: $PWD"
echo "BUILD_NUMBER: $BUILD_NUMBER"
echo "CLIENT: $CLIENT"
echo "TYPES: $TYPES"
echo "MODE: $MODE"
echo "PROJECT: $PROJECT"
echo "USER: $USER"
echo "SECURE_DIRECTORY: $SECURE_DIRECTORY"
echo "ROLE: $ROLE"
echo "EXECUTION_MODE: $EXECUTION_MODE"
echo "EXECUTION_MODE_VALUE: $EXECUTION_MODE_VALUE"
echo "ENV_ROLE: $ENV_ROLE"
echo "PASSWORD: $PASSWORD" 
echo "ENVIRONMENT: $ENVIRONMENT" 

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
ENVCONFIG_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/EnvironmentConfig/$ENVIRONMENT/$CLIENT/$CLIENT.properties"
EMAILCONFIG_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/emailConfig.properties"
APACHE_REPORT="/var/www/html/testreport/$PROJECT/result$BUILD_NUMBER"
TESTSTRUCTURE_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/testStructure.properties"

echo "ENVCONFIG_PROPERTIES_FILE located in $ENVCONFIG_PROPERTIES_FILE"

# Users for email configurations
SD_USERS="shamilya@datazoic.com,karthikrajg@datazoic.com,nasgath@datazoic.com,agnalraymend@datazoic.com,aishwaryak@datazoic.com,dhilipbm@datazoic.com,arasu@datazoic.com,vigneshm@datazoic.com,nageshwari@datazoic.com,gnanatilak@datazoic.com,sasibooshan@datazoic.com,kowsalya@datazoic.com,nallendhiran@datazoic.com,salmankhan@datazoic.com"
QA_USERS="shamilya@datazoic.com,kowsalya@datazoic.com,nallendhiran@datazoic.com,salmankhan@datazoic.com"

# Function to write a property to the properties file
write_property() {
    local property_key=$1
    local property_value=$2
    echo "${property_key}=${property_value}" >> "$TRANSITION_PROPERTIES_FILE"
}

get_property() {
        local property_key=$1
        local property_file=$2
        grep "^${property_key}=" "$property_file" | cut -d'=' -f2-
}

# Write variables to transition.properties
write_property "ip_address" "$IP_ADDRESS"
write_property "jobName" "$PROJECT"
write_property "jenkinsBuildNumber" "$BUILD_NUMBER"

#cat "$TRANSITION_PROPERTIES_FILE"

echo "EXECUTION_MODE before if condition: $EXECUTION_MODE"
ALL_TICKETS=""

# Split TYPES or EXECUTION_MODE_VALUE into TYPES_ARRAY based on EXECUTION_MODE
if [ "$EXECUTION_MODE" = "Auto" ] || [ "$EXECUTION_MODE" = "JiraID" ]; then
    IFS=',' read -ra TYPES_ARRAY <<< "$TYPES"
elif [ "$EXECUTION_MODE" = "Version" ]; then
    # Split EXECUTION_MODE_VALUE by semicolon into TYPES_ARRAY
    IFS=';' read -ra TYPES_ARRAY <<< "$EXECUTION_MODE_VALUE"
    
    PREPEND_COMMA="false"  # Initialize a flag to track if a comma should prepend
    
    for TYPE in "${TYPES_ARRAY[@]}"; do
        # Get the property value for the current TYPE
        TICKETS=$(get_property "$TYPE" "$TESTSTRUCTURE_PROPERTIES_FILE")
        
        # Prepend a comma to TICKETS from the second iteration
        if [ "$PREPEND_COMMA" = "true" ]; then
            TICKETS=",$TICKETS"
        fi
        ALL_TICKETS="$ALL_TICKETS$TICKETS"
        # Output the modified TICKETS for debugging
        echo "Processed TICKETS: $ALL_TICKETS"
        
        # Set the flag to prepend a comma in the next iteration
        PREPEND_COMMA="true"
    done
    IFS=',' read -ra TYPES_ARRAY <<< "$ALL_TICKETS"   
elif [ "$EXECUTION_MODE" = "ModuleWise" ]; then
    IFS=',' read -ra TYPES_ARRAY <<< "$EXECUTION_MODE_VALUE"
else
    echo "Unknown EXECUTION_MODE: $EXECUTION_MODE"
    exit 1
fi

# Iterate over the array and run mvn test command
for TYPE in "${TYPES_ARRAY[@]}"; do
    if [ "$USER" = "support" ]; then
        echo "${PASSWORD}" | su -c "echo '${PASSWORD}' | sudo -S sync && echo 3 | sudo -S tee /proc/sys/vm/drop_caches" datazoic
    elif [ "$USER" = "datazoic" ]; then
        echo "${PASSWORD}" | sudo -S sync && echo 3 | sudo -S tee /proc/sys/vm/drop_caches
    fi

    # Update product configuration files
    sed -i "s/^HeadLess.*/HeadLess=true/" "$PRODUCTCONFIG_PROPERTIES_FILE"
    sed -i "s/^SendEmail.*/SendEmail=true/" "$PRODUCTCONFIG_PROPERTIES_FILE"
    sed -i "s/^ROLE.*/ROLE=$ROLE/" "$PRODUCTCONFIG_PROPERTIES_FILE"
    sed -i "s/^client.*/client=$CLIENT/" "$TESTSTRUCTURE_PROPERTIES_FILE"
    
    # Update environment configuration files
    sed -i "s/^ROLES.*/ROLES=$ENV_ROLE/" "$ENVCONFIG_PROPERTIES_FILE"

    # Update email configuration files
    if [ "$SECURE_DIRECTORY" = "securesd" ]; then
        sed -i "s/^TO.*/TO=$SD_USERS/" "$EMAILCONFIG_PROPERTIES_FILE"
    else
        sed -i "s/^TO.*/TO=$QA_USERS/" "$EMAILCONFIG_PROPERTIES_FILE"
    fi

    export DISPLAY=:99
    mvn test -DClient="$CLIENT" -Dtype="$TYPE" -DProductionMode="$MODE"

    # Function to get a property value
    
    REPORTFOLDERNAME=$(get_property "reportfoldername" "$TRANSITION_PROPERTIES_FILE")
    REPORTFILEPATH=$(get_property "reportFilePath" "$TRANSITION_PROPERTIES_FILE")
    EMAILREPORTFILEPATH=$(get_property "emailReportFilePath" "$TRANSITION_PROPERTIES_FILE")

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
