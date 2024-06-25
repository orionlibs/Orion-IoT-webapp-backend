package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.database.IoTDatabase;
import io.github.orionlibs.orion_iot.device_details.DeviceModel;
import io.github.orionlibs.orion_iot.device_details.DevicesDAO;
import io.github.orionlibs.orion_iot.device_payload.DevicePayloadsDAO;
import io.github.orionlibs.orion_iot_webapp.IotDevicesSummariesResponseBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wapi/v1/iot-devices")
public class IoTDevicesController
{
    @GetMapping(value = "/summaries")
    public ResponseEntity<IotDevicesSummariesResponseBean> iotDevicesPageSummaries()
    {
        List<DeviceModel> devices = DevicesDAO.getAllWithAscendingOrder(IoTDatabase.deviceID);
        List<IotDevicesSummariesResponseBean.DeviceModel> devicesTemp = new ArrayList<>();
        for(DeviceModel device : devices)
        {
            devicesTemp.add(IotDevicesSummariesResponseBean.DeviceModel.builder()
                            .deviceID(device.getDeviceID())
                            .deviceName(device.getDeviceName())
                            .connectionURL(device.getConnectionURL())
                            .payloads(DevicePayloadsDAO.getNumberOfRecordsForDeviceID(device.getDeviceID()))
                            .build());
        }
        return ResponseEntity.ok(IotDevicesSummariesResponseBean.builder()
                        .devices(devicesTemp)
                        .build());
    }
}
