package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.stage.age.AgeStageInsertion;
import xyz.auriium.opentutorial.core.stage.chat.ChatStageInsertion;
import xyz.auriium.opentutorial.core.stage.clickblock.ClickBlockInsertion;
import xyz.auriium.opentutorial.core.stage.command.CommandStageInsertion;
import xyz.auriium.opentutorial.core.stage.delay.DelayStageInsertion;
import xyz.auriium.opentutorial.core.stage.invisible.InvisibleStageInsertion;
import xyz.auriium.opentutorial.core.stage.plainkeyword.PlainKeywordInsertion;
import xyz.auriium.opentutorial.core.stage.playsound.SoundStageInsertion;
import xyz.auriium.opentutorial.core.stage.teleport.TeleportStageInsertion;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CommonConsumerRegistry implements ConsumerRegistry{

    private final Set<ConsumerInsertion> insertionSet = new HashSet<>();

    @Override
    public Collection<ConsumerInsertion> getInsertions() {
        return Set.copyOf(insertionSet);
    }

    @Override
    public ConsumerRegistry addInsertion(ConsumerInsertion insertion) {
        insertionSet.add(insertion);

        return this;
    }

    public static ConsumerRegistry defaults() {
        return new CommonConsumerRegistry()
                .addInsertion(AgeStageInsertion.INIT)
                .addInsertion(ChatStageInsertion.INIT)
                .addInsertion(ClickBlockInsertion.INIT)
                .addInsertion(CommandStageInsertion.INIT)
                .addInsertion(DelayStageInsertion.INIT)
                .addInsertion(InvisibleStageInsertion.INIT)
                .addInsertion(PlainKeywordInsertion.INIT)
                .addInsertion(SoundStageInsertion.INIT)
                .addInsertion(TeleportStageInsertion.INIT);
    }
}
