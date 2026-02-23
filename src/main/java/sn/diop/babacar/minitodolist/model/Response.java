package sn.diop.babacar.minitodolist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Builder
public class Response<T> {

    private Status status;
    private T payload;
    private Object metadata;
    private Object message;

    /**
     * Crée une réponse avec un statut 400 Mauvaise Requête (Bad Request).
     *
     * @param <T> le type du contenu de la réponse
     * @return un objet Response avec le statut BAD_REQUEST
     */
    public static <T> Response<T> badRequest() {
        Response<T> response = new Response<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    /**
     * Crée une réponse avec un statut 200 OK.
     *
     * @param <T> le type du contenu de la réponse
     * @return un objet Response avec le statut OK
     */
    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(Status.OK);
        return response;
    }

    public enum Status {
        OK, BAD_REQUEST
    }

    @Getter
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Builder
    public static class PageMetadata {
        private final int size;
        private final long totalElements;
        private final int totalPages;
        private final int number;

        /**
         * Constructeur pour PageMetadata.
         *
         * @param size          la taille de la page
         * @param totalElements le nombre total d'éléments
         * @param totalPages    le nombre total de pages
         * @param number        le numéro de la page actuelle
         */
        public PageMetadata(int size, long totalElements, int totalPages, int number) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
        }
    }

}
