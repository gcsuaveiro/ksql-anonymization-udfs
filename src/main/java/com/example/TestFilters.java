package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class TestFilters {
    public static void main(String[] args) throws IOException {
        String test_event = Files.readString(Paths.get("/home/inryatt/uni/CA/prj2/java_ver/test_filters/src/main/resources/firewall_log_json.txt"), StandardCharsets.UTF_8);

        //test_event = removeIPs(test_event);
        //System.out.println(removeIDs(test_event));

        test_event = removeUserNames(test_event);
        System.out.println(test_event);
    }

    public static String removeIPs(String message){
        // Doesn't catch ports -- Likely won't need to be removed.
        String regex = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
        String replacement = "x.x.x.x";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        return matcher.replaceAll(replacement);
    }

    public static String removeIDs(String message){
        String regex = "\\b[A-Fa-f0-9]{8}-[A-Fa-f0-9]{4}-[A-Fa-f0-9]{4}-[A-Fa-f0-9]{4}-[A-Fa-f0-9]{12}\\b";
        String replacement = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        return matcher.replaceAll(replacement);
    }

    public static String removeUserNames(String message){
        // find the username
        String regex;
        String user,mail;
        boolean hasMail = true;
        if (message.contains("checkpoint-beat-filebeat")){
            regex =  "dst_user_name:\\\\\"[^\\\\\"]*";
        }
        //catch-all
        else {
            regex = "";
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            System.out.println("Found username");

            String content = matcher.group(0);
            content=content.split("\"")[1];

            user = content.split(" \\(")[0];
            mail = content.split(" \\(")[1].replaceFirst("\\)","");
            System.out.println(user);
            System.out.println(mail);
        }
        else{
            return message; // nothing to do
        }

        // ** Hash found values **
        String userHash = hashThisString(user);
        String mailHash = null;
        if (user.equals(mail)){
            hasMail=false;
        }
        else{
            if (mail.contains("@")){
                mail = mail.split("@")[0]; // user@gmail.com becomes sddfg@gmail.com, still anonymized but can be seen it's an email
            }
            mailHash = hashThisString(mail);
        }

        message = message.replaceAll(user,userHash);
        if (hasMail){
            message = message.replaceAll(mail,mailHash);
        }
        return message;
    }

    public static String hashThisString(String input)
    {
        try {
            // Create a SHA-3-256 MessageDigest instance
            MessageDigest sha3Digest = MessageDigest.getInstance("SHA3-256");

            // Convert the input string to bytes
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

            // Generate the hash value
            byte[] hashBytes = sha3Digest.digest(inputBytes);

            // Convert the hash bytes to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Print the hash value
            System.out.println("SHA-3 Hash: " + hexString.toString());
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            exit(1);
        }
        return "compiler stop complaining";
    }

}