package ua.kiev.prog.service;

import ua.kiev.prog.entity.Flat;

import java.util.List;

/**
 * Created by Вадим on 02.09.2017.
 */
public interface FlatService {

    void addFlat(Flat flat);
    void deleteFlatById(int id);
    void updatePrice(int id, int price);
    List<Flat> findAllFlats();
    List<Flat> findFlatsByParams(String district, String address, double square, int rooms, int price);
}
