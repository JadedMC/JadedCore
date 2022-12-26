package net.jadedmc.jadedcore.features.games;

import net.jadedmc.jadedcore.utils.xseries.XMaterial;

/**
 * Represents a game on the network.
 */
public enum Game {
    ELYTRAPVP("ElytraPvP", XMaterial.ELYTRA, GameType.PERSISTENT, "Action-Packed pvp in the air using bows!", "elytrapvp"),
    CACTUS_RUSH("Cactus Rush", XMaterial.CACTUS, GameType.COMPETITIVE, "Team-Based Cactus Fighting Minigame", "cactusrush");

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
