package com.paypal.billsafe.dojos;

import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

public class WordFinder {


    public boolean find(String needle, Reader reader) {
        
        IOUtils.lineIterator(reader);
        LineIterator it = IOUtils.lineIterator(reader);
        
        boolean found = false;
        while (it.hasNext()) {
            if (StringUtils.equalsIgnoreCase(needle, it.nextLine())) {
                found = true;
                break;
            }
        }
        IOUtils.closeQuietly(reader);
        return found;
    }
    
}
