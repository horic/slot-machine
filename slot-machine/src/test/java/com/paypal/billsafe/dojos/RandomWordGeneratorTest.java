package com.paypal.billsafe.dojos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

public class RandomWordGeneratorTest {

    @Test
    public void should_returnWordsWithGivenLength() {
        String word = createFixture(10).generateWord();
        assertThat("should return words with 10 letters if 10 is given wordLength", word.length(), equalTo(10));
    }



    @Test
    public void generatesEmptyStrings() {
        String word = createFixture(0).generateWord();
        assertThat("shoud return empty string if 0 is given wordLength", word.isEmpty(), is(true));
    }



    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionOnNegativeWordLength() {
        createFixture(-1);
    }



    private RandomWordGenerator createFixture(int wordLength) {
        return new RandomWordGenerator(wordLength);
    }

}
