package hooks;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;

public class CucumberEventListener implements EventListener {
    public static ThreadLocal<String> scenarioName = new ThreadLocal<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, event -> {
            scenarioName.set(event.getTestCase().getName());
        });
    }
}
