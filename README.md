# MCP Cab Rooster Management

A Spring Boot application that implements a Model Context Protocol (MCP) server for managing cab ride requests. This application exposes tools that can be used by AI assistants like Claude Desktop to handle ride requests and status checks.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [MCP Server Configuration](#mcp-server-configuration)
- [Available Tools](#available-tools)
- [Testing the Integration](#testing-the-integration)
- [Development](#development)
- [License](#license)

## Features
- MCP Server implementation for AI tool integration
- Ride request management
- Ride status tracking
- Structured error handling and validation
- Detailed logging

## Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Claude Desktop (for MCP client integration)

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd MCP_Cab_Rooster_Management
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

## Running the Application

### Running with Maven
```bash
mvn spring-boot:run
```

The application will start on port 8000 by default.

## MCP Server Configuration

The MCP server is configured in `src/main/resources/application.properties`:

```properties
# MCP Server Configuration
spring.ai.mcp.server.type=SYNC
spring.ai.mcp.server.name=cab-booking-mcp-server
spring.ai.mcp.server.version=1.0.0
spring.ai.mcp.server.port=8000

# Enable MCP features
spring.ai.mcp.features.tools.enabled=true
spring.ai.mcp.features.resources.enabled=true
spring.ai.mcp.features.prompts.enabled=true
```

## Available Tools

The following tools are exposed via the MCP server:

### 1. requestRide
Request a new cab ride.

**Parameters:**
- `name` (String): Passenger's name
- `email` (String): Passenger's email
- `phone` (String): Passenger's phone number
- `currentLatitude` (BigDecimal): Current location latitude
- `currentLongitude` (BigDecimal): Current location longitude
- `homeLatitude` (BigDecimal): Home/destination latitude
- `homeLongitude` (BigDecimal): Home/destination longitude
- `needsRide` (boolean): Whether a ride is needed

**Example Usage in Claude Desktop:**
```
Book me a ride from my current location to home
```

### 2. getRideStatus
Get the status of an existing ride request.

**Parameters:**
- `rideId` (String): The ID of the ride request

**Example Usage in Claude Desktop:**
```
What's the status of my ride request RIDE-1234?
```

## Testing the Integration

### Testing with Claude Desktop

1. Ensure your Spring Boot application is running
2. In Claude Desktop, go to Settings > AI Tools
3. Configure the MCP Server URL to: `http://localhost:8000`
4. Make sure the MCP Server Name matches: `cab-booking-mcp-server`
5. Save the settings and restart Claude Desktop if needed

### Testing with cURL

You can also test the API directly using cURL:

```bash
# Request a ride
curl -X POST http://localhost:8000/api/rides \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "+1234567890",
    "currentLatitude": 37.7749,
    "currentLongitude": -122.4194,
    "homeLatitude": 37.7858,
    "homeLongitude": -122.4064,
    "needsRide": true
  }'

# Get ride status
curl "http://localhost:8000/api/rides/RIDE-1234/status"
```

## Development

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── model/         # Data models
│   │       ├── service/       # Business logic and MCP tools
│   │       └── DemoApplication.java  # Main application class
│   └── resources/
│       └── application.properties  # Configuration
└── test/                      # Test files
```

### Building and Running Tests

```bash
# Run tests
mvn test

# Build the application
mvn clean package

# Run the application
java -jar target/mcp-requestride-list-0.0.1-SNAPSHOT.jar
```

### Debugging

To enable debug logging, add the following to `application.properties`:

```properties
logging.level.com.example.demo=DEBUG
logging.level.org.springframework.ai=DEBUG
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## References

- [Model Context Protocol Documentation](https://modelcontextprotocol.io/)
- [MCP Server Quickstart](https://modelcontextprotocol.io/quickstart/server#windows)
- [Spring AI Documentation](https://spring.io/projects/spring-ai)

## Troubleshooting

### Common Issues

1. **MCP Server Not Starting**
   - Check if port 8000 is available
   - Verify Java 17+ is installed and configured
   - Check application logs for errors

2. **Connection Issues with Claude Desktop**
   - Ensure both applications are running
   - Verify the MCP server URL and name in Claude Desktop settings
   - Check firewall settings if running on different machines

3. **Tool Not Found**
   - Ensure the tool methods are properly annotated with `@Tool`
   - Verify the tool is registered in the Spring context
   - Check application logs for any registration errors

For additional help, please open an issue in the repository.
