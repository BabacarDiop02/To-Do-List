package sn.diop.babacar.minitodolist.mapper;

import sn.diop.babacar.minitodolist.entity.TacheEntity;
import sn.diop.babacar.minitodolist.model.TacheDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TacheMapper extends EntityMapper<TacheDTO, TacheEntity> {
}
