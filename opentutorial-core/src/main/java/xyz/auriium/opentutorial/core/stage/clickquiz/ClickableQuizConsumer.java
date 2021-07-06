package xyz.auriium.opentutorial.core.stage.clickquiz;

import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.event.chat.ClickableEvent;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.stage.AbstractDelayConsumer;

public class ClickableQuizConsumer extends AbstractDelayConsumer<ClickableQuizStage, ClickableEvent> {

    private final TeachableRegistry teachableRegistry;
    private final MessageConfig config;

    public ClickableQuizConsumer(Scheduler scheduler, TeachableRegistry registry, MessageConfig config) {
        super(scheduler, registry, config);

        this.teachableRegistry = registry;
        this.config = config;
    }

    @Override
    public Class<ClickableEvent> eventClass() {
        return ClickableEvent.class;
    }

    @Override
    public void consume(ClickableQuizStage stage, ClickableEvent event, Tutorial tutorial) {
        if (event.getClicked() == stage.getCorrectOption() - 1) {
            tutorial.fireNext();
        } else {
            teachableRegistry.getAudienceByUUID(tutorial.getIdentifier()).ifPresent(teachable -> {
                config.notAnswerMessage().send(teachable);

                if (stage.isCancelOnFail()) {
                    tutorial.fireCancel();
                }

                stage.getCommandOnFail().ifPresent(cmd -> {
                    teachable.runConsole(cmd.replaceAll("%PLAYER%", teachable.getName()));
                });

            });

        }
    }

    @Override
    public void started(ClickableQuizStage options, Tutorial continuable) {
        super.started(options, continuable);

        teachableRegistry.getAudienceByUUID(continuable.getIdentifier()).ifPresent(teachable -> {

            for (int i = 0; i < options.getOptions().size() - 1; i++) {
                String str = options.getOptions().get(i);

                String ooga = config.optionMessage().parse(i + 1,str);

                System.out.println(ooga);

                teachable.sendClickable(ooga, "opentutorial option " + i, "&7Click to choose option " + (i + 1));
            }

        });
    }

    @Override
    public Class<ClickableQuizStage> stageClass() {
        return ClickableQuizStage.class;
    }
}
