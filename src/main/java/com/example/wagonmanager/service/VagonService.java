package com.example.wagonmanager.service;

import com.example.wagonmanager.model.Vagon;
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

    public List<Vagon> getAllVagons() {
        return vagonRepository.findAll();
    }

    public Optional<Vagon> getVagonByUuid(String uuid) {
        return vagonRepository.findByUuid(uuid);
    }

    public Vagon saveVagon(Vagon vagon) {
        return vagonRepository.save(vagon);
    }

    public void deleteVagon(Long id) {
        vagonRepository.deleteById(id);
    }
}
