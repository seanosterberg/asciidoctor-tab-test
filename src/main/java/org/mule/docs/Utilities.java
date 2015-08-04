package org.mule.docs;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.UUID;

public class Utilities {

    public static String cleanPageFileNames(String originalFilename) {
        if (originalFilename != null) {
            String finalFilename;
            finalFilename = originalFilename.toLowerCase();
            finalFilename = StringUtils.replacePattern(finalFilename, "((\\%28)|(\\%29))", "-");
            finalFilename = StringUtils.replacePattern(finalFilename, "(\\+)(\\+*)", "-");
            finalFilename = StringUtils.replacePattern(finalFilename, "(\\-)(\\-*)", "-");
            finalFilename = StringUtils.replacePattern(finalFilename, " ", "-");
            return finalFilename;
        } else
            throw new NullPointerException("String references are null.");
    }

    public static String getRandomAlphaNumericString(int length) {
        StringBuffer buffer = new StringBuffer();
        while (buffer.length() < length) {
            buffer.append(uuidString());
        }
        //this part controls the length of the returned string
        return buffer.substring(0, length);
    }

    private static String uuidString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getOnlyContentDivFromHtml(String html) {
        Document doc = Jsoup.parse(html, "UTF-8");
        return doc.getElementById("content").html();
    }

    public static String getConcatPath(String[] filesOrDirectoriesToAppend) {
        String temp = filesOrDirectoriesToAppend[0];
        for (int i = 1; i < filesOrDirectoriesToAppend.length; i++) {
            if (!temp.isEmpty() && !temp.endsWith("/")) {
                temp = temp.concat("/").concat(filesOrDirectoriesToAppend[i]);
            } else {
                temp = temp.concat(filesOrDirectoriesToAppend[i]);
            }
        }
        return temp;
    }
}
