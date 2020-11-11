package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Decorating class that adds the Server Memory to the status description
 */
public class MemoryStatus extends ServerStatusDecorator{

    public MemoryStatus(ServerStatusInterface decoratedStatus) {
        super(decoratedStatus);
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ServerManager.getServerMemory();
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
