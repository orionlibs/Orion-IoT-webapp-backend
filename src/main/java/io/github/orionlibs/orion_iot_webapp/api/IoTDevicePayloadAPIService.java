package io.github.orionlibs.orion_iot_webapp.api;

import io.github.orionlibs.core.calendar.CalendarService;
import io.github.orionlibs.orion_iot.device_payload.DevicePayloadModel;
import io.github.orionlibs.orion_iot.device_payload.DevicePayloadsDAO;
import io.github.orionlibs.orion_iot_webapp.AddIotDeviceResponseBean;
import io.github.orionlibs.orion_iot_webapp.SaveIotDevicePayloadRequestBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/iot-devices/payloads")
public class IoTDevicePayloadAPIService
{
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AddIotDeviceResponseBean> IoTDevicePayloadAPISavePayload(@RequestBody SaveIotDevicePayloadRequestBean requestBean)
    {
        try
        {
            DevicePayloadsDAO.save(DevicePayloadModel.builder()
                            .topic(requestBean.getTopic())
                            .payload(requestBean.getPayload())
                            .timestampOfRecord(CalendarService.getCurrentDatetimeAsSQLTimestamp())
                            .isDeleted(Boolean.FALSE)
                            .build());
            return ResponseEntity.ok(AddIotDeviceResponseBean.builder().build());
        }
        catch(Throwable e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
