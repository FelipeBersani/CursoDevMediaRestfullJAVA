package main.JAVA.DAO;

import main.JAVA.config.BDConfig;
import main.JAVA.entidade.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    public List<Nota> listarNotas() throws Exception{
        List<Nota> lista = new ArrayList();

        Connection conexao = BDConfig.getConnection();

        String sql = "SELECT * FROM TB_NOTA";

        PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            Nota nota = new Nota();
            nota.setId(rs.getInt("ID"));
            nota.setTitulo(rs.getString("TITULO"));
            nota.setDescricao(rs.getString("DESCRICAO"));

            lista.add(nota);
        }

        return lista;
    }


    public Nota buscarNotaPorId(int idNota) throws Exception{
        Nota nota = null;

        Connection conexao = BDConfig.getConnection();

        String sql = "SELECT * FROM TB_NOTA WHERE ID_NOTE = ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idNota);
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            nota.setId(rs.getInt("ID"));
            nota.setTitulo(rs.getString("TITULO"));
            nota.setDescricao(rs.getString("DESCRICAO"));
        }

        return nota;
    }


    public void addNota (Nota nota) throws SQLException, ClassNotFoundException {
        Connection conexao = BDConfig.getConnection();

        String sql = "INSERT INTO TB_NOTA (TITULO, DESCRICAO) VALUES (?,?)";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, nota.getTitulo());
        statement.setString(2, nota.getDescricao());
        statement.execute();

    }

    public void editarNota(Nota nota) throws SQLException, ClassNotFoundException {
        Connection conexao = BDConfig.getConnection();

        String sql = "UPDATE TB_NOTA SET TITULO=?, DESCRICAO=?, WHERE ID_NOTA = ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, nota.getTitulo());
        statement.setString(2, nota.getDescricao());
        statement.setInt(3, nota.getId());
        statement.execute();

    }

    public void removerNota(Nota nota) throws SQLException, ClassNotFoundException {
        Connection conexao = BDConfig.getConnection();

        String sql = "DELETE TB_NOTA WHERE ID_NOTA = ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, nota.getId());
        statement.execute();

    }







}


















