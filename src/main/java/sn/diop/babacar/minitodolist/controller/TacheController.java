package sn.diop.babacar.minitodolist.controller;

import sn.diop.babacar.minitodolist.model.TacheDTO;
import sn.diop.babacar.minitodolist.model.Response;
import sn.diop.babacar.minitodolist.services.TacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("taches")
@RequiredArgsConstructor
@CrossOrigin("*")

public class TacheController {

    private final TacheService tacheService;

    /**
     * Crée une nouvelle tâche.
     *
     * @param tacheDTO les données de la tâche à créer
     * @return une réponse contenant la tâche créée ou un message d'erreur
     */
    @Operation(summary = "Créer une tâche", description = "ce point de terminaison prend une tâche en entrée et l'enregistre.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Succès"),
            @ApiResponse(responseCode = "400", description = "La requête envoyée par le client est incorrecte."),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors du traitement de la requête.") })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Object> createTache(@RequestBody TacheDTO tacheDTO) {
        try {
            var dto = tacheService.createTache(tacheDTO);
            return Response.ok().setPayload(dto).setMessage("Tâche créée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    /**
     * Met à jour une tâche existante.
     *
     * @param id       l'identifiant de la tâche à mettre à jour
     * @param tacheDTO les données de la tâche mises à jour
     * @return une réponse contenant la tâche mise à jour ou un message d'erreur
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> updateTache(
            @Parameter(name = "id", description = "l'identifiant de la tâche à mettre à jour") @PathVariable("id") Long id,
            @RequestBody TacheDTO tacheDTO) {
        tacheDTO.setId(id);
        try {
            var dto = tacheService.updateTache(tacheDTO);
            return Response.ok().setPayload(dto).setMessage("Tâche modifiée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }

    }

    /**
     * Récupère une tâche par son identifiant.
     *
     * @param id l'identifiant de la tâche à récupérer
     * @return une réponse contenant la tâche ou un message d'erreur
     */
    @Operation(summary = "Lire la tâche", description = "Ce point de terminaison est utilisé pour lire une tâche, il prend l'identifiant de la tâche en entrée")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Succès"),
            @ApiResponse(responseCode = "400", description = "La requête envoyée par le client est incorrecte"),
            @ApiResponse(responseCode = "404", description = "La ressource demandée n'existe pas"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors du traitement de la requête") })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> getTache(
            @Parameter(name = "id", description = "l'identifiant de la tâche à valider") @PathVariable Long id) {
        try {
            var dto = tacheService.getTache(id);
            return Response.ok().setPayload(dto).setMessage("Tâche trouvée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    /**
     * Récupère une liste paginée de toutes les tâches.
     *
     * @param searchParams filtres de recherche
     * @param pageable     informations de pagination
     * @return une réponse contenant la liste des tâches et les métadonnées de
     *         pagination
     */
    @Operation(summary = "Lire toutes les tâches", description = "Ce point de terminaison prend les paramètres de page en entrée et retourne la liste correspondante")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Succès"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors du traitement de la requête") })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> getAllTaches(@RequestParam Map<String, String> searchParams, Pageable pageable) {
        var page = tacheService.getAllTaches(searchParams, pageable);
        Response.PageMetadata metadata = Response.PageMetadata.builder().number(page.getNumber())
                .totalElements(page.getTotalElements()).size(page.getSize()).totalPages(page.getTotalPages()).build();
        return Response.ok().setPayload(page.getContent()).setMetadata(metadata);
    }

    /**
     * Supprime une tâche par son identifiant.
     *
     * @param id l'identifiant de la tâche à supprimer
     */
    @Operation(summary = "supprimer la tâche", description = "Supprimer une tâche, prend l'identifiant de la tâche en entrée")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Pas de contenu"),
            @ApiResponse(responseCode = "400", description = "La requête envoyée par le client est incorrecte"),
            @ApiResponse(responseCode = "404", description = "La ressource demandée n'existe pas"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors du traitement de la requête") })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTache(@PathVariable("id") Long id) {
        try {
            tacheService.deleteTache(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marque une tâche comme terminée.
     *
     * @param id l'identifiant de la tâche à marquer comme terminée
     * @return une réponse contenant la tâche mise à jour ou un message d'erreur
     */
    @Operation(summary = "Marquer la tâche comme terminée", description = "Ce point de terminaison marque une tâche comme terminée par son identifiant")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Succès"),
            @ApiResponse(responseCode = "400", description = "La requête envoyée par le client est incorrecte"),
            @ApiResponse(responseCode = "404", description = "La ressource demandée n'existe pas"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors du traitement de la requête") })
    @PutMapping("/{id}/terminer")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> marquerTerminee(
            @Parameter(name = "id", description = "l'identifiant de la tâche à marquer comme terminée") @PathVariable("id") Long id) {
        try {
            var dto = tacheService.marquerTerminee(id);
            return Response.ok().setPayload(dto).setMessage("Tâche marquée comme terminée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }
}
