package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Cidades;
import beans.Estados;
import beans.Fone;
import beans.Pessoa;
import beans.Produto;
import persistencia.CidadesDao;
import persistencia.PessoaDAO;
import persistencia.ProdutoDAO;

@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private Pessoa pessoa = new Pessoa();
    private String filtro = "";
    private Estados estado;
    private List<Estados> estados;
    private Cidades cidade;
    private List<Cidades> cidades;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Pessoa> getListagem() {
        return PessoaDAO.listagem(filtro);
    }

    public String actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (pessoa.getId() == 0) {
            pessoa.setTipo("ROLE_CLIENTE");
            PessoaDAO.inserir(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso",
                    "Inserido com sucesso!"));

        } else {
            PessoaDAO.alterar(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso",
                    "Alterado com sucesso!"));

        }
        return "/admin/lista_pessoa";
    }

    public String actionGravarAdm() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (pessoa.getId() == 0) {
            pessoa.setTipo("ROLE_ADMINISTRATOR");
            PessoaDAO.inserir(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso",
                    "Inserido com sucesso!"));

        } else {
            PessoaDAO.alterar(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso",
                    "Alterado com sucesso!"));

        }
        return "/admin/lista_pessoa";
    }

    public String actionInserir() {
        pessoa = new Pessoa();
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        return "/admin/lista_pessoa";
    }

    public String actionInserirNovoCliente() {
        if (pessoa.getNome() != null) {
            estados = CidadesDao.listar("est_nome");
            cidades = new ArrayList<Cidades>();
        } else {
            pessoa = new Pessoa();
            estados = CidadesDao.listar("est_nome");
            cidades = new ArrayList<Cidades>();
        }
        return "/publico/cadastro_cliente?faces-redirect=true";
    }

    public void actionAlterar(Pessoa p) {
            this.pessoa = p;
            estado = CidadesDao.est(9);
            estados = CidadesDao.listar("est_nome");
            cidades = new ArrayList<Cidades>();     
    }

    public String actionExcluir() {
        PessoaDAO.excluir(pessoa);
        return "/admin/lista_pessoa";

    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Pessoa Selecionada",
                String.valueOf(((Pessoa) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public String actionInserirFone() {
        Fone fone = new Fone();
        fone.setPessoa(pessoa);
        pessoa.getFones().add(fone);
        return "/admin/lista_pessoa";
    }

    public String actionInserirFoneCliente() {
        Fone fone = new Fone();
        fone.setPessoa(pessoa);
        pessoa.getFones().add(fone);
        return "/cliente/lista_pessoa";
    }

    public String actionExcluirFone(Fone f) {
        pessoa.getFones().remove(f);
        return "/admin/lista_pessoa";
    }

    public String actionExcluirFoneCliente(Fone f) {
        pessoa.getFones().remove(f);
        return "/cliente/lista_pessoa";
    }

    public void popular() {
        if (estado != null) {
            CidadesDao cidadesDao = new CidadesDao();
            cidades = cidadesDao.buscaPorCidade(estado.getEst_id());
        } else {
            cidades = new ArrayList<Cidades>();
        }

    }

    public String confirmarSenha() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!filtro.equalsIgnoreCase(pessoa.getSenha())) {
            setFiltro("Repita as senhas corretamente!");
            return "/publico/login?faces-redirect=true";
        }
        return actionInserirNovoCliente();
    }

    public Cidades getCidade() {
        return cidade;
    }

    public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }

    public List<Cidades> getCidades() {
        cidades = new ArrayList<Cidades>();  
        return cidades;
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public List<Estados> getEstados() {
        estados = CidadesDao.listagemSiglaEstados("");
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

}
