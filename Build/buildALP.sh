#!/bin/bash
cd ../workspace
rootDir=$(pwd)
echo ${rootDir}
#version=0.6
cd ${rootDir}/alp-utils
mvn clean install
cd ${rootDir}/alp-reporter
mvn clean install
cd ${rootDir}/alp-selenium
mvn clean install
cd ${rootDir}/alp-flexpilot
mvn clean install
mvn install:install-file -Dfile=${rootDir}/alp-utils/target/alp-utils-${version}.jar -DgroupId=com.lohika.alp -DartifactId=alp-utils -Dversion=${version} -Dpackaging=jar
mvn install:install-file -Dfile=${rootDir}/alp-reporter/target/alp-reporter-${version}.jar -DgroupId=com.lohika.alp -DartifactId=alp-reporter -Dversion=${version} -Dpackaging=jar
mvn install:install-file -Dfile=${rootDir}/alp-flexpilot/target/alp-flexpilot-${version}.jar -DgroupId=com.lohika.alp -DartifactId=alp-flexpilot -Dversion=${version} -Dpackaging=jar
mvn install:install-file -Dfile=${rootDir}/alp-selenium/target/alp-selenium-0.6.1.jar -DgroupId=com.lohika.alp -DartifactId=alp-selenium -Dversion=0.6.1 -Dpackaging=jar
