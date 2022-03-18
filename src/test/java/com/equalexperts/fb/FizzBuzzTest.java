package com.equalexperts.fb;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FizzBuzzTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldFailForInvalidNumberRange() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(
                "Invalid range of numbers provided : min 1 is greater than max -1 Please correct the input and try again");
        FizzBuzz fizzBuzz = new FizzBuzz();
        fizzBuzz.play(1, -1);
    }

    @Test
    public void verifyFizzBuzzForNumbersFromOneToTwenty() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.play(1, 20);
        Assert.assertEquals("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz fizz: 4 buzz: 3 fizzbuzz: 1 lucky: 2 integer: 10", result);
    }

}
