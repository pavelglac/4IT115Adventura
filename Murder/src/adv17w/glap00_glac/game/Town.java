/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import static adv17w.glap00_glac.game.Component.NOT_MOVABLE;
import static adv17w.glap00_glac.game.Component.STANDARD;
import adv17w.glap00_glac.util.IObserverZmenyProstoru;
import adv17w_fw.game_txt.INamed;
import java.util.Collection;
import adv17w_fw.game_txt.IWorld;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

/**
 * *****************************************************************************
 * An instance of the {@code EmptyWorld} class represents the game world. It
 * should be defined as a singleton. It is responsible for arrangement of
 * individual places and keeps information, in which place the player is just
 * situated. The mutual arrangement may change during the game, the places can
 * gain and/or lose their neighbors.
 * <p>
 * In this game the world is ...
 *
 * @author Pavel Glac
 * @version 2017-Winter
 */
public class Town
        implements IWorld {
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /**
     * The only instance (singleton) of this world.
     */
    private static final Town SINGLETON = new Town();

//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
    /**
     * *************************************************************************
     * The factory method returning the only existing instance of the game.
     *
     * @return The only instance of the given game
     */
    static Town getInstance() {
        return SINGLETON;
    }

//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================
//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
    /**
     * The collection of all places (mostly rooms) in this world.
     */
    private final Collection<Place> rooms;

    /**
     * The immutable collection of all places (mostly rooms) in this world that
     * continuously maps the {@link #rooms} collection content.
     */
    private final Collection<Place> exportedRooms;

    /**
     * Room in which the game begins.
     */
    private final Place startingRoom;

//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
    /**
     * The place, in which the player is just situated
     */
    private Place currentArea;

    private List<IObserverZmenyProstoru> seznamPozorovatelu;

//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================
    /**
     * *************************************************************************
     * The private constructor creating the only instance of the place world.
     * Within this manager definition it creates all game places.
     */
    private Town() {
        rooms = new ArrayList<>();
        exportedRooms = Collections.unmodifiableCollection(rooms);
        seznamPozorovatelu = new ArrayList<>();

        startingRoom = new Place("STANICE",
                250.00, 280.00,
                new String[]{"ULICE"},
                NOT_MOVABLE + "KOLEGA",
                STANDARD + "ODZNAK"
        );

        rooms.add(startingRoom);

        rooms.add(new Place("ULICE",
                285.00, 215.00,
                new String[]{
                    "STANICE", "MÍSTO_ČINU", "ŠKOLKA", "RADNICE", "HOSPODA"},
                STANDARD + "ODPADKY",
                STANDARD + "DROBÁKY"
        ));

        rooms.add(new Place("MÍSTO_ČINU",
                110.00, 270.00,
                new String[]{"ULICE"},
                NOT_MOVABLE + "MRTVOLA",
                STANDARD + "NŮŽ",
                STANDARD + "KAMERA"
        ));

        rooms.add(new Place("ŠKOLKA",
                395.00, 210.00,
                new String[]{"ULICE"},
                NOT_MOVABLE + "UČITELKA",
                NOT_MOVABLE + "DÍTĚ",
                STANDARD + "HRAČKA"
        ));

        rooms.add(new Place("RADNICE",
                410.00, 110.00,
                new String[]{"ULICE"},
                STANDARD + "PAPÍR"
        ));

        rooms.add(new Place("HOSPODA",
                250.00, 175.00,
                new String[]{"ULICE"},
                NOT_MOVABLE + "OPILEC",
                STANDARD + "SEKERA",
                NOT_MOVABLE + "HOSTINSKÝ"
        ));

    }

//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
    /**
     * *************************************************************************
     * Returns the place (room) with the given name wrapped in the
     * {@link Optional}.
     *
     * @param name Name of the required place
     * @return The wrapped place with the given name
     */
    public Optional<Place> getOPlace(String name) {
        Optional<Place> result = INamed.getO(name, rooms);
        return result;
    }

    /**
     * *************************************************************************
     * Returns the collection of all places of the game.
     *
     * @return Collection of all places performing in the game
     */
    @Override
    public Collection<Place> getAllPlaces() {
        return exportedRooms;
    }

    /**
     * *************************************************************************
     * Returns the current place, i.e. to the place in which the player is just
     * situated.
     *
     * @return The place in which the player is just situated
     */
    @Override
    public Place getCurrentPlace() {
        return currentArea;
    }

    /**
     * *************************************************************************
     * Sets the given place as the current one, i.e. the place, in which the
     * player is just situated.
     *
     * @param destinationRoom The set place
     */
    public void setCurrentArea(Place destinationRoom) {
        currentArea = destinationRoom;
        upozorniPozorovatele();

    }

    @Override
    public void zaregistrujPozorovatele(IObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    @Override
    public void odregistrujPozorovatele(IObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    @Override
    public void upozorniPozorovatele() {
        for (IObserverZmenyProstoru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
    }

//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
    /**
     * *************************************************************************
     * The method initializing the game world. Firstly it initializes all places
     * and then it sets the initial current place.
     */
    public void initialize() {
        rooms.forEach(Place::initialize);
        currentArea = startingRoom;
    }
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
