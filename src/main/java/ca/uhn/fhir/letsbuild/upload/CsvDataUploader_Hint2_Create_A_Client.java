package ca.uhn.fhir.letsbuild.upload;

import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Patient;

public class CsvDataUploader_Hint2_Create_A_Client {

    public static void main(String[] args) {

        // Create the Patient resource
        Patient patient = new Patient();
        patient.setId("Patient/ABC");
        patient.addName().setFamily("Agnew").addGiven("James");

        // Gender code needs to be mapped
        String gender = "M";
        switch (gender) {
            case "M":
                patient.setGender(Enumerations.AdministrativeGender.MALE);
                break;
            case "F":
                patient.setGender(Enumerations.AdministrativeGender.FEMALE);
                break;
        }


    }

}
