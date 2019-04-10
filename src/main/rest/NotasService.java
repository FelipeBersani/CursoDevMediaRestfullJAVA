package main.rest;

import main.DAO.NotaDAO;
import main.entidade.Nota;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/notas")
public class NotasService {

    private NotaDAO notaDAO;

    @PostConstruct
    private void init(){
        notaDAO = new NotaDAO();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Nota> listarNotas(){

        List<Nota> lista = new ArrayList<>();
        Nota nota = new Nota();
        nota.setDescricao("Descricao");
        nota.setTitulo("Titulo");
        nota.setId(1);
        lista.add(nota);

        try {
//            lista = Optional.of(notaDAO.listarNotas());
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
