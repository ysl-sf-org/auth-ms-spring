package application.auth.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Auth API")
@RequestMapping("/")
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);
    
//    @Autowired
//    private CustomerServiceClient customerClient;
    
    /**
     * check
     */
//    @RequestMapping("/healthz")
//    @ResponseBody String check() {
//    	customerClient.healthCheck();
//        return "it works!";
//    }

    /**
     * Handle auth header
     * @return HTTP 200 if success
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    @ResponseBody ResponseEntity<?> getAuthenticate() {
    	logger.debug("GET /authenticate");
    	
    	return ResponseEntity.ok().build();
    }
    
	/**
     * Handle auth header
     * @return HTTP 200 if success
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody ResponseEntity<?> postAuthenticate() {
    	logger.debug("POST /authenticate");
    	
    	return ResponseEntity.ok().build();
    }

}
