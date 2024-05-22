package wbarcellosn;

import org.junit.Test;
import wbarcellosn.main.dao.ProdutoDao;
import wbarcellosn.main.dao.IProdutoDao;
import wbarcellosn.main.dao.IProdutoDao;
import wbarcellosn.main.dao.ProdutoDao;
import wbarcellosn.main.domain.Produto;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author winic
 */

public class ProdutoTeste {

    private IProdutoDao produtoDao;

    @Test
    public void cadastrarTeste() throws Exception{

        produtoDao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setCodigo_pd("001");
        produto.setDescricao("Produto 1");
        Integer countCadastros = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCadastros);

        Produto produtoDoBanco = produtoDao.buscar("001");
        assertNotNull(produtoDoBanco);
        assertEquals(produto.getCodigo_pd(), produtoDoBanco.getCodigo_pd());
        assertEquals(produto.getDescricao(), produtoDoBanco.getDescricao());

        Integer contadorExclusao = produtoDao.excluir(produtoDoBanco);
        assertEquals(1, (int) contadorExclusao);
    }

    @Test
    public void buscarTeste() throws Exception{

        produtoDao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setCodigo_pd("001");
        produto.setDescricao("Produto 1");
        Integer countCadastros = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCadastros);

        Produto produtoDoBanco = produtoDao.buscar("001");
        assertNotNull(produtoDoBanco);
        assertEquals(produto.getCodigo_pd(), produtoDoBanco.getCodigo_pd());
        assertEquals(produto.getDescricao(), produtoDoBanco.getDescricao());

        Integer contadorExclusao = produtoDao.excluir(produtoDoBanco);
        assertEquals(1, (int) contadorExclusao);
    }

    @Test
    public void buscarTodosTeste() throws Exception {
        produtoDao = new ProdutoDao();

        Produto produto1 = new Produto();
        produto1.setCodigo_pd("001");
        produto1.setDescricao("Produto 1");
        Integer countCad1 = produtoDao.cadastrar(produto1);
        assertEquals(1, (int) countCad1);

        Produto produto2 = new Produto();
        produto2.setCodigo_pd("002");
        produto2.setDescricao("Produto 2");
        Integer countCad2 = produtoDao.cadastrar(produto2);
        assertEquals(1, (int) countCad2);

        List<Produto> lista = produtoDao.buscarTodos();
        assertNotNull(lista);
        assertEquals(2, lista.size());

        int countDel = 0;
        for (Produto produto : lista){
            produtoDao.excluir(produto);
            countDel++;
        }
        assertEquals(lista.size(), countDel);

        lista = produtoDao.buscarTodos();
        assertEquals(lista.size(), 0);
    }

    @Test
    public void updateTeste() throws Exception{

        produtoDao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setCodigo_pd("001");
        produto.setDescricao("Produto 1");
        Integer countCadastros = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCadastros);

        Produto produtoDoBanco = produtoDao.buscar("001");
        assertNotNull(produtoDoBanco);
        assertEquals(produto.getCodigo_pd(), produtoDoBanco.getCodigo_pd());
        assertEquals(produto.getDescricao(), produtoDoBanco.getDescricao());

        produtoDoBanco.setCodigo_pd("002");
        produtoDoBanco.setDescricao("Produto 02");
        Integer countUpdate = produtoDao.atualizar(produtoDoBanco);
        assertEquals(1, (int) countUpdate);

        Produto produtoDoBanco1 = produtoDao.buscar("001");
        assertNull(produtoDoBanco1);

        Produto produtoDoBanco2 = produtoDao.buscar("002");
        assertNotNull(produtoDoBanco2);
        assertEquals(produtoDoBanco.getId(), produtoDoBanco2.getId());
        assertEquals(produtoDoBanco.getCodigo_pd(), produtoDoBanco2.getCodigo_pd());
        assertEquals(produtoDoBanco.getDescricao(), produtoDoBanco2.getDescricao());

        List<Produto> lista = produtoDao.buscarTodos();
        for (Produto cli : lista){
            produtoDao.excluir(cli);
        }

    }

    @Test
    public void excluirTeste() throws Exception{

        produtoDao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setCodigo_pd("001");
        produto.setDescricao("Produto 1");
        Integer countCadastros = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCadastros);

        Produto produtoDoBanco = produtoDao.buscar("001");
        assertNotNull(produtoDoBanco);
        assertEquals(produto.getCodigo_pd(), produtoDoBanco.getCodigo_pd());
        assertEquals(produto.getDescricao(), produtoDoBanco.getDescricao());

        Integer contadorExclusao = produtoDao.excluir(produtoDoBanco);
        assertEquals(1, (int) contadorExclusao);
    }
}
