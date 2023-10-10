package bernie.ver1.berniever1.controller;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api/v1")
public class HomeController {

    // @Operation(summary = "Get home")
    // @ApiResponses(value = {
    //         @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful operation"),
    //         @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
    //         @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Pet not found") })
    @GetMapping(value ="/abc")
    public String index() {
        return "Hello World";
    }
}
