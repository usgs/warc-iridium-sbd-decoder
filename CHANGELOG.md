# USGS Iridium Short Burst Data (SBD) Decoder Library

## 1.5.0 - 05/29/2020
 * Integration of SpotBugs and address potential bugs reported
 * Integration of FindSecBugs plugin into SpotBugs and address potential bugs reported
 * Use ${revision} and ${changelist} in version string
 * Update to Spring Boot 2.3.0
 * Specify groupId for maven-compiler-plugin
 * Specify version for maven-compiler-plugin and maven-surefire-plugin in .domain
 * Update .project files for changes in <natures>
 * Update travel.yml to 'install verify' instead of 'clean install'
 * Add findbugs preference files

## 1.4.0 - 04/22/2020
 * Update to Spring Boot 2.2.6
 * Update to Guava 29.0
 * Update to JDT Annotation 2.2.400

## 1.3.0 - 09/24/2019
 * Update the getPayloadDecoder method so that the type name is included
 
## 1.2.1 - 03/27/2019
 * Remove premature byte check for pseudobinary b format in SbdParser
 * Update PseudobinaryBPayloadDecoder to use a queue, be able to decode even if all expected types aren't present, and to be sensitive to "NaN" values

## 1.2.0 - 03/25/2019
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
 
## 1.1.0 - 11/16/2018
 * Update .travis.yml to build OpenJDK11 and all branches
 * Update to Spring Boot 2.1.0
 * Update guava to 27.0-jre
 * Workaround for "Could not find or load main class org.apache.maven.surefire.booter.ForkedBooter" MAVEN error

## 1.0.0 - 05/11/2018
 * Initial release
