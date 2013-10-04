package com.paypal.billsafe.dojos;

import java.util.Random;

public class RandomWordGenerator {

    public static enum Letter {

        E, N, I, S, R, A, T, D, H, U, L, C, G, M, O, B, W, F, K, Z, P, V, ß, J, Y, X, Q
    }

    private int wordLength;

    private Random random;



    public RandomWordGenerator(int wordLength) {
        if (wordLength < 0) {
            throw new IllegalArgumentException("wordLength cannot be negative");
        }
        this.wordLength = wordLength;
        this.random = new Random();
    }



    public String generateWord() {
        StringBuilder sb = new StringBuilder(wordLength);
        for (int i = 0; i < wordLength; i++) {
            Letter l = generateLetter(3);
            sb.append(l.name());
        }
        return sb.toString();
    }



    private Letter generateLetter(int iterations) {

        Letter result = null;

        for (int i = 0; i < iterations; i++) {
            Letter next = Letter.values()[random.nextInt(27)];
            if (result == null || result.compareTo(next) > 0) {
                result = next;
            }
        }

        return result;
    }

}
