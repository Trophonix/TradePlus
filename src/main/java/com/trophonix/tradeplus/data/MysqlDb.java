package com.trophonix.tradeplus.data;

import java.sql.SQLException;

public class MysqlDb extends Db {

  public MysqlDb(String url) throws SQLException {
    super(Type.MYSQL, "jdbc:mysql://" + url);
  }

}
