package org.example.banco.resource;

import java.sql.SQLException;
import java.util.List;

import org.example.banco.exception.BadInfoException;
import org.example.banco.exception.IdNotFoundException;
import org.example.banco.service.ApoliceService;
import org.example.model.Apolice;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/apolice")
public class ApoliceResource {

private ApoliceService service;
	
	public ApoliceResource() throws ClassNotFoundException, SQLException {
		service = new ApoliceService();
	}
	

	@GET
	@Path("/pesquisa")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Apolice> pesquisar(@QueryParam("nome") String pesquisa) throws SQLException{
		return service.pesquisarPorNome(pesquisa);
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Apolice> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}
	

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(service.pesquisar(codigo)).build();
		} catch (IdNotFoundException e) {

			return Response.status(Status.NOT_FOUND).build();
		}
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Apolice apolice, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(apolice);

			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();

			uriBuilder.path(String.valueOf(apolice.getCodigo()));

			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();

			return Response.status(Status.BAD_REQUEST)
								.entity(e.getMessage()).build();
		}
	}

	

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Apolice apolice, @PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
		try {
			apolice.setCodigo(codigo);
			service.atualizar(apolice);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	

	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			service.remover(id);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
