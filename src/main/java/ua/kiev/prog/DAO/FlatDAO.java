package ua.kiev.prog.DAO;

import ua.kiev.prog.entity.Flat;

import java.util.List;

/**
 * Created by Вадим on 02.09.2017.
 */
public interface FlatDAO {

    void add(Flat flat);
    void deleteById(int id);
    void updatePrice(int id, int price);
    List<Flat> findAll();
    List<Flat> findByParams(String district, String address, double square, int rooms, int price);


}
