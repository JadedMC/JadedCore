package net.jadedmc.jadedcore.features.games;

import net.jadedmc.jadedcore.utils.xseries.XMaterial;

/**
 * Represents a game on the network.
 */
public enum Game {
    ELYTRAPVP("ElytraPvP", XMaterial.FEATHER, GameType.PERSISTENT, "Action-Packed pvp in the air using bows!", "elytrapvp"),
    TURFWARS("Turf Wars", XMaterial.BOW, GameType.COMPETITIVE, "Advance your team's territory by killing other players.", "turfwars"),
    CACTUS_RUSH("Cactus Rush", XMaterial.CACTUS, GameType.COMPETITIVE, "Team-Based Cactus Fighting Minigame.", "cactusrush"),
    HOUSING("Housing", XMaterial.DARK_OAK_DOOR, GameType.PERSISTENT, "Create in your own mini-world, or visit someone else's!", "housing"),
    LOBBY("Main Lobby", XMaterial.CRAFTING_TABLE, GameType.NONE, "", "lobby"),
    GENERAL("General", XMaterial.BOOK, GameType.NONE, "", ""),
    DUELS("Duels", XMaterial.IRON_SWORD, GameType.COMPETITIVE, "", ""),
    MODERN_DUELS("1.20 Duels", XMaterial.DIAMOND_SWORD, GameType.COMPETITIVE, "", "modernduels"),
    LEGACY_DUELS("1.8 Duels", XMaterial.GOLDEN_SWORD, GameType.COMPETITIVE, "", "legacyduels");

    private final String name;
    private final XMaterial iconMaterial;
    private final GameType type;
    private final String description;
    private final String server;

    Game(String name, XMaterial iconMaterial, GameType type, String description, String server) {
        this.name = name;
        this.iconMaterial = iconMaterial;
        this.type = type;
        this.description = description;
        this.server = server;
    }

    /**
     * Get the description of the game.
     * @return The description of the game.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the material the game icon is made of.
     * @return Game icon material.
     */
    public XMaterial getIconMaterial() {
        return iconMaterial;
    }

    /**
     * Get the name of the game.
     * @return Game name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the server the game is hosted on.
     * @return Server to send players to.
     */
    public String getServer() {
        return server;
    }

    /**
     * Gets the GameType of the game.
     * @return GameType.
     */
    public GameType getType() {
        return type;
    }
}
