#!/bin/bash
cd ../workspace
rootDir=$(pwd)
echo ${rootDir}
version=0.7
verflex=0.1
verutils=0.2
cd ${rootDir}/alp-utils
mvn clean install -Dmaven.test.skip=true
cd ${rootDir}/alp-reporter
mvn clean install -Dmaven.test.skip=true
cd ${rootDir}/alp-selenium
mvn clean install -Dmaven.test.skip=true
cd ${rootDir}/alp-flexpilot
mvn clean install -Dmaven.test.skip=true
mvn install:install-file -Dfile=${rootDir}/alp-utils/target/alp-utils-${verutils}.jar -DgroupId=com.lohika.alp -DartifactId=alp-utils -Dversion=${verutils} -Dpackaging=jar
mvn install:install-file -Dfile=${rootDir}/alp-reporter/target/alp-reporter-${version}.jar -DgroupId=com.lohika.alp -DartifactId=alp-reporter -Dversion=${version} -Dpackaging=jar
mvn install:install-file -Dfile=${rootDir}/alp-flexpilot/target/alp-flexpilot-${verflex}.jar -DgroupId=com.lohika.alp -DartifactId=alp-flexpilot -Dversion=${verflex} -Dpackaging=jar
mvn install:install-file -Dfile=${rootDir}/alp-selenium/target/alp-selenium-${version}.jar -DgroupId=com.lohika.alp -DartifactId=alp-selenium -Dversion=${version} -Dpackaging=jar
