package gcs.anonymization;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import gcs.anonymization.filters.Filters;


public class TestMain {
    public static void main(String[] args) throws IOException {
        String test_event = Files.readString(Paths.get("/home/inryatt/uni/sarai/udf_test/src/main/resources/usrName.txt"), StandardCharsets.UTF_8); // Replace this with your own file -- DO NOT ADD IT TO GIT.

        test_event = Filters.removeUserNames_v2(test_event,"usrName");
        //test_event= Filters.removeFieldContents(test_event,"cookie");
        //test_event = Filters.removeUserNames(test_event);


        String aux =test_event;
        aux = Filters.removeUserNames_v2(aux,"usrName");
        aux = Filters.removeUserNames_v2(aux,"src_user_name");
        aux = Filters.removeUserNames_v2(aux,"src_user_dn");
        aux = Filters.removeUserNames_v2(aux,"originsicname");

        System.out.println(test_event);
    }

}