/**
 * @author winic
 */
package wbarcellosn.main.dao;

import wbarcellosn.main.domain.Cliente;
import wbarcellosn.main.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements IClienteDao{
    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlInsert();
            statement = connection.prepareStatement(sql);
            addParametrosInsert(statement, cliente);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            statement = connection.prepareStatement(sql);
            addParametrosUpdate(statement, cliente);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
    }

    @Override
    public Cliente buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelect();
            statement = connection.prepareStatement(sql);
            addParametrosSelect(statement, codigo);
            rs =  statement.executeQuery();

            if (rs.next()){
                cliente = new Cliente();
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cd = rs.getString("codigo");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }

        return cliente;
    }

    @Override
    public List<Cliente> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            statement = connection.prepareStatement(sql);
            rs =  statement.executeQuery();

            while (rs.next()){
                cliente = new Cliente();
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cd = rs.getString("codigo");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
                lista.add(cliente);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
        return lista;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            statement = connection.prepareStatement(sql);
            addParametrosDelete(statement, cliente);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(statement, connection, null);
        }
    }


    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_cliente(id, codigo, nome) ");
        sb.append("VALUES(nextval('sq_cliente'), ?, ?)");
        return sb.toString();
    }

    private void addParametrosInsert(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setString(1, cliente.getCodigo());
        statement.setString(2, cliente.getNome());
    }

    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_cliente ");
        sb.append("SET nome = ?, codigo = ? ");
        sb.append("WHERE id = ?");
        return sb.toString();
    }

    private void addParametrosUpdate(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getCodigo());
        statement.setLong(3, cliente.getId());
    }

    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_cliente ");
        sb.append("WHERE codigo = ?");
        return sb.toString();
    }

    private void addParametrosSelect(PreparedStatement statement, String codigo) throws SQLException {
        statement.setString(1, codigo);
    }

    private String getSqlSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_cliente");
        return sb.toString();
    }

    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_cliente ");
        sb.append("WHERE codigo = ?");
        return sb.toString();
    }

    private void addParametrosDelete(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setString(1, cliente.getCodigo());
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
