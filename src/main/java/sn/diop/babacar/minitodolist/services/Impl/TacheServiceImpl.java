package sn.diop.babacar.minitodolist.services.Impl;

import com.querydsl.core.BooleanBuilder;
import sn.diop.babacar.minitodolist.entity.QTacheEntity;
import sn.diop.babacar.minitodolist.mapper.TacheMapper;
import sn.diop.babacar.minitodolist.model.TacheDTO;
import sn.diop.babacar.minitodolist.repository.TacheRepository;
import sn.diop.babacar.minitodolist.services.TacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
import sn.diop.babacar.minitodolist.entity.enums.TacheStatusType;

@Service
@Transactional
@RequiredArgsConstructor
public class TacheServiceImpl implements TacheService {
    private final TacheRepository tacheRepository;
    private final TacheMapper tacheMapper;

    @Override
    public TacheDTO createTache(TacheDTO tacheDTO) {
        var entity = tacheMapper.asEntity(tacheDTO);
        var entitySave = tacheRepository.save(entity);
        return tacheMapper.asDto(entitySave);
    }

    @Override
    public TacheDTO updateTache(TacheDTO tacheDTO) {
        var entityUpdate = tacheMapper.asEntity(tacheDTO);
        var updatedEntity = tacheRepository.save(entityUpdate);
        return tacheMapper.asDto(updatedEntity);
    }

    @Override
    public void deleteTache(Long id) {
        if (!tacheRepository.existsById(id)) {
            throw new RuntimeException("Tache not found");
        }
        tacheRepository.deleteById(id);
    }

    @Override
    public TacheDTO getTache(Long id) {
        var entity = tacheRepository.findById(id);
        return tacheMapper.asDto(entity.get());
    }

    @Override
    public Page<TacheDTO> getAllTaches(Map<String, String> searchParams, Pageable pageable) {
        var booleanBuilder = new BooleanBuilder();
        buildSearch(searchParams, booleanBuilder);
        return tacheRepository.findAll(booleanBuilder, pageable)
                .map(tacheMapper::asDto);
    }

    /**
     * Construit le prédicat de recherche basé sur les paramètres fournis.
     *
     * @param searchParams   la map des filtres de recherche
     * @param booleanBuilder le BooleanBuilder Querydsl à peupler
     */
    private void buildSearch(Map<String, String> searchParams, BooleanBuilder booleanBuilder) {
        if (Objects.nonNull(searchParams)) {
            var qEntity = QTacheEntity.tacheEntity;
            if (searchParams.containsKey("titre"))
                booleanBuilder.and(qEntity.titre.containsIgnoreCase(searchParams.get("titre")));
            if (searchParams.containsKey("description"))
                booleanBuilder.and(qEntity.description.containsIgnoreCase(searchParams.get("description")));
            String statut = searchParams.get("statut");
            if (statut != null && !statut.isEmpty()) {
                booleanBuilder.and(qEntity.statut.stringValue().lower().containsIgnoreCase(statut.toLowerCase()));
            }
        }
    }

    @Override
    public TacheDTO marquerTerminee(Long id) {
        var entity = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tache not found"));
        entity.setStatut(TacheStatusType.TERMINER);
        var updatedEntity = tacheRepository.save(entity);
        return tacheMapper.asDto(updatedEntity);
    }

}
