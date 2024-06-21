package io.github.orionlibs.orion_iot_webapp;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class OrionRequest implements Serializable
{
    private String internalUse;
}