package com.example.wagonmanager.service;

import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.repository.WagonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WagonService {

    @Autowired
    private WagonRepository wagonRepository;

    @Autowired
    public WagonService(WagonRepository wagonRepository) {
        this.wagonRepository = wagonRepository;
    }

    public List<Wagon> getAllWagons() {
        return wagonRepository.findAll();
    }

    public Optional<Wagon> getWagonByUuid(String uuid) {
        return wagonRepository.findByUuid(uuid);
    }
    @Transactional
    public Wagon saveWagon(Wagon wagon) {
        return wagonRepository.save(wagon);
    }

    @Transactional
    public int batchSaveOrUpdate(List<Wagon> wagons, String userName) {
        int affected = 0;
        for (Wagon wagon : wagons) {
            affected += wagonRepository.upsertWagon(
                    wagon.getUuid(),
                    wagon.getNumber(),
                    wagon.getType(),
                    userName,
                    wagon.getUpdatedAt(),
                    wagon.getCreatedAt()
            );
        }
        return affected;
    }

    @Transactional
    public void deleteWagonByUuid(Long wagonUuid) {
        wagonRepository.deleteById(wagonUuid);
    }
}
