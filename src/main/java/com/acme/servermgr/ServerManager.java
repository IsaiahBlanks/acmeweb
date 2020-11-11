package com.acme.servermgr;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project.
 * Treat this as a 'black box' that gives back indicators like up, running etc for
 * various 'services' that are being managed.
 */
public class ServerManager {

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    static public String getCurrentServerStatus() {
        return "up";  // The server is up
    }

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    static public Boolean isOperatingNormally()
    {
        return true;
    }

    /**
     * Get the status of the server operations
     * @return String detailing the nature of the server operations
     */
    static public String getServerOperations() {
        return ", and is operating normally";
    }

    /**
     * Get the extensions the server is using
     * @return String listing the extensions being used by the server
     */
    static public String getServerExtensions() {
        return ", and is using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }

    /**
     * Get the server memory status
     * @return String indicating how much memory the server is using
     */
    static public String getServerMemory() {
        return ", and its memory is running low";
    }
}
