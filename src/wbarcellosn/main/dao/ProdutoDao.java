/**
 * @author winic
 */
package wbarcellosn.main.dao;

import wbarcellosn.main.domain.Cliente;
import wbarcellosn.main.domain.Produto;
import wbarcellosn.main.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao implements IProdutoDao{

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlInsert();
            statement = connection.prepareStatement(sql);
            addParametrosInsert(statement, produto);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            statement = connection.prepareStatement(sql);
            addParametrosUpdate(statement, produto);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
    }

    @Override
    public Produto buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Produto produto = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelect();
            statement = connection.prepareStatement(sql);
            addParametrosSelect(statement, codigo);
            rs =  statement.executeQuery();

            if (rs.next()){
                produto = new Produto();
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                String cd = rs.getString("codigo_pd");
                produto.setId(id);
                produto.setDescricao(descricao);
                produto.setCodigo_pd(cd);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }

        return produto;
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            statement = connection.prepareStatement(sql);
            rs =  statement.executeQuery();

            while (rs.next()){
                produto = new Produto();
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                String cd = rs.getString("codigo_pd");
                produto.setId(id);
                produto.setDescricao(descricao);
                produto.setCodigo_pd(cd);
                listaProdutos.add(produto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
        return listaProdutos;
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            statement = connection.prepareStatement(sql);
            addParametrosDelete(statement, produto);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
    }

    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_produto(id, codigo_pd, descricao) ");
        sb.append("VALUES(nextval('sq_produto'), ?, ?)");
        return sb.toString();
    }

    private void addParametrosInsert(PreparedStatement statement, Produto produto) throws SQLException {
        statement.setString(1, produto.getCodigo_pd());
        statement.setString(2, produto.getDescricao());
    }

    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_produto ");
        sb.append("SET descricao = ?, codigo_pd = ? ");
        sb.append("WHERE id = ?");
        return sb.toString();
    }

    private void addParametrosUpdate(PreparedStatement statement, Produto produto) throws SQLException {
        statement.setString(1, produto.getDescricao());
        statement.setString(2, produto.getCodigo_pd());
        statement.setLong(3, produto.getId());
    }

    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produto ");
        sb.append("WHERE codigo_pd = ?");
        return sb.toString();
    }

    private void addParametrosSelect(PreparedStatement statement, String codigo) throws SQLException {
        statement.setString(1, codigo);
    }

    private String getSqlSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produto");
        return sb.toString();
    }

    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_produto ");
        sb.append("WHERE codigo_pd = ?");
        return sb.toString();
    }

    private void addParametrosDelete(PreparedStatement statement, Produto produto) throws SQLException {
        statement.setString(1, produto.getCodigo_pd());
    }


    private void closeConnection(PreparedStatement statement, Connection connection, ResultSet rs) {
        try{
            if(rs != null && !rs.isClosed()){
                rs.close();
            }

            if(statement != null && !statement.isClosed()){
                statement.close();
            }

            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
