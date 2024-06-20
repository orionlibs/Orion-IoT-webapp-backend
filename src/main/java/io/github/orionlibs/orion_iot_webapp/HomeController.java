package io.github.orionlibs.orion_iot_webapp;

import io.github.orionlibs.orion_iot.MQTTMessageBrokerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping(value = "/home")
public class HomeController
{
    @PostMapping(value = "/test-url", consumes = "application/json")
    public ResponseEntity<ResponseBean> testURL(@RequestBody RequestBean requestBean/*, HttpServletRequest request, HttpServletResponse response, Model model*/)
    {
        System.out.println("------" + requestBean.getField1());
        MQTTMessageBrokerClient client = new MQTTMessageBrokerClient(requestBean.getField1());
        ResponseBean result1 = ResponseBean.builder()
                        .field1(128L)
                        .field2(client.testVariable)
                        .build();
        return ResponseEntity.ok(result1);
    }
}
