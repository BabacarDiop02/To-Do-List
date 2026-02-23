package sn.diop.babacar.minitodolist.mapper;

public interface EntityMapper<D, E> {

    E asEntity(D dto);

    D asDto(E entity);
}
