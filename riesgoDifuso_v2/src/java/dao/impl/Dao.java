/*
 Clase Dao
 */
package dao.impl;

import parainfo.sql.ConectaDb;

/**
 *
 * @author DANIEL
 */
public class Dao {

    protected final ConectaDb db;
    protected final StringBuilder sql;
    protected String message;

    public Dao() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

}
