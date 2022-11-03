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
public class SequentialFlowJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job sequentialJob(
            Step sequentialFailJob,
            Step sequentialSuccessJob
    ) {
        return jobBuilderFactory.get("sequentialJob")
                .start(sequentialFailJob)
                .next(sequentialSuccessJob)
                .build();
    }

    @Bean
    @JobScope
    public Step sequentialFailJob() {
        return stepBuilderFactory.get("sequentialFailJob")
                // tasklet = Reader & Processor & Writer
                .tasklet((contribution, chunkContext) -> {
                    log.info("sequentialFailJob");
                    // set step fail
                    throw new Exception();

                })
                .build();
    }

    @Bean
    @JobScope
    public Step sequentialSuccessJob() {
        return stepBuilderFactory.get("sequentialSuccessJob")
                // tasklet = Reader & Processor & Writer
                .tasklet((contribution, chunkContext) -> {
                    log.info("sequentialSuccessJob");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
