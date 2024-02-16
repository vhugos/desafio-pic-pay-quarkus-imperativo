package br.com.cpqd.picpay.clients;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey="authroizer-api")
public interface TransactionAuthorizerClient {
    @POST
    @Path("/createDevice")
    String createCamera(String request);
    @POST
    @Path("/deleteCamera")
    String deleteCamera(String request);
    @POST
    @Path("/updateCamera")
    void updateCamera(String json);


    @POST
    @Path("/createHardwareDevice")
    String createHardwareDevice(String request);
    @POST
    @Path("/deleteHardware")
    String deleteHardware(String request);
    @POST
    @Path("/updateHardware")
    void updateHardware(String json);
}
