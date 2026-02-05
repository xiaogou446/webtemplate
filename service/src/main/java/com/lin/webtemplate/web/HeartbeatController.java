package com.lin.webtemplate.web;

import java.time.Instant;

import com.lin.webtemplate.web.response.Result;
import lombok.Data;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/health"))
public class HeartbeatController {

    private final HealthEndpoint healthEndpoint;

    public HeartbeatController(HealthEndpoint healthEndpoint) {
        this.healthEndpoint = healthEndpoint;
    }

    @GetMapping("/heartbeat")
    public Result<HeartbeatData> heartbeat() {
        HealthComponent health = healthEndpoint.health();
        String status = health.getStatus().getCode();
        HeartbeatData data = new HeartbeatData(status, Instant.now().toEpochMilli());

        if ("UP".equals(status)) {
            return Result.ok(data);
        }

        // We keep HTTP status as 200 and express health via Result.code/message.
        return Result.fail(1, "NOT_UP: " + status, data);
    }

    @Data
    public static class HeartbeatData {

        private final String status;
        private final long timestamp;
    }
}
