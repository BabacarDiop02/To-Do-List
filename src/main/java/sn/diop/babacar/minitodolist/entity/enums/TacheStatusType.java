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

public enum TacheStatusType {
    EN_COURS("En cours"),
    TERMINER("Terminer");


    @Getter
    @Setter
    private String description;

    TacheStatusType(String description) {
        this.description = description;
    }

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
        throw new IllegalArgumentException(MessageFormat.format("{0} not found with the value: {1} in [{2}]", TacheStatusType.class, tacheStatusType, values()));
    }

    @JsonValue
    Map<String, Object> getModule() {
        return Map.of(
                "name", name(),
                "description", description
        );
    }

    public static Set<TacheStatusType> getAllStatuts() {
        return stream(values())
                .collect(Collectors.toSet());
    }
}
