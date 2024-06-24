package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.device_details.DeviceDAO;
import io.github.orionlibs.orion_iot.device_details.DeviceModel;
import io.github.orionlibs.orion_iot_webapp.AddIotDeviceRequestBean;
import io.github.orionlibs.orion_iot_webapp.AddIotDeviceResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/iot-devices")
public class AddIoTDeviceController
{
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AddIotDeviceResponseBean> addIoTDevicePageAddDevice(@RequestBody AddIotDeviceRequestBean requestBean)
    {
        try
        {
            DeviceDAO.save(DeviceModel.builder()
                            .deviceName(requestBean.getDeviceName())
                            .connectionURL(requestBean.getConnectionURL())
                            .build());
            return ResponseEntity.ok(AddIotDeviceResponseBean.builder().build());
        }
        catch(Throwable e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
