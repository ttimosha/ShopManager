package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.dao.SizeDao;
import ru.shop.model.Size;

import java.util.List;

@Service
public class SizeService {
    private SizeDao sizeDao;

    public void setSizeDao(SizeDao sizeDao) {
        this.sizeDao = sizeDao;
    }

    @Transactional
    public List<Size> listSizes(){return this.sizeDao.listSizes();}
}
