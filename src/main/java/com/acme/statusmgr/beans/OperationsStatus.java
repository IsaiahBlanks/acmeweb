package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Decorating class that adds the Server Operations to the status description
 */
public class OperationsStatus extends ServerStatusDecorator{

    public OperationsStatus(ServerStatusInterface decoratedStatus) {
        super(decoratedStatus);
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ServerManager.getServerOperations();
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
