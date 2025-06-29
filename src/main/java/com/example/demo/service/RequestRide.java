package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Ride;
import org.springframework.ai.tool.annotation.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class RequestRide {
    private static final Logger logger = LoggerFactory.getLogger(RequestRide.class);
    
    // In-memory storage for demo purposes (replace with database in production)
    private final Map<String, Ride> rideRequests = new HashMap<>();

    @Tool(name = "requestRide", description = "An API to request a ride. Required parameters: name, email, phone, currentLatitude, currentLongitude, homeLatitude, homeLongitude, needsRide")
    public ResponseEntity<Map<String, Object>> requestRide(Ride ride) {
        try {
            logger.info("Received ride request: {}", ride);
            
            // Validate required fields
            if (ride == null || 
                ride.getName() == null || ride.getName().trim().isEmpty() ||
                ride.getEmail() == null || ride.getEmail().trim().isEmpty() ||
                ride.getPhone() == null || ride.getPhone().trim().isEmpty() ||
                ride.getCurrentLatitude() == null || ride.getCurrentLongitude() == null) {
                
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", "error");
                errorResponse.put("message", "Missing required fields. Please provide name, email, phone, and location coordinates.");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            // Generate a unique ride ID
            String rideId = "RIDE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            ride.setId(Long.parseLong(rideId.split("-")[1], 16));
            
            // Set timestamp
            ride.setRequestTime(LocalDateTime.now());
            
            // In a real application, you would:
            // 1. Validate the pickup and drop locations
            // 2. Find available drivers
            // 3. Calculate estimated fare
            // 4. Send notifications
            
            // For demo, we'll just store the request
            rideRequests.put(rideId, ride);
            
            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("rideId", rideId);
            response.put("message", "Ride request received and is being processed");
            response.put("estimatedWaitTime", "5-10 minutes");
            response.put("pickupLocation", String.format("%.6f, %.6f", 
                ride.getCurrentLatitude(), ride.getCurrentLongitude()));
                
            logger.info("Ride request processed successfully. Ride ID: {}", rideId);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error processing ride request", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed to process ride request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    // Additional method to get ride status (can be called by Claude)
    @Tool(name = "getRideStatus", description = "Get the status of an existing ride request")
    public ResponseEntity<Map<String, Object>> getRideStatus(String rideId) {
        if (!rideRequests.containsKey(rideId)) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Ride not found with ID: " + rideId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        
        Ride ride = rideRequests.get(rideId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("rideId", rideId);
        response.put("status", "assigned"); // In a real app, this would be dynamic
        response.put("driverName", "John Doe"); // Mock data
        response.put("driverPhone", "+1-555-123-4567"); // Mock data
        response.put("estimatedArrival", "5 minutes"); // Mock data
        
        return ResponseEntity.ok(response);
    }
}