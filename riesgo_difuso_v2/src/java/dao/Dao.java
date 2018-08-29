package dao;

import parainfo.sql.ConectaDb;

public class Dao {

    protected final ConectaDb db;
    protected final StringBuilder sql;
    protected String message;

    public Dao() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

}
