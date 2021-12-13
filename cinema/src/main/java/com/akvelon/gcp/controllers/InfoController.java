package com.akvelon.gcp.controllers;


import com.akvelon.gcp.bean.Version;
import com.akvelon.gcp.services.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrey Kustov on 12.12.2021
 * controller  information
 */
@Api(value = "Controller info", description = "Info service")
@RestController
@RequestMapping("/api/info")
public class InfoController {


    @Autowired
    private InfoService infoService;

    /**
     * version
     */
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ApiOperation(value = "get version", notes = "Method get version  of module")
    public Version getVersion() {
        return infoService.getVersionObj();
    }


}
