package com.equalexperts.fb;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzz {

    private static final String COLON = ":";
    private static final String DELIMITER_SPACE = " ";
    private static final String FIZZ = "fizz";
    private static final String BUZZ = "buzz";
    private static final String FIZZ_BUZZ = FIZZ + BUZZ;
    private static final String LUCKY = "lucky";
    private static final String INTEGER = "integer";
    private static final List<String> STATISTICS_KEYS = Arrays.asList(FIZZ, BUZZ, FIZZ_BUZZ, LUCKY, INTEGER);


    //TODO : Inject the predicates/Functions as list through constructor and remove if else conditions using a chain of responsibility if it becomes more complex
    //TODO: So that what is changing can be controlled without modifying this class.
    private final IntPredicate fizzPredicate = number -> number % 3 == 0;
    private final IntPredicate buzzPredicate = number -> number % 5 == 0;
    private final IntPredicate fizzBuzzPredicate = fizzPredicate.and(buzzPredicate);
    private final IntPredicate luckyPredicate = number -> String.valueOf(number).contains("3");


    public String play(Integer start, Integer end) {
        if (start > end) {
            String errorMessage = String.format(
                    "Invalid range of numbers provided : min %d is greater than max %d Please correct the input and try again",
                    start, end);
            throw new IllegalArgumentException(errorMessage);
        }
        final List<String> resultList = IntStream.rangeClosed(start, end).mapToObj(this::computeResult).collect(Collectors.toList());
        return String.join(DELIMITER_SPACE, resultList) + DELIMITER_SPACE + buildStatistics(resultList);
    }

    private String buildStatistics(List<String> resultList) {
        final Map<String, Long> statisticsMap = resultList.stream().map(s -> StringUtils.isNumeric(s) ? INTEGER : s).collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
        return STATISTICS_KEYS.stream().map(s -> s + COLON + DELIMITER_SPACE + statisticsMap.get(s))
                .collect(Collectors.joining(DELIMITER_SPACE)).trim();
    }

    private String computeResult(Integer number) {
        if (luckyPredicate.test(number)) {
            return LUCKY;
        } else if (fizzBuzzPredicate.test(number)) {
            return FIZZ_BUZZ;
        } else if (buzzPredicate.test(number)) {
            return BUZZ;
        } else if (fizzPredicate.test(number)) {
            return FIZZ;
        } else {
            return String.valueOf(number);
        }
    }

}
