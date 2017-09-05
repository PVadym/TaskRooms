package ua.kiev.prog.service;

import ua.kiev.prog.DAO.FlatDAO;
import ua.kiev.prog.entity.Flat;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Вадим on 02.09.2017.
 */
public class FlatServiceImpl implements FlatService {

    private FlatDAO dao;

    public FlatServiceImpl(FlatDAO dao) {
        this.dao = dao;
    }

    @Override
    public void addFlat(Flat flat) {
        dao.add(flat);

    }

    @Override
    public void deleteFlatById(int id) {
        dao.deleteById(id);
    }

    @Override
    public void updatePrice(int id, int price) {
        dao.updatePrice(id, price);
    }

    @Override
    public List<Flat> findAllFlats() {
        return dao.findAll();
    }

    @Override
    public List<Flat> findFlatsByParams(String district, String address, double square, int rooms, int price) {
        if (district==null){
            district = "";
        }
        if (address == null){
            address = "";
        }
        return dao.findByParams(district, address, square, rooms, price);
    }
}
