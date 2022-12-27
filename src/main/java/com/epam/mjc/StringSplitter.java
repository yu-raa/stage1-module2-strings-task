package com.epam.mjc;

import java.util.*;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder regex = new StringBuilder("[");
        for (String delim : delimiters) {
            regex.append("(");
            regex.append(delim);
            regex.append(")");
        }

        regex.append("]+");

        String[] substrings = source.split(regex.toString());

        return Arrays.stream(substrings).filter(str -> (!str.isEmpty() && !str.isBlank())).collect(Collectors.toList());
    }
}
