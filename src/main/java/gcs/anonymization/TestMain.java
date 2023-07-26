package gcs.anonymization;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import gcs.anonymization.filters.Filters;

import static java.lang.System.exit;

public class TestMain {
    public static void main(String[] args) throws IOException {
        String test_event = Files.readString(Paths.get("/home/inryatt/uni/sarai/udf_test/src/main/resources/usrName5.txt"), StandardCharsets.UTF_8); // Replace this with your own file -- DO NOT ADD IT TO GIT.

        test_event = Filters.removeUserNames_Test(test_event,"dst_user_name");
        test_event = Filters.removeUserNames_Test(test_event,"usrName");

        test_event = Filters.removeUserNames(test_event);
        System.out.println(test_event);
    }

}