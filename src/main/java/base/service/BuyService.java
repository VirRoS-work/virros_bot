package base.service;

import base.dao.GenericDAO;
import base.model.Buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyService implements GenericService<Buy, Integer> {

    @Autowired
    private GenericDAO dao;

    @Override
    @Transactional
    public void create(Buy object) {
        dao.create(object);
    }

    @Override
    @Transactional
    public void update(Buy object) {
        dao.update(object);
    }

    @Override
    @Transactional
    public void delete(Integer key) {
        dao.delete(key);
    }

    @Override
    @Transactional
    public Buy getObjectById(Integer key) {
        return (Buy) dao.getObjectById(key);
    }

    @Override
    @Transactional
    public List<Buy> getAllObjects() {
        return (List<Buy>) dao.getAllObjects();
    }
}
