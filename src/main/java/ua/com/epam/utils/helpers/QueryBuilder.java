package ua.com.epam.utils.helpers;

import java.util.Map;

public class QueryBuilder {

    public static String build(Map<String,String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (Map.Entry<String,String> entry : map.entrySet()) {
            if (sb.length() > 1) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    entry.getKey().toString(),
                    entry.getValue().toString()
            ));
        }
        return sb.toString();
    }

}
