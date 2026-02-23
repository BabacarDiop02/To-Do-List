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

    @Operation(summary = "Create tache", description = "this endpoint takes input tache and saves it")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing") })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Object> createTache(@RequestBody TacheDTO tacheDTO) {
        try {
            var dto = tacheService.createTache(tacheDTO);
            return Response.ok().setPayload(dto).setMessage("Tache créée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> updateTache(
            @Parameter(name = "id", description = "the tache id to updated") @PathVariable("id") Long id,
            @RequestBody TacheDTO tacheDTO) {
        tacheDTO.setId(id);
        try {
            var dto = tacheService.updateTache(tacheDTO);
            return Response.ok().setPayload(dto).setMessage("Tache modifiée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }

    }

    @Operation(summary = "Read the tache", description = "This endpoint is used to read tache, it takes input id tache")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing") })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> getTache(
            @Parameter(name = "id", description = "the tache id to valid") @PathVariable Long id) {
        try {
            var dto = tacheService.getTache(id);
            return Response.ok().setPayload(dto).setMessage("Tache trouvée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    @Operation(summary = "Read all Taches", description = "It takes input param of the page and returns this list related")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing") })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> getAllTaches(@RequestParam Map<String, String> searchParams, Pageable pageable) {
        var page = tacheService.getAllTaches(searchParams, pageable);
        Response.PageMetadata metadata = Response.PageMetadata.builder().number(page.getNumber())
                .totalElements(page.getTotalElements()).size(page.getSize()).totalPages(page.getTotalPages()).build();
        return Response.ok().setPayload(page.getContent()).setMetadata(metadata);
    }

    @Operation(summary = "delete the tache", description = "Delete tache, it takes input id tache")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing") })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTache(@PathVariable("id") Long id) {
        try {
            tacheService.deleteTache(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Mark tache as terminated", description = "This endpoint marks a tache as terminated by its id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing") })
    @PutMapping("/{id}/terminer")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> marquerTerminee(
            @Parameter(name = "id", description = "the tache id to mark as terminated") @PathVariable("id") Long id) {
        try {
            var dto = tacheService.marquerTerminee(id);
            return Response.ok().setPayload(dto).setMessage("Tache marquée comme terminée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }
}
