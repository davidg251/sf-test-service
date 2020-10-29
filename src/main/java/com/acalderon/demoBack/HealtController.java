package com.acalderon.demoBack;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/health")
public class HealtController {

    /**
     * Obtains the http code of the health of the service.
     *
     * @return http code of the health of the service.
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getServiceHealth(HttpServletResponse response) {
        return new ResponseEntity<>("{\"status\": \"Healthy\"}", HttpStatus.OK);
    }
}