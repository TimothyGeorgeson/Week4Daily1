package com.example.consultants.week4daily1.utils;

public class DisplayUtil {

    //converts string to camel case
    public static String convertToCamelCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    //converts country codes to country names
    public static String codeToCountry(String code)
    {
        String result = "";
        switch (code) {
            case "AU":
                result = "Australia";
                break;
            case "BR":
                result = "Brazil";
                break;
            case "CA":
                result = "Canada";
                break;
            case "CH":
                result = "China";
                break;
            case "DE":
                result = "Germany";
                break;
            case "DK":
                result = "Denmark";
                break;
            case "ES":
                result = "Spain";
                break;
            case "FI":
                result = "Finland";
                break;
            case "FR":
                result = "France";
                break;
            case "GB":
                result = "Great Britain";
                break;
            case "IE":
                result = "Ireland";
                break;
            case "IR":
                result = "Iran";
                break;
            case "NL":
                result = "Netherlands";
                break;
            case "NZ":
                result = "New Zealand";
                break;
            case "TR":
                result = "Turkey";
                break;
            case "US":
                result = "United States";
                break;
        }
        return result;
    }
}
