package com.example.wagonmanager.service;

import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.repository.VagonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagonService {

    private final VagonRepository vagonRepository;

    @Autowired
    public VagonService(VagonRepository vagonRepository) {
        this.vagonRepository = vagonRepository;
    }

    public List<Wagon> getAllVagons() {
        return vagonRepository.findAll();
    }

    public Optional<Wagon> getVagonByUuid(String uuid) {
        return vagonRepository.findByUuid(uuid);
    }

    public Wagon saveVagon(Wagon wagon) {
        return vagonRepository.save(wagon);
    }

    public void deleteVagon(Long id) {
        vagonRepository.deleteById(id);
    }
}
