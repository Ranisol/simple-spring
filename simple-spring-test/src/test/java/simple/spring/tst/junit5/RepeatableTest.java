package simple.spring.tst.junit5;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RepeatableTest {
    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatedTest(
            RepetitionInfo repetitionInfo
    ) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"ra", "ni", "sol"})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void convertStudyOneArg(
        @ConvertWith(StudyConverterOneArg.class) Study study
    ) {
        System.out.println(study.getLimit());
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void argumentAccessor(
            ArgumentsAccessor argumentAccessor
    ) {
        Study study = new Study(argumentAccessor.getInteger(0), argumentAccessor.getString(1));
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void convertStudyMultiArg(
            @AggregateWith(StudyConverterMultiArg.class) Study study
    ) {
        System.out.println(study.getLimit() + " " + study.getName());
    }


    static class StudyConverterOneArg extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    static class StudyConverterMultiArg implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    @Getter
    @Setter
    @ToString
    static class Study {
        private StudyStatus studyStatus;
        private int limit;
        private String name;
        public Study(int limit) {
            if(limit < 0) {
                throw new IllegalArgumentException("limit은 0보다 커야 한다.");
            }
            this.limit = limit;
        }

        public Study(int limit, String name) {
            this.limit = limit;
            this.name = name;
        }


    }

    enum StudyStatus {
        A("A");
        final String name;
        StudyStatus(String name) {
            this.name = name;
        }
    }
}
