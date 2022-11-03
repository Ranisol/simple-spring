package simple.spring.batch.job.flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ConditionalFlowJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job conditionalFlowJob(
            Step controlFlowStepA,
            Step controlFlowStepB
    ) {
        return jobBuilderFactory.get("conditionalFlowJob")
                .start(controlFlowStepA)
                .on(ExitStatus.FAILED.getExitCode()) // FAILED 일 경우
                .to(controlFlowStepB) // sequentialStep3으로 이동
                .end()
                .build();
    }

    @Bean
    @JobScope
    public Step controlFlowStepA() {
        return stepBuilderFactory.get("controlFlowStepA")
                // tasklet = Reader & Processor & Writer
                .tasklet((contribution, chunkContext) -> {
                    log.info("controlFlowStepA");
                    // set step fail
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @JobScope
    public Step controlFlowStepB() {
        return stepBuilderFactory.get("controlFlowStepB")
                // tasklet = Reader & Processor & Writer
                .tasklet((contribution, chunkContext) -> {
                    log.info("controlFlowStepB");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
