package demo.app.car.domain.inspection.type;

import demo.app.car.domain.inspection.model.CarDetailDTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

public class CarDetailType implements UserType<CarDetailDTO> {
  @Override
  public int getSqlType() {
    return SqlTypes.JSON;
  }

  @Override
  public Class<CarDetailDTO> returnedClass() {
    return CarDetailDTO.class;
  }

  @Override
  public boolean equals(CarDetailDTO oneEntity, CarDetailDTO otherEntity) {
    return otherEntity.equals(oneEntity);
  }

  @Override
  public int hashCode(CarDetailDTO carDetailDTO) {
    return carDetailDTO.hashCode();
  }

  @Override
  public CarDetailDTO nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
    var content = rs.getString(position);
    if(content == null)
      return null;
    try {
      var parser = newParser();
      return parser.fromJson(content, CarDetailDTO.class);
    } catch(JsonbException thrownJsonbException) {
      throw new RuntimeException(thrownJsonbException);
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement stmt, CarDetailDTO carDetailDTO, int position, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
    if(carDetailDTO == null) {
      stmt.setNull(position, Types.OTHER);
    }
    try {
      var parser = newParser();
      stmt.setObject(position,parser.toJson(carDetailDTO), Types.OTHER);
    } catch(JsonbException thrownJsonbException) {
      throw new HibernateException(thrownJsonbException);
    }
  }

  @Override
  public CarDetailDTO deepCopy(CarDetailDTO carDetailDTO) {
    try {
      var byteArrayOutputStream = new ByteArrayOutputStream();
      var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(carDetailDTO);
      objectOutputStream.flush();
      objectOutputStream.close();
      byteArrayOutputStream.close();

      var byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
      CarDetailDTO objectValue = (CarDetailDTO)new ObjectInputStream(byteArrayInputStream).readObject();
      byteArrayInputStream.close();
      return objectValue;
    } catch(ClassNotFoundException| IOException thrownException) {
      throw new HibernateException(thrownException);
    }
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(CarDetailDTO carDetailDTO) {
    return null;
  }

  @Override
  public CarDetailDTO assemble(Serializable serializable, Object o) {
    return null;
  }

  private Jsonb newParser() {
    return JsonbBuilder.create();
  }
}
