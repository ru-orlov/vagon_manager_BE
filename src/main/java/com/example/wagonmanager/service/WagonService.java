package com.example.wagonmanager.service;

import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.repository.WagonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WagonService {

    private final WagonRepository wagonRepository;

    @Autowired
    public WagonService(WagonRepository wagonRepository) {
        this.wagonRepository = wagonRepository;
    }

    public List<Wagon> getAllVagons() {
        return wagonRepository.findAll();
    }

    public Optional<Wagon> getVagonByUuid(String uuid) {
        return wagonRepository.findByUuid(uuid);
    }

    public Wagon saveVagon(Wagon wagon) {
        return wagonRepository.save(wagon);
    }

    public void deleteVagon(Long id) {
        wagonRepository.deleteById(id);
    }
}
