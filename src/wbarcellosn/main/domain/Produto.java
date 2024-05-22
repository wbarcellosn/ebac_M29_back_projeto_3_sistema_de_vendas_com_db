/**
 * @author winic
 */
package wbarcellosn.main.domain;

public class Produto {

    private Long id;
    private String codigo_pd;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo_pd() {
        return codigo_pd;
    }

    public void setCodigo_pd(String codigo_pd) {
        this.codigo_pd = codigo_pd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
