package org.example.banco.resource;

import java.sql.SQLException;
import java.util.List;

import org.example.banco.exception.BadInfoException;
import org.example.banco.exception.IdNotFoundException;
import org.example.banco.service.VeiculoService;
import org.example.model.Veiculo;

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

@Path("/veiculo")
public class VeiculoResource {

private VeiculoService service;
	
	public VeiculoResource() throws ClassNotFoundException, SQLException {
		service = new VeiculoService();
	}
	
	@GET
	@Path("/pesquisa")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Veiculo> pesquisar(@QueryParam("nome") String pesquisa) throws SQLException{
		return service.pesquisarPorNome(pesquisa);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Veiculo> lista() throws ClassNotFoundException, SQLException {
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
	public Response cadastrar(Veiculo veiculo, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(veiculo);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(veiculo.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Veiculo veiculo, @PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
		try {
			veiculo.setId(codigo);
			service.atualizar(veiculo);
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
