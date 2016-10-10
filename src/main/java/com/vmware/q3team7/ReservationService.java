package com.vmware.q3team7;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.vmware.q3team7.models.Reservation;
import com.vmware.q3team7.util.KafkaUtil;
import com.vmware.q3team7.util.MarshalUtil;

/**
 * @author kdaniel
 *
 */

@Path("/reservations")
public class ReservationService {
    final static Logger logger = Logger.getLogger(ReservationService.class);
    final private String TOPIC = "reservation";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response defaultGET(){
        logger.debug("Invalid service path is hit - returning 404 message.");
        return Response.status(404).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void getReservation(@PathParam("id") String reservId) throws WebApplicationException {
        // TODO
        KafkaUtil ku = KafkaUtil.getKafkaUtil();
        
    }

    @POST
    @Path("/")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void createReservation(Reservation reserv) {
        // TODO check if this is a repeat call
        // TODO get the server data from blob service and check if valid 
        // TODO validate the reservation request

        // for now, just write to kafka
        KafkaUtil ku = KafkaUtil.getKafkaUtil();
        String key = reserv.getName();
        String value = MarshalUtil.marshal(reserv);
        ku.sendRecord(TOPIC, key, value);
    }
}
