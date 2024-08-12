package demo.app.car.domain.inspection.mapper;

import demo.app.car.domain.inspection.entity.Inspection;
import demo.app.car.domain.inspection.model.InspectionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InspectionMapper {
  InspectionMapper INSTANCE = Mappers.getMapper(InspectionMapper.class);

  @Mappings({
    @Mapping(source="id", target="id"),
    @Mapping(source="carDetailDTO", target="carDetailDTO"),
    @Mapping(source="evaluationDTO", target="evaluationDTO"),
    @Mapping(source="createdAt", target="createdAt"),
    @Mapping(source="evaluatedAt", target="evaluatedAt")
  })
  Inspection fromDTO(InspectionDTO inspectionDTO);

  @Mappings({
          @Mapping(source="id", target="id"),
          @Mapping(source="carDetailDTO", target="carDetailDTO"),
          @Mapping(source="evaluationDTO", target="evaluationDTO"),
          @Mapping(source="createdAt", target="createdAt"),
          @Mapping(source="evaluatedAt", target="evaluatedAt")
  })
  InspectionDTO fromEntity(Inspection inspection);
}
