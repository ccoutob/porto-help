package org.example.banco.resource;

import java.sql.SQLException;
import java.util.List;

import org.example.banco.exception.BadInfoException;
import org.example.banco.exception.IdNotFoundException;
import org.example.banco.service.CargaService;
import org.example.model.Carga;

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

@Path("/carga")
public class CargaResource {
	
private CargaService service;
	
	public CargaResource() throws ClassNotFoundException, SQLException {
		service = new CargaService();
	}

	@GET
	@Path("/pesquisa")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carga> pesquisar(@QueryParam("nome") String pesquisa) throws SQLException{
		return service.pesquisarPorNome(pesquisa);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carga> lista() throws ClassNotFoundException, SQLException {
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
	public Response cadastrar(Carga carga, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(carga);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			
			uriBuilder.path(String.valueOf(carga.getId()));
			
			return Response.created(uriBuilder.build()).build();
			
		} catch (BadInfoException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Carga carga, @PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
		try {
			carga.setId(codigo);
			service.atualizar(carga);
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
