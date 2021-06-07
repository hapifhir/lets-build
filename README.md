# HAPI-FHIR Let's Build

# Precursors

* Check this project out to your local hard drive.
* Open it in an IDE
* Have the following tools installed:
   * [Java JDK Version 11.x.x](https://adoptopenjdk.net/) (This is the recommended version but Java 8 and above should also work)
   * [Apache Maven](https://maven.apache.org/)

# Hour 1 - Build a FHIR Data Mapper

**Rationale:** When adopting FHIR, a common scenario is needing to convert your existing data into the FHIR model. This can be a challenging first step, but if you approach it systematically it can be easy. 

**Exercise:** For this exercise, we will be building a mapper that converts existing data a CSV file into FHIR Patient and Observation resources. 

We'll be using a sample data file from the CDC NHANES (National Health and Nutrition Examination Study) publicly available sample data set. The format of the data set is described at [this link](https://wwwn.cdc.gov/Nchs/Nhanes/2017-2018/CBC_J.htm) but we have reworked the format a bit to add fake patient identities and timestamps to the data.

The input CSV file can be found here: https://github.com/hapifhir/lets-build/blob/main/src/main/resources/sample-data.csv 

## Approach

The input data looks like the following:

```csv
SEQN   , TIMESTAMP               , PATIENT_ID, PATIENT_FAMILYNAME, PATIENT_GIVENNAME, PATIENT_GENDER, WBC, RBC, HB
93704.0, 2020-11-13T07:47:35.964Z, PT00002   , Simpson           , Marge            , F             , 7.4, 0.1, 13.1
```

Note the columns:

* SEQN: This is a unique identifier for the test
* TIMESTAMP: This is the timestamp for the test
* Patient detail columns (note that the patients repeat so you will want to ):
   * PATIENT_ID: This is the ID of the patient
   * PATIENT_FAMILYNAME: This is the family (last) name of the patient
   * PATIENT_GIVENNAME: This is the given (first) name of the patient
   * PATIENT_GENDER: This is the gender of the patient
* Test result columns (each of these will be a separate Observation resource):
   * WBC: "White Blood Cell Count": This is a count of the number of white blood cells in your blood (These cells deliver oxygen)
   * RBC: "Red Blood Cell Count": This is a count of the number of red blood cells in your blood (These cells are an important part of the immune system)
   * HB: "Hemoglobin": This a measurement of the amount of hemoglobin protein in your blood (this iron-rich protein carries oxygen, among other things)



## Writing a Mapper

* Open the following class: [ca.uhn.fhir.letsbuild.upload.CsvDataUploader](https://github.com/hapifhir/lets-build/blob/main/src/main/java/ca/uhn/fhir/letsbuild/upload/CsvDataUploader.java).

* Notice how this class has a basic skeleton that reads in a CSV file.

* Create a FHIR Client:
 
   * Using the instructions here: https://hapifhir.io/hapi-fhir/docs/client/generic_client.html
   * Use the public hapi server URL: http://hapi.fhir.org/baseR4
 
* For each row, create the Patient resource. Use an Update (PUT) operation so that you can control the ID of the patient resource. For example, you might want *Marge Simpson* to have a resource ID of "Patient/PT00002"

* For each row, create 3 Observation resources, one for each of the 3 tests.

## Hints:

* Don't optimize at first! There are lots of ways this code can be made efficient, but it's better to start by getting it working, then worry about performance later.

* You can use the PATIENT_ID column as the resource ID for the Patient resource. Using a client-assigned ID for your resources makes the outcome predictable, and means that you can re-run your uploader as many times as you like without constantly creating new datasets. 

* You can use the SEQN column as the resource ID for the Observation resources, but you will need to append something to it since there are 3 Observations per row.

# Bonus - Secure Your FHIR Server

In this exercise we will add simple authentication and authorization to our server. To keep things simple:

* We will secure only read operations, and we'll leave write operations unsecured. Obviously this isn't a setup you would use for real, but all of the read security principles work exactly the same for writing and can be used for those operations too.

* We will hardcode our credentials in the server code. This is also not a practice you should ever use in a real system, but it makes it easy to show the concepts and can be replaced with something more robust as needed.

## Approach

Open the class `ServerAuthorizationInterceptor` and 