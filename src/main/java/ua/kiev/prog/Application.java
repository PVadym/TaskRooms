package ua.kiev.prog;

import ua.kiev.prog.DAO.FlatDAO;
import ua.kiev.prog.DAO.FlatDaoJDBC;
import ua.kiev.prog.connection.ConnectionMySql;
import ua.kiev.prog.controller.MainController;
import ua.kiev.prog.service.FlatServiceImpl;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Вадим on 03.09.2017.
 */
public class Application {

    private ConnectionMySql connection = new ConnectionMySql();
    private FlatDAO dao = new FlatDaoJDBC(connection.getConnection());
    private FlatServiceImpl service = new FlatServiceImpl(dao);
    private MainController controller = new MainController(service);



    public  void start() {
        int choice;
        while (true) {
            printMenu();
            choice = readChoice();
            if (choice == 0) {
                connection.close();
                break;
            }
            controller.action(choice);
        }
    }

    public void printMenu() {
        System.out.println();
        System.out.println("MENU");
        System.out.println("1- add new flat");
        System.out.println("2- delete flat by ID");
        System.out.println("3- update flat by ID");
        System.out.println("4- view all flats");
        System.out.println("5- select flats by parameters");
        System.out.println("0- exit");
    }

    public int readChoice() {
        int choice;
        System.out.print("Input your choice ->  ");
        try {
            choice = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
        }
        return choice;
    }
}
