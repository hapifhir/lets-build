package ca.uhn.fhir.letsbuild.upload;

import ca.uhn.fhir.context.FhirContext;
import com.google.common.base.Charsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class CsvDataUploader {

    private static final Logger ourLog = LoggerFactory.getLogger(CsvDataUploader.class);

    public static void main(String[] theArgs) throws Exception {

        FhirContext ctx = FhirContext.forR4Cached();

        // Open the CSV file for reading
        try (InputStream inputStream = new FileInputStream("src/main/resources/sample-data.csv")) {
            Reader reader = new InputStreamReader(inputStream, Charsets.UTF_8);

            CSVFormat format = CSVFormat.EXCEL
                    .withFirstRecordAsHeader()
                    .withDelimiter(',');
            CSVParser csvParser = format.parse(reader);

            // Loop throw each row in the CSV file
            for (CSVRecord nextRecord : csvParser.getRecords()) {

                // Sequence number - This could be used as an ID for generated resources
                String seqN = nextRecord.get("SEQN");

                // Timestamp - This will be formatted in ISO8601 format
                String timestamp = nextRecord.get("TIMESTAMP");

                // Patient ID
                String patientId = nextRecord.get("PATIENT_ID");

                // Add a log line - you can copy this to add more helpful logging
                ourLog.info("Processing row with sequence {} for patient ID {}", seqN, patientId);

                // Patient Family Name
                String patientFamilyName = nextRecord.get("PATIENT_FAMILYNAME");

                // Patient Given Name
                String patientGivenName = nextRecord.get("PATIENT_GIVENNAME");

                // Patient Gender - Values will be "M" or "F"
                String patientGender = nextRecord.get("PATIENT_GENDER");

                // White blood cell count - This corresponds to LOINC code:
                // Code:        6690-2
                // Display:     Leukocytes [#/volume] in Blood by Automated count
                // Unit System: http://unitsofmeasure.org
                // Unit Code:   10*3/uL
                String rbc = nextRecord.get("RBC");

                // White blood cell count - This corresponds to LOINC code:
                // Code:        789-8
                // Display:     Erythrocytes [#/volume] in Blood by Automated count
                // Unit System: http://unitsofmeasure.org
                // Unit Code:   10*6/uL
                String wbc = nextRecord.get("WBC");

                // Hemoglobin
                // Code:        718-7
                // Display:     Hemoglobin [Mass/volume] in Blood
                // Unit System: http://unitsofmeasure.org
                // Unit Code:   g/dL
                String hb = nextRecord.get("HB");

                // Day 1 Exercise:
                // Create a Patient resource, and 3 Observation resources, and
                // log them to the console.

            }
        }
    }

}
