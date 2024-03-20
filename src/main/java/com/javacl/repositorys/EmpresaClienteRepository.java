package com.javacl.repositorys;

import java.sql.Connection;

import com.javacl.model.connection.DatabaseConnection;

public class EmpresaClienteRepository {
    private Connection connection = DatabaseConnection.getConnection();

    
}
