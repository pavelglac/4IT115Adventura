/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;



/*******************************************************************************
 * @author  Pavel Glac
 * @version 2017-Winter
 */


public class State
{

    /**
     *
     */
    public static Collection<AAction> actions = AAction.getAllActions();

    /**
     *
     */
    public static Collection<AAction> notUsedActions;

    /***************************************************************************
     * This method initializes the state of the game.
     */
    static void initialize()
    {        
        notUsedActions = new ArrayList<>();
        for (AAction act : actions) {
            notUsedActions.add(act);
        }
    }

    /***************************************************************************
     * Private constructor preventing creation of instances
     */
    private State()
    {
    }

}
