package com.example.demo;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; 
import java.util.List;
import com.example.demo.service.RequestRide;
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

	@Bean
	// public List<ToolCallback> tools(RequestRide requestRide) {
	// 	return List.of(ToolCallbacks.from(requestRide));
	// }
	public ToolCallbackProvider weatherTools(RequestRide requestRide) {
		return  MethodToolCallbackProvider.builder().toolObjects(requestRide).build();
	}
}
