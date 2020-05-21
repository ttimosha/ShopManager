package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.dao.TypeDao;
import ru.shop.model.Type;

import java.util.List;

@Service
public class TypeService {
    private TypeDao typeDao;

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Transactional
    public List<Type> listTypes() { return this.typeDao.listTypes(); }
}
