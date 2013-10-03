package com.paypal.billsafe.dojos;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class WordFinder {

    private BufferedReader reader;



    public WordFinder(BufferedReader reader) {
        this.reader = reader;
    }



    public boolean find(String needle) {
        boolean found = false;
        try {
            if (StringUtils.isNotBlank(needle)) {
                String word = null;
                while ((word = reader.readLine()) != null) {
                    if (word.equalsIgnoreCase(needle)) {
                        found = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // ignore;
            }
        }
        return found;
    }

}
