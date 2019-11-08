package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ubicacion
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-08T18:01:04.874Z[GMT]")
public class Ubicacion   {
  @JsonProperty("latitud")
  private BigDecimal latitud = null;

  @JsonProperty("longitud")
  private BigDecimal longitud = null;

  public Ubicacion latitud(BigDecimal latitud) {
    this.latitud = latitud;
    return this;
  }

  /**
   * Get latitud
   * @return latitud
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public BigDecimal getLatitud() {
    return latitud;
  }

  public void setLatitud(BigDecimal latitud) {
    this.latitud = latitud;
  }

  public Ubicacion longitud(BigDecimal longitud) {
    this.longitud = longitud;
    return this;
  }

  /**
   * Get longitud
   * @return longitud
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public BigDecimal getLongitud() {
    return longitud;
  }

  public void setLongitud(BigDecimal longitud) {
    this.longitud = longitud;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ubicacion ubicacion = (Ubicacion) o;
    return Objects.equals(this.latitud, ubicacion.latitud) &&
        Objects.equals(this.longitud, ubicacion.longitud);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitud, longitud);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ubicacion {\n");
    
    sb.append("    latitud: ").append(toIndentedString(latitud)).append("\n");
    sb.append("    longitud: ").append(toIndentedString(longitud)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
