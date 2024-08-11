package demo.app.car.domain.inspector.mapper;

import demo.app.car.domain.inspector.entity.CarDetail;
import demo.app.car.domain.inspector.model.CarDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarDetailMapper {
  CarDetailMapper INSTANCE = Mappers.getMapper(CarDetailMapper.class);

  @Mappings({
    @Mapping(source="brand", target="brand"),
    @Mapping(source="model", target="model"),
    @Mapping(source="variant", target="variant"),
    @Mapping(source="transmission", target="transmission"),
    @Mapping(source="yearOfCarManufactured", target="yearOfCarManufactured"),
    @Mapping(source="mileage", target = "mileage")
  })
  CarDetail fromPayload(CarDetailDTO carDetailDTO);

  @Mappings({
          @Mapping(source="brand", target="brand"),
          @Mapping(source="model", target="model"),
          @Mapping(source="variant", target="variant"),
          @Mapping(source="transmission", target="transmission"),
          @Mapping(source="yearOfCarManufactured", target="yearOfCarManufactured"),
          @Mapping(source="mileage", target = "mileage")
  })
  CarDetailDTO fromEntity(CarDetail carDetail);
}
