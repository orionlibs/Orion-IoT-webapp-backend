package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.device_details.DevicesDAO;
import io.github.orionlibs.orion_iot.device_details.DeviceModel;
import io.github.orionlibs.orion_iot_webapp.EditIotDeviceRequestBean;
import io.github.orionlibs.orion_iot_webapp.EditIotDeviceResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/iot-devices")
public class EditIoTDeviceController
{
    @PutMapping(consumes = "application/json")
    public ResponseEntity<EditIotDeviceResponseBean> addIoTDevicePageAddDevice(@RequestBody EditIotDeviceRequestBean requestBean)
    {
        try
        {
            DeviceModel device = DevicesDAO.getByID(requestBean.getDeviceID());
            device.setDeviceName(requestBean.getDeviceName());
            device.setConnectionURL(requestBean.getConnectionURL());
            DevicesDAO.update(device);
            return ResponseEntity.ok(EditIotDeviceResponseBean.builder().build());
        }
        catch(Throwable e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
