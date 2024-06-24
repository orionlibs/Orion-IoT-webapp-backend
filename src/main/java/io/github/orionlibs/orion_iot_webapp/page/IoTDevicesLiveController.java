package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.database.IoTDatabase;
import io.github.orionlibs.orion_iot.device_details.DeviceModel;
import io.github.orionlibs.orion_iot.device_details.DevicesDAO;
import io.github.orionlibs.orion_iot.device_payload.DevicePayloadsDAO;
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
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Scheduled(initialDelay = 5000, fixedRate = 3000)
    public void iotDevicesLivePageSummaries()
    {
        try
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
            IotDevicesSummariesResponseBean responseBean = IotDevicesSummariesResponseBean.builder()
                            .devices(devicesTemp)
                            .build();
            this.messagingTemplate.convertAndSend("/topic/iot-devices-live/summaries", responseBean);
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
    }
}
