#!/bin/bash

# Assign command-line arguments to variables
BUILD_NUMBER="$1"
CLIENT="$2"
EXECUTION_MODE="$3"
EXECUTION_MODE_VALUE="$4"
TYPE="$5"
PROJECT="$6"
USER="$7"

# Define file paths
BASE_DIR="/home/$USER/workspace/$PROJECT/QAAutomation$BUILD_NUMBER/Product-Automation-Testing-V2/playwright"
TRANSITION_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/transition.properties"
TESTSTRUCTURE_PROPERTIES_FILE="$BASE_DIR/src/test/resources/Configuration/testStructure.properties"

# Print all variables for debugging
echo "********************"
echo "Printing all variables:"
echo "BUILD_NUMBER: $BUILD_NUMBER"
echo "CLIENT: $CLIENT"
echo "EXECUTION_MODE: $EXECUTION_MODE"
echo "EXECUTION_MODE_VALUE: $EXECUTION_MODE_VALUE"
echo "TYPE: $TYPE"
echo "PROJECT: $PROJECT"
echo "USER: $USER"
echo "BASE_DIR: $BASE_DIR"
echo "TRANSITION_PROPERTIES_FILE: $TRANSITION_PROPERTIES_FILE"
echo "TESTSTRUCTURE_PROPERTIES_FILE: $TESTSTRUCTURE_PROPERTIES_FILE"
echo "********************"

# Navigate to the base directory
cd "$BASE_DIR" || { echo "Failed to change directory to $BASE_DIR"; exit 1; }

# Handle EXECUTION_MODE logic
if [ "$EXECUTION_MODE" = "Auto" ] || [ "$EXECUTION_MODE" = "JiraID" ]; then
    EXECUTION_MODE_VALUE="$TYPE"
    echo "EXECUTION_MODE_VALUE set to: $EXECUTION_MODE_VALUE"
fi

# Output debug information
echo "Running in $EXECUTION_MODE mode..."
echo "EXECUTION_MODE_VALUE: $EXECUTION_MODE_VALUE"
echo "TRANSITION_PROPERTIES_FILE: $TRANSITION_PROPERTIES_FILE"
echo "TESTSTRUCTURE_PROPERTIES_FILE: $TESTSTRUCTURE_PROPERTIES_FILE"

# Execute Maven command
mvn test \
    -DClient=TEST \
    -Dtype=testGenerator \
    -Dmode="$EXECUTION_MODE" \
    -DmodeValue="$EXECUTION_MODE_VALUE" \
    -Denv="$CLIENT"
