package net.jadedmc.jadedcore;

import java.sql.Connection;

public class JadedAPI {
    private static JadedCore plugin = null;

    public JadedAPI(JadedCore pl) {
        plugin = pl;
    }

    public static Connection getDatabase() {
        return plugin.mySQL().getConnection();
    }

    public static JadedCore getPlugin() {
        return plugin;
    }
}