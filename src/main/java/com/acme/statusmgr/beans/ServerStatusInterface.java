package com.acme.statusmgr.beans;

/**
 * Interface which is implemented by various ServerStatus decorators
 */
public interface ServerStatusInterface {
    String getStatusDesc();

    long getId();

    String getContentHeader();
}
