package io.github.orionlibs.orion_iot_webapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ResponseBean extends OrionResponse
{
    private Long field1;
    private String field2;
}