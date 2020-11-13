# HAPI-FHIR Let's Build

# Precursors

* Check this project out to your local hard drive.
* Open it in an IDE
* Have the following tools installed:
   * [Java JDK Version 11.x.x](https://adoptopenjdk.net/) (This is the recommended version but Java 8 and above should also work)
   * [Apache Maven](https://maven.apache.org/)

# Step 1 - Build a FHIR Data Mapper

**Rationale:** When adopting FHIR, a common scenario is needing to convert your existing data into the FHIR model. This can be a challenging first step, but if you approach it systematically it can be easy. 

**Exercise:** For this exercise, we will be building a mapper that converts existing data a CSV file into FHIR Patient and Observation resources. 

We'll be using a sample data file from the CDC NHANES (National Health and Nutrition Examination Study) publicly available sample data set. The format of the data set is described at [this link](https://wwwn.cdc.gov/Nchs/Nhanes/2017-2018/CBC_J.htm) but we have reworked the format a bit to add fake patient identities and timestamps to the data.

The input CSV file can be found here: https://github.com/hapifhir/lets-build/blob/main/src/main/resources/sample-data.csv 

## Approach




The input file contains the  
ca.uhn.fhir.letsbuild.upload.CsvDataUploader
 