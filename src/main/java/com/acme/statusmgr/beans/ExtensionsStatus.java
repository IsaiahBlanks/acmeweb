package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Decorating class that adds the Server Extensions to the status description
 */
public class ExtensionsStatus extends ServerStatusDecorator {

    public ExtensionsStatus(ServerStatusInterface decoratedStatus) {
        super(decoratedStatus);
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ServerManager.getServerExtensions();
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getContentHeader() {
        return super.getContentHeader();
    }
}
