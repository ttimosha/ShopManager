package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.dao.CityDao;
import ru.shop.model.City;

import java.util.List;

@Service
public class CityService {
    private CityDao cityDao;

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Transactional
    public List<City> listCities() { return this.cityDao.listCities(); }
}
