package demo.app.car.domain.inspector.mapper.custom;

import demo.app.car.domain.inspector.entity.Inspection;
import demo.app.car.domain.inspector.mapper.CarDetailMapper;
import demo.app.car.domain.inspector.model.InspectionRequestDTO;
import lombok.Getter;


public class InspectionMapper {
  @Getter
  private static final InspectionMapper instance = new InspectionMapper();

  public Inspection fromPayload(InspectionRequestDTO inspectionRequestDTO) {
    var inspection = new Inspection();
    inspection.setCarDetail(CarDetailMapper.INSTANCE.fromPayload(inspectionRequestDTO.carDetailDTO()));
    return inspection;
  }
}
