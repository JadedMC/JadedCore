package net.jadedmc.jadedcore.features.games;

/**
 * Represents the type of game a minigame is.
 */
public enum GameType {
    PERSISTENT("Persistent Game"),
    COMPETITIVE("Competitive"),
    NONE("");

    private final String name;
    GameType(String name) {
        this.name = name;
    }

    /**
     * Converts the GameType to a String.
     * Used in GUIs.
     * @return String version of the game type.
     */
    @Override
    public String toString() {
        return name;
    }
}