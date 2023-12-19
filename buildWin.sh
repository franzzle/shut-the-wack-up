#!/bin/bash

# Get the user's home directory
HOME_DIR=$HOME

# Define platform-specific variables
JDK_FOLDER="jdk_windows64_Temurin8"
JDK_ARCHIVE="OpenJDK8U-jdk_x64_windows_hotspot_8u382b05.zip"
ICON_FILE="lwjgl3/icons/icon.ico"

# Run Gradle tasks
./gradlew clean lwjgl3:distZip

# Set output directory
OUTPUT_NAME="$HOME_DIR/Development/GameDeployableDevelopment/shutthewackup_windows64"

# Remove existing output directory
rm -rf "$OUTPUT_NAME"

# Pack and package the application
java -jar "$HOME_DIR/Development/Tooling/packr/packr-all-4.0.0.jar" \
     --platform windows64 \
     --jdk "$HOME_DIR/Development/Tooling/$JDK_FOLDER/$JDK_ARCHIVE"  \
     --executable shutthewackup \
     --classpath "$PWD/lwjgl3/build/lib/shut-the-wack-up-1.0.1.jar" \
     --removelibs "$PWD/lwjgl3/build/lib/shut-the-wack-up-1.0.1.jar" \
     --mainclass com.pimpedpixel.games.ShutTheWackUpGame \
     --vmargs Xmx1G \
     --resources "$PWD/assets/." \
     --minimizejre soft \
     --icon "$ICON_FILE" \
     --output "$OUTPUT_NAME"


