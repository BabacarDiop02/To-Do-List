package sn.diop.babacar.minitodolist.services;

import sn.diop.babacar.minitodolist.model.TacheDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TacheService {

    /**
     * Crée une nouvelle tâche.
     *
     * @param tacheDTO les données de la tâche à créer
     * @return la tâche créée sous forme de DTO
     */
    TacheDTO createTache(TacheDTO tacheDTO);

    /**
     * Met à jour une tâche existante.
     *
     * @param tacheDTO les données de la tâche à mettre à jour
     * @return la tâche mise à jour sous forme de DTO
     */
    TacheDTO updateTache(TacheDTO tacheDTO);

    /**
     * Supprime une tâche par son identifiant.
     *
     * @param id l'identifiant de la tâche à supprimer
     */
    void deleteTache(Long id);

    /**
     * Récupère une tâche par son identifiant.
     *
     * @param id l'identifiant de la tâche à récupérer
     * @return la tâche sous forme de DTO
     */
    TacheDTO getTache(Long id);

    /**
     * Récupère une liste paginée de tâches en fonction de paramètres de recherche.
     *
     * @param searchParams une map de filtres de recherche (ex: titre, description,
     *                     statut)
     * @param pageable     informations de pagination
     * @return une page de DTO de tâches
     */
    Page<TacheDTO> getAllTaches(Map<String, String> searchParams, Pageable pageable);

    /**
     * Marque une tâche comme terminée.
     *
     * @param id l'identifiant de la tâche à marquer comme terminée
     * @return la tâche mise à jour sous forme de DTO
     */
    TacheDTO marquerTerminee(Long id);

}
