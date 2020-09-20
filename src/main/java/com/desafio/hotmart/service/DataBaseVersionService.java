package com.desafio.hotmart.service;

import com.desafio.hotmart.entity.DataBaseVersion;
import com.desafio.hotmart.repository.DataBaseVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class DataBaseVersionService extends BaseService<DataBaseVersion> {

    @Autowired
    private DataBaseVersionRepo dataBaseVersionRepo;

    @Override
    protected JpaRepository<DataBaseVersion, Long> getEntityRepository() {
        return dataBaseVersionRepo;
    }

    @Override
    public void validateBeforeSave(DataBaseVersion entity) {

    }

    public void initDataBase() {
        DataBaseVersion db = new DataBaseVersion();
        db.setVersiondb_dev(1);
        db.setVersiondb_prod(0);
        dataBaseVersionRepo.save(db);
    }

    public DataBaseVersion getCurrentDataVersion() {
        DataBaseVersion first = dataBaseVersionRepo.findAll().stream().findFirst().orElse(null);
        return first;
    }
}
