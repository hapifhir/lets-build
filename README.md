# HAPI-FHIR Let's Build

# Step 1 - Build a FHIR Data Mapper

We'll be using a sample data file from the CDC NHANES (National Health and Nutrition Examination Study) publicly available sample data set. The format of the data set is described at [this link](https://wwwn.cdc.gov/Nchs/Nhanes/2017-2018/CBC_J.htm) but we have reworked the format a bit to add fake patient identities and timestamps to the data.

The input 
ca.uhn.fhir.letsbuild.upload.CsvDataUploader
 