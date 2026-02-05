package com.lin.webtemplate.web;

import java.time.Instant;


@RestController
public class HeartbeatController {

    private final HealthEndpoint healthEndpoint;

    public HeartbeatController(HealthEndpoint healthEndpoint) {
        this.healthEndpoint = healthEndpoint;
    }

    @GetMapping("/heartbeat")
    public ResponseEntity<Result<HeartbeatData>> heartbeat() {
        HealthComponent health = healthEndpoint.health();
        String status = health.getStatus().getCode();
        HeartbeatData data = new HeartbeatData(status, Instant.now().toEpochMilli());

        if ("UP".equals(status)) {
            return ResponseEntity.ok(Result.ok(data));
        }

        // Align with common probe semantics: non-UP maps to 503.
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Result.fail(1, "NOT_UP: " + status, data));
    }

    public record HeartbeatData(String status, long timestamp) {
    }
}
