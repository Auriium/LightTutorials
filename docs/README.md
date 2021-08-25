## OpenTutorial

> The best open source tutorial plugin for Spigot!

## What is it?

A plugin that offers lists of "stages" to related stage handlers. With collections of these stages listening for user
interaction, plugin users can create anything from simple linear tutorials to quizzes, interviews, and even simple 
quests!

## Why this vs other tutorial plugins?

OpenTutorial is lightweight. OpenTutorial is well designed. OpenTutorial understands platform design and has been
created in such a way so that it has minimal performance impact and minimal plugin conflict, is robust enough to
sustain version changes, doesn't rely on NMS hackery, shitty blocking I/O calls, or other such garbage.

OpenTutorial is also **free and open source**, and will **always be so.**

## The Wiki

**https://auriium.github.io/OpenTutorial**

## How do I install this as a server owner?

OpenTutorial is designed to be platform-agnostic. That being said, it is still limited to Minecraft.
Find your server version in the list of modules in the source code (For example, spigot users would use opentutorial-spigot)
or search the plugin on the corresponding marketplace for said plugin. (Ore for Sponge, Spigot for, well, Spigot)

## How do I configure this as a server owner?

Please read the Customization section! //TODO add this and make description better

## How do I use this as a developer?

OpenTutorial has a complex developer API for registering stages, stage systems, and other integral parts of the design
without compromising design security. Therefore, it exposes access to its' internals via an API module (opentutorial-api).
The code itself contains Java documentation but to better understand it please read the Development section //TODO

