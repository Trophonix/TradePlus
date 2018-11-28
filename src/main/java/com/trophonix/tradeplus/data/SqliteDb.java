package com.trophonix.tradeplus.data;

import java.io.File;
import java.sql.SQLException;

public class SqliteDb extends Db {

  public SqliteDb(File dataFolder, String fileName) throws SQLException {
    super(Type.SQLITE, "jdbc:sqlite:" + new File(dataFolder, fileName));
  }

}
