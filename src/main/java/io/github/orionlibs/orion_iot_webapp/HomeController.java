package io.github.orionlibs.orion_iot_webapp;

import io.github.orionlibs.orion_iot.device_message.MQTTMessageBrokerClient;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class HomeController
{
    @PostMapping(value = "/test-url", consumes = "application/json")
    public ResponseEntity<ResponseBean> testURL(@RequestBody RequestBean requestBean/*, HttpServletRequest request, HttpServletResponse response, Model model*/) throws MqttException, IOException
    {
        MQTTMessageBrokerClient client = new MQTTMessageBrokerClient("topic1", "tcp://0.0.0.0:1883", "client1");
        ResponseBean result1 = ResponseBean.builder()
                        .field1(128L)
                        //.field2(client.testVariable)
                        .build();
        return ResponseEntity.ok(result1);
    }
}
