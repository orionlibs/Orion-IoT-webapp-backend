package io.github.orionlibs.orion_iot_webapp;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class IotDevicesSummariesResponseBean extends OrionResponse
{
    private List<DeviceModel> devices;


    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class DeviceModel implements Serializable
    {
        private Long deviceID;
        private String deviceName;
        private String connectionURL;
    }
}