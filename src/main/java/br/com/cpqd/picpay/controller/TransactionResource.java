package br.com.cpqd.picpay.controller;


import br.com.cpqd.picpay.dto.transaction.TransactionDto;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.service.CreateTransactionService;
import br.com.cpqd.picpay.service.TransactionServiceValidator;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/transaction")
public class TransactionResource {

    @Inject
    CreateTransactionService createTransactionService;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTransaction(TransactionDto dto){
        return Response.status(Status.CREATED).entity(createTransactionService.createTransaction(dto)).build();
    }
/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUser(Long id){
        return Response.status(Status.OK).entity(findUserService.findUserById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public Response getUsers(){
        return Response.status(Status.OK).entity(findAllUsersService.findUsersById()).build();
    }
*/
}
