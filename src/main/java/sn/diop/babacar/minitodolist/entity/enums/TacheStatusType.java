package sn.diop.babacar.minitodolist.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * Enumération représentant les différents statuts d'une tâche.
 */
public enum TacheStatusType {
    /**
     * Tâche en cours de réalisation.
     */
    EN_COURS("En cours"),
    /**
     * Tâche terminée.
     */
    TERMINER("Terminer");

    @Getter
    @Setter
    private String description;

    /**
     * Constructeur pour TacheStatusType.
     *
     * @param description la description du statut
     */
    TacheStatusType(String description) {
        this.description = description;
    }

    /**
     * Crée une instance de TacheStatusType à partir d'une valeur (String ou Map).
     *
     * @param tacheStatusType la valeur à convertir
     * @return l'instance correspondante de TacheStatusType
     * @throws IllegalArgumentException si la valeur ne correspond à aucun statut
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TacheStatusType fromValue(Object tacheStatusType) {
        if (tacheStatusType instanceof Map) {
            Map<String, Object> mapTacheStatusType = (Map<String, Object>) tacheStatusType;
            if (mapTacheStatusType.containsKey("name")) {
                return TacheStatusType.valueOf(mapTacheStatusType.get("name").toString());
            }
        }
        if (tacheStatusType instanceof String) {
            return TacheStatusType.valueOf(tacheStatusType.toString());
        }
        throw new IllegalArgumentException(MessageFormat.format("{0} not found with the value: {1} in [{2}]",
                TacheStatusType.class, tacheStatusType, values()));
    }

    /**
     * Retourne une représentation sous forme de map du statut pour la sérialisation
     * JSON.
     *
     * @return une map contenant le nom et la description du statut
     */
    @JsonValue
    Map<String, Object> getModule() {
        return Map.of(
                "name", name(),
                "description", description);
    }

    /**
     * Retourne l'ensemble de tous les statuts disponibles.
     *
     * @return un Set contenant tous les TacheStatusType
     */
    public static Set<TacheStatusType> getAllStatuts() {
        return stream(values())
                .collect(Collectors.toSet());
    }
}
