package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

import java.util.List;

/**
 * A POJO that represents Server Status and can be used to generate JSON for that status
 * This class provides the basic implementation of a ServerStatus which can then be decorated
 * by the ServerStatusDecorator with other ServerStatus types
 */
public class ServerStatusImpl implements ServerStatusInterface{

    private long id;
    private String contentHeader;
    private String statusDesc = "Unknown";

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     * This class must return a pretty, english-like representation of the server status.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public ServerStatusImpl(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        // Obtain current status of server
        this.statusDesc = "Server is " + ServerManager.getCurrentServerStatus();
    }

    public ServerStatusImpl() {

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getContentHeader() { return contentHeader; }

    @Override
    public String getStatusDesc() {
        return statusDesc;
    }
}
