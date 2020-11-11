package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach
 *
 *
 *
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();


    /**
     * Basic server status return method for getting the server status without details
     * @param name Who requested this status. Default is Anonymous
     * @return ServerStatulImpl object with no decorators
     */
    @RequestMapping("/status")
    public ServerStatusInterface getServerStatus(@RequestParam(value="name", defaultValue="Anonymous") String name) {
        return new ServerStatusImpl(counter.incrementAndGet(),
                String.format(template, name));
    }

    /**
     * Decorated server status return method which decorates the details listed in the details parameter
     * onto the ServerStatusImpl class.
     * @param name Who requested this status. Default is Anonymous
     * @param details List of details to return about the server. Empty list throws an exception. Valid
     *                details are 'operations', 'extensions', 'memory'. Invalid details throws an exception
     * @return
     */
    @RequestMapping("/status/detailed")
    public ServerStatusInterface getDetailedServerStatus(@RequestParam(value="name", defaultValue="Anonymous") String name,
                                        @RequestParam(value="details", required = false) List<String> details) {
        System.out.println("***DEBUG INFO ***" + details);
        ServerStatusInterface status = new ServerStatusImpl(counter.incrementAndGet(),
                String.format(template, name));
        if(details != null) {
            for (String s : details) {
                //for each detail decorate the appropriate class
                if (s.equals("operations")) {
                    status = new OperationsStatus(status);
                } else if (s.equals("extensions")) {
                    status = new ExtensionsStatus(status);
                } else if (s.equals("memory")) {
                    status = new MemoryStatus(status);
                } else {
                    throw new InvalidDetailException();
                }
            }
        } else {
                throw new EmptyDetailListException();
            }
        return status;
    }


}
