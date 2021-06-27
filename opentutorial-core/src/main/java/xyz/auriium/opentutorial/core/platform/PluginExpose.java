package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.insertion.InsertionRegistry;

/**
 * Base (Not reloadable) construct exposer
 */
public interface PluginExpose {

    HookRegistry getHookRegistry();
    InsertionRegistry getInsertionRegistry();

}
