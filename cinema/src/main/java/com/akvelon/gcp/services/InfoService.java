package com.akvelon.gcp.services;

import com.akvelon.gcp.bean.Version;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InfoService {

    @Resource
    BuildProperties buildProperties;

    /**
     * get version obj
     * @return
     */
    public Version getVersionObj(){
        return new Version(getVersion(), buildProperties.getArtifact());
    }

    public String getVersion() {
        return buildProperties.getVersion();
    }

}
