import com.google.common.base.Charsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.time.DateUtils;
import org.hl7.fhir.r4.model.InstantType;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;

public class DataConverter {

    public static void main(String[] theArgs) throws Exception {

        try (InputStream inputStream = new FileInputStream("src/test/resources/original-data.csv")) {
            try (FileWriter writer = new FileWriter("src/main/resources/sample-data.csv", false)) {

                writer.append("SEQN").append(",");
                writer.append("TIMESTAMP").append(",");
                writer.append("PATIENT_ID").append(",");
                writer.append("PATIENT_FAMILYNAME").append(",");
                writer.append("PATIENT_GIVENNAME").append(",");
                writer.append("PATIENT_GENDER").append(",");
                writer.append("WBC").append(",");
                writer.append("RBC").append(",");
                writer.append("HB").append("\n");

                Reader reader = new InputStreamReader(inputStream, Charsets.UTF_8);

                CSVFormat format = CSVFormat.EXCEL
                        .withFirstRecordAsHeader()
                        .withDelimiter(',');
                CSVParser csvParser = format.parse(reader);

                long date = System.currentTimeMillis();
                int patientIndex = 0;
                String[] patientIds = new String[]{"00001", "00002", "00003", "00004", "00005"};
                String[] patientFamilyNames = new String[]{"Simpson", "Simpson", "Simpson", "Simpson", "Simpson"};
                String[] patientGivenNames = new String[]{"Homer", "Marge", "Bart", "Lisa", "Maggie"};
                String[] patientGenders = new String[]{"M", "F", "M", "F", "F"};

                int count = 0;
                for (CSVRecord nextRecord : csvParser.getRecords()) {
                    if (count++ > 100) {
                        break;
                    }

                    String seqN = nextRecord.get("SEQN");
                    String whiteBloodCount = nextRecord.get("LBXWBCSI");
                    String redBloodCount = nextRecord.get("LBXNRBC");
                    String hemoglobin = nextRecord.get("LBXHGB");

                    if (whiteBloodCount.toLowerCase().contains("nan")) {
                        continue;
                    }

                    date += ((float) DateUtils.MILLIS_PER_DAY * Math.random());
                    patientIndex = ++patientIndex % patientIds.length;
                    String timestamp = new InstantType(new Date(date)).setTimeZoneZulu(true).getValueAsString();
                    String patientId = patientIds[patientIndex];
                    String patientFamilyName = patientFamilyNames[patientIndex];
                    String patientGivenName = patientGivenNames[patientIndex];
                    String patientGender = patientGenders[patientIndex];

                    writer.append(seqN).append(",");
                    writer.append(timestamp).append(",");
                    writer.append(patientId).append(",");
                    writer.append(patientFamilyName).append(",");
                    writer.append(patientGivenName).append(",");
                    writer.append(patientGender).append(",");
                    writer.append(whiteBloodCount).append(",");
                    writer.append(redBloodCount).append(",");
                    writer.append(hemoglobin).append("\n");


                }
            }
        }
    }

}
