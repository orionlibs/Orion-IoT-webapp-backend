package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.device_payload.DevicePayloadModel;
import io.github.orionlibs.orion_iot.device_payload.DevicePayloadsDAO;
import io.github.orionlibs.orion_iot_webapp.IotDevicesPayloadsResponseBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wapi/v1/iot-devices/payloads")
public class IoTDevicePayloadsController
{
    @GetMapping(value = "/{deviceID}")
    public ResponseEntity<IotDevicesPayloadsResponseBean> iotDevicePayloadsPageLatestPayloads(@PathVariable Long deviceID, @RequestParam(name = "latest", required = true) boolean latest)
    {
        List<IotDevicesPayloadsResponseBean.DevicePayloadModel> payloadsTemp = new ArrayList<>();
        List<DevicePayloadModel> payloads = null;
        if(latest)
        {
            payloads = DevicePayloadsDAO.getNLatestByDeviceID(deviceID, 10);
        }
        if(payloads != null)
        {
            for(DevicePayloadModel payload : payloads)
            {
                payloadsTemp.add(IotDevicesPayloadsResponseBean.DevicePayloadModel.builder()
                                .topic(payload.getTopic())
                                .payload(payload.getPayload())
                                .timestampOfRecord(payload.getTimestampOfRecord().printLongDateTime())
                                .build());
            }
        }
        return ResponseEntity.ok(IotDevicesPayloadsResponseBean.builder()
                        .payloads(payloadsTemp)
                        .build());
    }
}
