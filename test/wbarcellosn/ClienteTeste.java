package wbarcellosn;

import org.junit.Test;
import wbarcellosn.main.dao.ClienteDao;
import wbarcellosn.main.dao.IClienteDao;
import wbarcellosn.main.domain.Cliente;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author winic
 */

public class ClienteTeste {

    private IClienteDao clienteDao;

    @Test
    public void cadastrarTeste() throws Exception{

        clienteDao = new ClienteDao();

        //Criando o registro no banco
        Cliente cliente = new Cliente();
        cliente.setCodigo("1213");
        cliente.setNome("Winicius");
        Integer countCadastros = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCadastros);

        //Verificando se o registro realmente foi feito no banco
        Cliente clienteDoBanco = clienteDao.buscar("1213");
        assertNotNull(clienteDoBanco);
        assertEquals(cliente.getCodigo(), clienteDoBanco.getCodigo());
        assertEquals(cliente.getNome(), clienteDoBanco.getNome());

      //Excluindo o registro do banco (boa prática)
        Integer contadorExclusao = clienteDao.excluir(clienteDoBanco);
        assertEquals(1, (int) contadorExclusao);
    }

    @Test
    public void buscarTeste() throws Exception{

        clienteDao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("1213");
        cliente.setNome("Winicius");
        Integer countCadastros = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCadastros);

        Cliente clienteDoBanco = clienteDao.buscar("1213");
        assertNotNull(clienteDoBanco);
        assertEquals(cliente.getCodigo(), clienteDoBanco.getCodigo());
        assertEquals(cliente.getNome(), clienteDoBanco.getNome());

        Integer contadorExclusao = clienteDao.excluir(clienteDoBanco);
        assertEquals(1, (int) contadorExclusao);
    }

    @Test
    public void buscarTodosTeste() throws Exception {
        clienteDao = new ClienteDao();

        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("1213");
        cliente1.setNome("Winicius");
        Integer countCad1 = clienteDao.cadastrar(cliente1);
        assertEquals(1, (int) countCad1);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("1214");
        cliente2.setNome("Marcela");
        Integer countCad2 = clienteDao.cadastrar(cliente2);
        assertEquals(1, (int) countCad2);

        List<Cliente> lista = clienteDao.buscarTodos();
        assertNotNull(lista);
        assertEquals(2, lista.size());

        int countDel = 0;
        for (Cliente cliente : lista){
            clienteDao.excluir(cliente);
            countDel++;
        }
        assertEquals(lista.size(), countDel);

        lista = clienteDao.buscarTodos();
        assertEquals(lista.size(), 0);
    }

    @Test
    public void updateTeste() throws Exception{

        clienteDao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("1213");
        cliente.setNome("Winicius");
        Integer countCadastros = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCadastros);

        Cliente clienteDoBanco = clienteDao.buscar("1213");
        assertNotNull(clienteDoBanco);
        assertEquals(cliente.getCodigo(), clienteDoBanco.getCodigo());
        assertEquals(cliente.getNome(), clienteDoBanco.getNome());

        clienteDoBanco.setCodigo("1215");
        clienteDoBanco.setNome("Winicius Barcellos");
        Integer countUpdate = clienteDao.atualizar(clienteDoBanco);
        assertEquals(1, (int) countUpdate);

        Cliente clienteDoBanco1 = clienteDao.buscar("1213");
        assertNull(clienteDoBanco1);

        Cliente clienteDoBanco2 = clienteDao.buscar("1215");
        assertNotNull(clienteDoBanco2);
        assertEquals(clienteDoBanco.getId(), clienteDoBanco2.getId());
        assertEquals(clienteDoBanco.getCodigo(), clienteDoBanco2.getCodigo());
        assertEquals(clienteDoBanco.getNome(), clienteDoBanco2.getNome());

        List<Cliente> lista = clienteDao.buscarTodos();
        for (Cliente cli : lista){
            clienteDao.excluir(cli);
        }

    }

    @Test
    public void excluirTeste() throws Exception{

        clienteDao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("1213");
        cliente.setNome("Winicius");
        Integer countCadastros = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCadastros);

        Cliente clienteDoBanco = clienteDao.buscar("1213");
        assertNotNull(clienteDoBanco);
        assertEquals(cliente.getCodigo(), clienteDoBanco.getCodigo());
        assertEquals(cliente.getNome(), clienteDoBanco.getNome());

        Integer contadorExclusao = clienteDao.excluir(clienteDoBanco);
        assertEquals(1, (int) contadorExclusao);
    }
}
