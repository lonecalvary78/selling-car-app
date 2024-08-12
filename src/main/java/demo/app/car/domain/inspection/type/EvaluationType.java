package demo.app.car.domain.inspection.type;

import demo.app.car.domain.inspection.model.CarDetailDTO;
import demo.app.car.domain.inspection.model.EvaluationDTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class EvaluationType implements UserType<EvaluationDTO> {
  @Override
  public int getSqlType() {
    return SqlTypes.JSON;
  }

  @Override
  public Class<EvaluationDTO> returnedClass() {
    return EvaluationDTO.class;
  }

  @Override
  public boolean equals(EvaluationDTO oneEntity, EvaluationDTO otherEntity) {
    return otherEntity.equals(oneEntity);
  }

  @Override
  public int hashCode(EvaluationDTO evaluationDTO) {
    return evaluationDTO.hashCode();
  }

  @Override
  public EvaluationDTO nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
    var content = rs.getString(position);
    if(content == null)
      return null;
    try {
      var parser = newParser();
      return parser.fromJson(content, EvaluationDTO.class);
    } catch(JsonbException thrownJsonbException) {
      throw new RuntimeException(thrownJsonbException);
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement stmt, EvaluationDTO evaluationDTO, int position, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
    if(evaluationDTO == null) {
      stmt.setNull(position, Types.OTHER);
    }
    try {
      var parser = newParser();
      stmt.setObject(position,parser.toJson(evaluationDTO), Types.OTHER);
    } catch(JsonbException thrownJsonbException) {
      throw new HibernateException(thrownJsonbException);
    }
  }

  @Override
  public EvaluationDTO deepCopy(EvaluationDTO evaluationDTO) {
    return null;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(EvaluationDTO evaluationDTO) {
    return null;
  }

  @Override
  public EvaluationDTO assemble(Serializable serializable, Object o) {
    return null;
  }

  private Jsonb newParser() {
    return JsonbBuilder.create();
  }
}
