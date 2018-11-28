package com.trophonix.tradeplus.data;

import org.bukkit.entity.Player;

import java.sql.*;

public class Db {

  private Type type;
  private Connection connection;

  public Db(Type type, String url) throws SQLException {
    this.type = type;
    connection = DriverManager.getConnection(url);

    connection.createStatement().execute("CREATE TABLE IF NOT EXISTS playerData (" +
            "`uuid` VARCHAR(36) UNIQUE NOT NULL," +
            "`allowsTrades` BIT DEFAULT 0," +
            "PRIMARY KEY (`uuid`));");

  }

  public void init(Player player) throws SQLException {
    PreparedStatement ps = connection.prepareStatement("INSERT INTO playerData (uuid, allowsTrades) VALUES (" +
            "'" + player.getUniqueId().toString() + "', " +
            "1" +
            ");");
    ps.execute();
    ps.close();
  }

  public boolean allowsTrades(Player player) {
    PreparedStatement ps = null;
    ResultSet rs;
    try {
      ps = connection.prepareStatement("SELECT * FROM playerData WHERE uuid = '" + player.getUniqueId().toString() + "';");
      ps.closeOnCompletion();
      rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getBoolean("allowsTrades");
      } else return true;
    } catch (SQLException ignored) {
    } finally {
      try {
        if (ps != null && !ps.isClosed()) ps.close();
      } catch (SQLException e) {
      }
    }
    return true;
  }

  public enum Type {

    SQLITE("lite", "sqllite"),
    MYSQL("my");

    private String[] alts;

    Type(String... alts) {
      this.alts = alts;
    }

    public static Type getType(String str) {
      for (Type type : values()) {
        if (type.name().equalsIgnoreCase(str)) {
          return type;
        }
        for (String alt : type.alts) {
          if (alt.equalsIgnoreCase(str)) {
            return type;
          }
        }
      }
      return null;
    }

  }

}
