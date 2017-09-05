package ua.kiev.prog;

import ua.kiev.prog.DAO.FlatDaoJDBC;
import ua.kiev.prog.connection.ConnectionMySql;
import ua.kiev.prog.controller.MainController;
import ua.kiev.prog.service.FlatServiceImpl;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Вадим on 02.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        new Application().start();
    }
}
