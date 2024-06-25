package io.github.orionlibs.orion_iot_webapp;

import io.github.orionlibs.core.calendar.SQLTimestamp;
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
public class IotDevicesPayloadsResponseBean extends OrionResponse
{
    private List<DevicePayloadModel> payloads;


    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class DevicePayloadModel implements Serializable
    {
        private String topic;
        private String payload;
        private String timestampOfRecord;
    }
}