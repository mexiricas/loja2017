/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import beans.Cidades;
import beans.Estados;
import beans.FormaPgto;
import beans.ItensPedidos;
import beans.Pedidos;
import beans.Pessoa;
import beans.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Denis
 */
@SessionScoped
@ManagedBean(name = "carrinhoCtrl")
public class CarrinhoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private Pessoa pessoa = new Pessoa();
    private Produto prod = new Produto();
    private FormaPgto formaPgto;
    private Pedidos ped = new Pedidos();
    private ItensPedidos iten = new ItensPedidos();

    private List<Produto> lsprod = new ArrayList<>();
    private List<FormaPgto> forpgt;
    private List<Integer> lsint = new ArrayList<>();
    private List<Cidades> cidades;
    private List<Estados> estados;

    private String img_nome = "";
    private int qdtItens = 0;
    private int qdtTotal = 0;
    private int validade = 0;
    private float subtotal = 0;
    private String msg = "";
    private float somaDosProdutos = 0;

    public String inserirProd(Produto p) {

        somaDosProdutos = somaDosProdutos + p.getPreco();
        lsprod.add(p);
        this.qdtTotal = lsprod.size();
        this.subtotal = somaDosProdutos;
        prod = p;

        return "/publico/carrinho?faces-redirect=true";
    }

    public String verificarStatus() {
        String usu = getUsuarioLogado().toString();
        if(usu != null){
            return"/publico/lista_compra?faces-redirect=true";  
        }
        return "/publico/login?faces-redirect=true";
    }

    public String actionIntemsRemover(Produto p) {
        prod = p;
        this.qdtTotal = this.qdtTotal - 1;
        this.subtotal = subtotal - p.getPreco();
        lsprod.remove(p);

        return "/publico/lista_compra?faces-redirect=true";

    }

    public String getUsuarioLogado() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    //GETTER E SETTERS
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Pedidos getPed() {
        return ped;
    }

    public void setPed(Pedidos ped) {
        this.ped = ped;
    }

    public ItensPedidos getIten() {
        return iten;
    }

    public void setIten(ItensPedidos iten) {
        this.iten = iten;
    }

    public List<Produto> getLsprod() {
        return lsprod;
    }

    public void setLsprod(List<Produto> lsprod) {
        this.lsprod = lsprod;
    }

    public List<FormaPgto> getForpgt() {
        return forpgt;
    }

    public void setForpgt(List<FormaPgto> forpgt) {
        this.forpgt = forpgt;
    }

    public List<Integer> getLsint() {
        return lsint;
    }

    public void setLsint(List<Integer> lsint) {
        this.lsint = lsint;
    }

    public List<Cidades> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    public List<Estados> getEstados() {
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    public String getImg_nome() {
        return img_nome;
    }

    public void setImg_nome(String img_nome) {
        this.img_nome = img_nome;
    }

    public int getQdtItens() {
        return qdtItens;
    }

    public void setQdtItens(int qdtItens) {
        this.qdtItens = qdtItens;
    }

    public int getQdtTotal() {
        return qdtTotal;
    }

    public void setQdtTotal(int qdtTotal) {
        this.qdtTotal = qdtTotal;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public float getSomaDosProdutos() {
        return somaDosProdutos;
    }

    public void setSomaDosProdutos(float somaDosProdutos) {
        this.somaDosProdutos = somaDosProdutos;
    }

}
