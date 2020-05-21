package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.dao.ConditionDao;
import ru.shop.model.Condition;

import java.util.List;

@Service
public class ConditionService {
    private ConditionDao conditionDao;

    public void setConditionDao(ConditionDao conditionDao) {
        this.conditionDao = conditionDao;
    }

    @Transactional
    public List<Condition> listConditions(){
        return this.conditionDao.listConditions();
    }
}
