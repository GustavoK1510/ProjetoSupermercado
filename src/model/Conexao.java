package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/supermercado";
	private static final String USER = "root";
	private static final String PASSWORD = "projetosupermercado";
	
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

//create database if not exists supermercado;
//use supermercado;
//
//create table usuario (
//id int auto_increment primary key,
//nome varchar(100) not null,
//cpf char(11) not null unique,
//admin boolean not null default false
//);
//
//create table produto (
//id int auto_increment primary key,
//nome varchar(100) not null,
//quantidade int not null,
//valor double not null,
//descricao text
//);
//
//#caso o professor queira verificar as tabelas
//select * from produto; 
//select * from usuario;
