package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder stringBuilder1 = new StringBuilder(source);
        String[] delimits = new String[delimiters.size()];
        delimiters.toArray(delimits);
        LinkedList<String> list = new LinkedList<>();
        while (stringBuilder1.length() > 0) {
            String sub = "";
            int lengthOfMinimal = stringBuilder1.length();
            int ind = 0;
            for (int i = 0; i < delimiters.size(); i++) {
                String delim = delimits[i];
                try {
                    String subStr = new StringTokenizer(stringBuilder1.toString(), delim, false).nextToken();
                    if (subStr.length() <= lengthOfMinimal && subStr.length() > 0 && !subStr.equals(delim)) {
                        boolean flag = false;
                        for (String deli : delimits) {
                            if (subStr.contains(deli)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            continue;
                        }
                        sub = subStr;
                        ind = i;
                        lengthOfMinimal = subStr.length();
                    }
                }
                catch (NoSuchElementException exc) {
                    sub = stringBuilder1.toString();
                    boolean flag1 = false;
                    for (String deli : delimits) {
                        if (sub.contains(deli)) {
                            flag1 = true;
                            break;
                        }
                    }

                    if (flag1) {
                        sub = "";
                    }
                }
            }

            if (sub.length() > 0) {
                list.add(sub);
            }
            stringBuilder1 = stringBuilder1.delete(0, sub.length() + delimits[ind].length());
        }

        return list;
    }
}
