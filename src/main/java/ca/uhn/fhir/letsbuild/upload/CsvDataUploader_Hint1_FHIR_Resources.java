package ca.uhn.fhir.letsbuild.upload;

public class CsvDataUploader_Hint1_FHIR_Resources {

    /*

    Sample complete patient resource:
    {
        "resourceType": "Patient",
        "id": "PT00004",
        "name": [
           {
              "family": "Simpson",
              "given": [ "Lisa" ]
           }
        ],
        "gender": "female"
    }



    Sample Observation:
    {
       "resourceType": "Observation",
       "id": "rbc-93808.0",
       "status": "final",
       "code": {
         "coding": [ {
             "system": "http://loinc.org",
             "code": "6690-2",
             "display": "Leukocytes [#/volume] in Blood by Automated count"
          } ]
       },
       "subject": {
          "reference": "Patient/PT00004"
       },
       "effectiveDateTime": "2021-01-05T15:37:27.110Z",
       "valueQuantity": {
          "value": 5.4,
          "unit": "10*3/uL",
          "system": "http://unitsofmeasure.org",
          "code": "10*3/uL"
       }
     }

     */

}
