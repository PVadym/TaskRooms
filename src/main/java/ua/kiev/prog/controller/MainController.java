package ua.kiev.prog.controller;

import ua.kiev.prog.entity.Flat;
import ua.kiev.prog.service.FlatService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Вадим on 02.09.2017.
 */
public class MainController {

    private FlatService service;

    public MainController(FlatService service) {
        this.service = service;
    }


    public void action(int choice){
        try {
            switch (choice) {
                case 1:
                    addNew();
                    break;
                case 2:
                    deleteFlat();
                    break;
                case 3:
                    updateFlat();
                    break;
                case 4:
                    showAll();
                    break;
                case 5:
                    showFlatByParams();
                    break;
            }
        } catch (InputMismatchException e){
            System.out.println("Error in input,try again....");
        }

    }

    private void showFlatByParams() {
        System.out.print("Input part of district: ");
        String district = new Scanner(System.in).nextLine();
        System.out.println("Input part of address: ");
        String address = new Scanner(System.in).nextLine();
        System.out.println("Input the required square (for example 25,0): ");
        double square = new Scanner(System.in).nextDouble();
        System.out.println("Input the required rooms: ");
        int rooms = new Scanner(System.in).nextInt();
        System.out.println("Input price from: ");
        int price = new Scanner(System.in).nextInt();
        System.out.println("Selected flats by entered parameters:");
        service.findFlatsByParams(district,address,square,rooms,price).forEach(System.out::println);
    }

    private void updateFlat() {
        System.out.print("Input id of Flat to update: ");
        int id = new Scanner(System.in).nextInt();
        System.out.print("Input new price of flat: ");
        int price = new Scanner(System.in).nextInt();
        service.updatePrice(id,price);
        System.out.println("Flat updated!");
    }

    private void addNew() {
        System.out.print("Input district of flat: ");
        String district = new Scanner(System.in).nextLine();
        System.out.println("Input address: ");
        String address = new Scanner(System.in).nextLine();
        System.out.println("Input square (for example 25,0): ");
        double square = new Scanner(System.in).nextDouble();
        System.out.println("Input quantity rooms: ");
        int rooms = new Scanner(System.in).nextInt();
        System.out.println("Input price: ");
        int price = new Scanner(System.in).nextInt();
        Flat flat = new Flat(district,address,square,rooms,price);
        service.addFlat(flat);
        System.out.println("Flat saved!");
    }

    private void deleteFlat() {
        System.out.print("Delete by id. Input id: ");
        int id = new Scanner(System.in).nextInt();
        service.deleteFlatById(id);
    }

    private void showAll() {
        System.out.println("All flats:");
        service.findAllFlats().forEach(System.out::println);
    }

}
