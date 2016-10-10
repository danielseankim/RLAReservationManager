package com.vmware.q3team7.models;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author kdaniel
 *
 */
@XmlRootElement
public class Reservation {
    private String name;
    private Calendar startDate;
    private Calendar endDate;
    private String serverName;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the startDate
     */
    public Calendar getStartDate() {
        return startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    /**
     * @return the endDate
     */
    public Calendar getEndDate() {
        return endDate;
    }
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }
    /**
     * @param serverName the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

}
