package io.github.orionlibs.orion_iot_webapp.page;

import io.github.orionlibs.orion_iot.device_details.DevicesDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wapi/v1/dashboard")
public class DashboardController
{
    /*@GetMapping(value = "/test-url")
    public ResponseEntity<ResponseBean> testURL1()
    {
        //MQTTMessageBrokerClient client = new MQTTMessageBrokerClient("topic1", "tcp://0.0.0.0:1883", "client1");
        ResponseBean result1 = ResponseBean.builder()
                        .field1(256L)
                        .field2("hello")
                        .build();
        return ResponseEntity.ok(result1);
    }


    @PostMapping(value = "/test-url", consumes = "application/json")
    public ResponseEntity<ResponseBean> testURL2(@RequestBody RequestBean requestBean)
    {
        //MQTTMessageBrokerClient client = new MQTTMessageBrokerClient("topic1", "tcp://0.0.0.0:1883", "client1");
        ResponseBean result1 = ResponseBean.builder()
                        .field1(512L)
                        .field2(requestBean.getField1())
                        .build();
        return ResponseEntity.ok(result1);
    }*/


    @GetMapping(value = "/number-of-iot-devices")
    public @ResponseBody Long dashboardPageGetNumberOfIoTDevices()
    {
        return DevicesDAO.getNumberOfRecords();
    }
}
