# OpenTutorial

OpenTutorial is a tutorial plugin utilizing a format for making ordered collections of sequential actions with potential conditions for proceeding

OpenTutorial follows a sequential path and as such has a major limitation in that it cannot have branching actions.

A further rendition of OpenTutorial may fix this.

//TODO: Said format must be migrated to Conversation and the ticket bot
//TODO: multiplatform

//TODO: market it as both a quiz plugin, job interview plugin AND tutorial plugin - that's what it is!

For ConversationAPI i'd like to make it so we have a 

SingleNode (contents: Queue<Node>)
BranchingNode (Contents: List<Node> and Condition)

i'll have to think it through more but it will follow the opentutorial standard
I may release a huge opentutorial update once convapi is done

//TODO: command for /opentutorial location just outputs a conf-copyable x: 1 y: 1 z: 1 for use in location stages

obligatory EXCEPTIONS ARE NOT BAD. i need to explain this to users so they doin't think its a buggy shitfest when in reality
exceptions are how we signal incorrect behavior yet the average ignorant server owner doesn't understand this and uses
actually buggy shitcode that doesn't throw exceptions and hogs memory and cpu

//TODO separate serializer from consumer (ugh) because i did not realize how orrible this would go - just make a new branch later
//TODO separate tutorial logic back to the controller, tutorials need only be a clone of the template
//TODO make stageserializer serialize a stage wrapping collection like Stages, so we can control indexes.