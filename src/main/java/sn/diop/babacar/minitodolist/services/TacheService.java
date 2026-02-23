package sn.diop.babacar.minitodolist.services;

import sn.diop.babacar.minitodolist.model.TacheDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TacheService {

    TacheDTO createTache(TacheDTO tacheDTO);

    TacheDTO updateTache(TacheDTO tacheDTO);

    void deleteTache(Long id);

    TacheDTO getTache(Long id);

    Page<TacheDTO> getAllTaches(Map<String, String> searchParams, Pageable pageable);

    TacheDTO marquerTerminee(Long id);

}
