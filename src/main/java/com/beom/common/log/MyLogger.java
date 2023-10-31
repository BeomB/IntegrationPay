package com.beom.common.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class MyLogger {

    private String uuid;

    private String requestURL;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message)
    {
        log.info("[{}] [{}] {}" ,uuid,requestURL,message );
    }
}
