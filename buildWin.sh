#!/bin/bash

# Get the user's home directory
HOME_DIR=$HOME

# Define platform-specific variables
JDK_FOLDER="jdk_windows64_Temurin8"
JDK_ARCHIVE="OpenJDK8U-jdk_x64_windows_hotspot_8u382b05.zip"
ICON_FILE="icons/icon.ico"

# Run Gradle tasks
./gradlew clean desktop:dist

# Set output directory
OUTPUT_NAME="$HOME_DIR/Development/GameDeployableDevelopment/shutthewackup_windows64"

# Remove existing output directory
rm -rf "$OUTPUT_NAME"

# Pack and package the application
java -jar "$HOME_DIR/Development/Tooling/packr/packr-all-4.0.0.jar" \
     --platform windows64 \
     --jdk "$HOME_DIR/Development/Tooling/$JDK_FOLDER/$JDK_ARCHIVE"  \
     --executable shutthewackup \
     --classpath "$PWD/desktop/build/lib/desktop-1.0.jar" \
     --removelibs "$PWD/desktop/build/lib/desktop-1.0.jar" \
     --mainclass com.franzzle.cgi.topotablet.desktop.DesktopLauncher \
     --vmargs Xmx1G \
     --resources "$PWD/core/assets/." \
     --minimizejre soft \
     --icon "$ICON_FILE" \
     --output "$OUTPUT_NAME"


