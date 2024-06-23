package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.database.IoTDatabase;
import io.github.orionlibs.orion_iot.device_details.DeviceDAO;
import io.github.orionlibs.orion_iot.device_details.DeviceModel;
import io.github.orionlibs.orion_iot_webapp.IotDevicesSummariesResponseBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class IoTDevicesLiveController
{
    @MessageMapping("/chat")
    @SendTo("/iot-devices-live/summaries")
    public IotDevicesSummariesResponseBean iotDevicesLivePageSummaries(MessageModel message)
    {
        List<DeviceModel> devices = DeviceDAO.getAllWithAscendingOrder(IoTDatabase.deviceID);
        List<IotDevicesSummariesResponseBean.DeviceModel> devicesTemp = new ArrayList<>();
        for(DeviceModel device : devices)
        {
            devicesTemp.add(IotDevicesSummariesResponseBean.DeviceModel.builder()
                            .deviceID(device.getDeviceID())
                            .deviceName(device.getDeviceName())
                            .build());
        }
        System.out.println("------" + message.from);
        System.out.println("------" + message.message);
        System.out.println("------" + devices.size());
        return IotDevicesSummariesResponseBean.builder()
                        .devices(devicesTemp)
                        .build();
    }


    public static class MessageModel
    {
        private String from;
        private String message;


        public String getFrom()
        {
            return this.from;
        }


        public String getMessage()
        {
            return this.message;
        }


        public void setFrom(String from)
        {
            this.from = from;
        }


        public void setMessage(String message)
        {
            this.message = message;
        }
    }
}
