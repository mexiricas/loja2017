package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import persistencia.ProdutoDAO;
import beans.Produto;

@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private String filtro = "";
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	public List<Produto> getListagem() {
		return ProdutoDAO.listagem(filtro);
	}
	
	public String actionGravar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (produto.getId() == 0) {
			ProdutoDAO.inserir(produto);
			context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
		}
		else {
			ProdutoDAO.alterar(produto);
			context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso"));
			
		}
		return "/admin/lista_produto";
	}
	
	public String actionInserir() {
		produto = new Produto();
		return "/admin/inserir_produto";
	}
	
	public String actionExcluir() {
		ProdutoDAO.excluir(produto);
		return "/admin/lista_produto";
		
	}
	public void onRowSelect(SelectEvent event){
		FacesMessage msg = new FacesMessage("Produto Selecionado",
				String.valueOf(((Produto) event.getObject()).getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	
	}
	
}
