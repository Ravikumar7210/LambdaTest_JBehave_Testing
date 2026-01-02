package com.example.jbehave.lambdatest.runner;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

@SpringBootTest
public class JBehaveRunnerTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void runStories() {
        Configuration configuration = new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(new StoryReporterBuilder()
                .withDefaultFormats()
                .withFormats(Format.CONSOLE, Format.TXT, Format.HTML)
                .withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName())
            );

        InjectableStepsFactory stepsFactory = new SpringStepsFactory(configuration, context);

        StoryFinder finder = new StoryFinder();
        // ✅ Pick up all .story files under /stories/
        List<String> stories = finder.findPaths(
            codeLocationFromClass(this.getClass()),
            "stories/*.story",
            ""
        );

        Embedder embedder = new Embedder();
        embedder.useConfiguration(configuration);
        embedder.useStepsFactory(stepsFactory);
        embedder.runStoriesAsPaths(stories);

        // ✅ Quit driver after all stories
        WebDriver driver = context.getBean(WebDriver.class);
        if (driver != null) {
            System.out.println("🛑 Closing WebDriver session at end of run");
            driver.quit();
        }
    }
}
