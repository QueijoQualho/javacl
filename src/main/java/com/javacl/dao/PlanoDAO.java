package com.javacl.dao;

import java.sql.Connection;
import com.javacl.model.Plano;


public class PlanoDAO {
    private Connection connection;

    public PlanoDAO(Connection connection) {
        this.connection = connection;
    }

    /* Get ALL */
    public Plano getAllPlano() {

        return null;
    }
}
