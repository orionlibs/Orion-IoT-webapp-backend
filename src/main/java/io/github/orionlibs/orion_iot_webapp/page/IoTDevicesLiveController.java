package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.database.IoTDatabase;
import io.github.orionlibs.orion_iot.device_details.DeviceDAO;
import io.github.orionlibs.orion_iot.device_details.DeviceModel;
import io.github.orionlibs.orion_iot_webapp.IotDevicesSummariesResponseBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IoTDevicesLiveController
{
    private static int i = 0;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Scheduled(fixedRate = 3000)
    public void iotDevicesLivePageSummaries()
    {
        try
        {
            List<DeviceModel> devices = DeviceDAO.getAllWithAscendingOrder(IoTDatabase.deviceID);
            List<IotDevicesSummariesResponseBean.DeviceModel> devicesTemp = new ArrayList<>();
            for(DeviceModel device : devices)
            {
                devicesTemp.add(IotDevicesSummariesResponseBean.DeviceModel.builder()
                                .deviceID(device.getDeviceID() + i)
                                .deviceName(device.getDeviceName() + "-" + i)
                                .build());
            }
            IotDevicesSummariesResponseBean responseBean = IotDevicesSummariesResponseBean.builder()
                            .devices(devicesTemp)
                            .build();
            i++;
            this.messagingTemplate.convertAndSend("/topic/iot-devices-live/summaries", responseBean);
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
    }
}
