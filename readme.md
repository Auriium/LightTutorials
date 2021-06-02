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
//TODO: User Interfacing system: if a "message" of a stage is left blank a UIRegistry can provide a default message, otherwise the stage can provide the message.
//TODO: configurable messages for all messages