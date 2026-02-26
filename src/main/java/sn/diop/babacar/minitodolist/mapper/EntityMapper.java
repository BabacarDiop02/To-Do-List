package sn.diop.babacar.minitodolist.mapper;

public interface EntityMapper<D, E> {

    /**
     * Mappe un DTO vers une Entité
     *
     * @param dto le DTO à mapper
     * @return l'Entité mappée
     */
    E asEntity(D dto);

    /**
     * Mappe une Entité vers un DTO
     *
     * @param entity l'Entité à mapper
     * @return le DTO mappé
     */
    D asDto(E entity);
}
