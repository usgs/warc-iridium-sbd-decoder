# USGS Iridium Short Burst Data (SBD) Decoder Library

## 2.1.0 - 2021-12-13
 * Explicitly specify property log4j2.version 2.15.0
 * Update Spring Boot to 2.6.1
 * com.github.spotbugs:spotbugs-maven-plugin 4.0.4 -> 4.2.3
 * com.google.guava:guava 29.0-jre -> 31.0.1-jre
 * com.h3xstream.findsecbugs:findsecbugs-plugin 1.10.1 -> 1.11.0
 * Add org.springframework.batch:spring-batch-test
 * Migrate JUnit tests and annotations for Junit Jupiter

## 2.0.0 - 2020-09-25
 * Migrate to Java 11 release 
 * Update Spring Boot to 2.3.4.RELEASE
 * com.github.spotbugs:spotbugs-maven-plugin 4.0.0 -> 4.0.4
 * org.eclipse.jdt:org.eclipse.jdt.annotation 2.2.400 -> 2.2.600
 * Remove unused/unecessary natures and build specs from .project
 * Remove argLine for forked booter (https://stackoverflow.com/questions/53010200/maven-surefire-could-not-find-forkedbooter-class)
 * Update .classpath files for Java 11
 * Removed unused/unnecessary preferences in .settings
 * Add spotbugs-exclude.xml file
  
## 1.5.0 - 2020-05-29
 * Integration of SpotBugs and address potential bugs reported
 * Integration of FindSecBugs plugin into SpotBugs and address potential bugs reported
 * Use ${revision} and ${changelist} in version string
 * Update to Spring Boot 2.3.0
 * Specify groupId for maven-compiler-plugin
 * Specify version for maven-compiler-plugin and maven-surefire-plugin in .domain
 * Update .project files for changes in <natures>
 * Update travel.yml to 'install verify' instead of 'clean install', remove openjdk11 build 
 * Add findbugs preference files

## 1.4.0 - 2020-04-22
 * Update to Spring Boot 2.2.6
 * Update to Guava 29.0
 * Update to JDT Annotation 2.2.400

## 1.3.0 - 2019-09-24
 * Update the getPayloadDecoder method so that the type name is included
 
## 1.2.1 - 2019-03-27
 * Remove premature byte check for pseudobinary b format in SbdParser
 * Update PseudobinaryBPayloadDecoder to use a queue, be able to decode even if all expected types aren't present, and to be sensitive to "NaN" values

## 1.2.0 - 2019-03-25
 * Add support for decoding Sutron Standard CSV messages
 * Update to Spring Boot 2.1.3
 * Update to Guava 27.1
 * Update to JDT Annotation 2.2.200
 * Update to Commons-CSV 1.6
 * SbdDataType is now Comparable
 * Add SbdDataTypeProvider
 * Add PayloadDecoder interface, along with PseudobinaryBPayloadDecoder and SutronStandardCsvPayloadDecoder
 * Add PayloadType
 * Rename BinaryParser to SbdParser
 * Remove StationDataTypes & test
 * SbdParser now requires a set of SbdDataType (to support CSV decoding)
 
## 1.1.0 - 2018-11-16
 * Update .travis.yml to build OpenJDK11 and all branches
 * Update to Spring Boot 2.1.0
 * Update guava to 27.0-jre
 * Workaround for "Could not find or load main class org.apache.maven.surefire.booter.ForkedBooter" MAVEN error

## 1.0.0 - 2018-05-11
 * Initial release
