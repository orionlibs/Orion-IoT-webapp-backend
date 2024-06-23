package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.database.IoTDatabase;
import io.github.orionlibs.orion_iot.device_details.DeviceDAO;
import io.github.orionlibs.orion_iot.device_details.DeviceModel;
import io.github.orionlibs.orion_iot_webapp.IotDevicesSummariesResponseBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/iot-devices")
public class IoTDevicesController
{
    @GetMapping(value = "/summaries")
    public ResponseEntity<IotDevicesSummariesResponseBean> iotDevicesPageSummaries()
    {
        DeviceDAO.save(DeviceModel.builder()
                        .deviceName("device 1")
                        .build());
        DeviceDAO.save(DeviceModel.builder()
                        .deviceName("device 2")
                        .build());
        DeviceDAO.save(DeviceModel.builder()
                        .deviceName("device 3")
                        .build());
        DeviceDAO.save(DeviceModel.builder()
                        .deviceName("device 4")
                        .build());
        DeviceDAO.save(DeviceModel.builder()
                        .deviceName("device 5")
                        .build());
        List<DeviceModel> devices = DeviceDAO.getAllWithAscendingOrder(IoTDatabase.deviceID);
        List<IotDevicesSummariesResponseBean.DeviceModel> devicesTemp = new ArrayList<>();
        for(DeviceModel device : devices)
        {
            devicesTemp.add(IotDevicesSummariesResponseBean.DeviceModel.builder()
                            .deviceID(device.getDeviceID())
                            .deviceName(device.getDeviceName())
                            .build());
        }
        return ResponseEntity.ok(IotDevicesSummariesResponseBean.builder()
                        .devices(devicesTemp)
                        .build());
    }
}
