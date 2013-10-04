package com.paypal.billsafe.dojos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class WordFinderTest {

    @Test
    public void returnsTrueIfReaderReadsWord_ignoresCase() {

        String toFind = "ValUe";

        WordFinder finder = createFixture();

        boolean actual = finder.find(toFind, createReaderMock(toFind, "WORD", "ANOTHER"));

        assertTrue(actual);
    }



    @Test
    public void closesReaderIfWordFound() {
        String toFind = "ValUe";
        BufferedReader reader = createReaderMock(toFind, "WORD", "ANOTHER");
        createFixture().find(toFind, reader);
        try {
            verify(reader).close();
        } catch (IOException e) {
        }
    }



    @Test
    public void closesReaderIfWordNotFound() {
        String toFind = "ValUe";
        BufferedReader reader = createReaderMock("HELO", "WORD", "ANOTHER");
        createFixture().find(toFind, reader);
        try {
            verify(reader).close();
        } catch (IOException e) {
        }
    }



    @Test
    public void closesReaderIfNeedleIsEmpty() {
        String toFind = "";
        BufferedReader reader = createReaderMock("HELO", "WORD", "ANOTHER");
        createFixture().find(toFind, reader);
        try {
            verify(reader).close();
        } catch (IOException e) {
        }
    }



    private BufferedReader createReaderMock(String word, String... words) {
        BufferedReader reader = mock(BufferedReader.class);
        try {

            String[] params = Arrays.copyOf(words, words.length + 1);

            params[words.length] = null;

            when(reader.readLine()).thenReturn(word, params);
        } catch (IOException e) {
        }
        return reader;
    }



    private WordFinder createFixture() {
        return new WordFinder();
    }
}
