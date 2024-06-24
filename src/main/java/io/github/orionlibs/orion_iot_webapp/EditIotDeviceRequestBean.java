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
public class EditIotDeviceRequestBean extends OrionResponse
{
    private Long deviceID;
    private String deviceName;
    private String connectionURL;
}