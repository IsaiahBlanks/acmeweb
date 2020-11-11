package com.acme.statusmgr.beans;

public abstract class ServerStatusDecorator implements ServerStatusInterface {
    private ServerStatusInterface statusInterface;

    public ServerStatusDecorator(ServerStatusInterface decoratedStatus) {
        statusInterface = decoratedStatus;
    }

    @Override
    public String getStatusDesc() {
        return statusInterface.getStatusDesc();
    }

    @Override
    public long getId() {
        return statusInterface.getId();
    }

    @Override
    public String getContentHeader() {
        return statusInterface.getContentHeader();
    }
}
