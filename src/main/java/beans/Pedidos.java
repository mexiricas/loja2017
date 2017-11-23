/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Denis
 */
@Entity
@Table(name = "pedido")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private int ped_id;
    @Column(name = "ped_dataEmissao", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ped_dataEmissao;
    @Column(name = "ped_status", length = 20, nullable = true)
    private String ped_status;
    @Column(name = "ped_dataAutorizacao", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ped_dataAutorizacao;
    @Column(name = "ped_total", nullable = true)
    private float ped_total;
    @Column(name = "ped_desconto", nullable = true)
    private float ped_desconto;

    public int getPed_id() {
        return ped_id;
    }

    public void setPed_id(int ped_id) {
        this.ped_id = ped_id;
    }

    public Date getPed_dataEmissao() {
        return ped_dataEmissao;
    }

    public void setPed_dataEmissao(Date ped_dataEmissao) {
        this.ped_dataEmissao = ped_dataEmissao;
    }

    public String getPed_status() {
        return ped_status;
    }

    public void setPed_status(String ped_status) {
        this.ped_status = ped_status;
    }

    public Date getPed_dataAutorizacao() {
        return ped_dataAutorizacao;
    }

    public void setPed_dataAutorizacao(Date ped_dataAutorizacao) {
        this.ped_dataAutorizacao = ped_dataAutorizacao;
    }

    public float getPed_total() {
        return ped_total;
    }

    public void setPed_total(float ped_total) {
        this.ped_total = ped_total;
    }

    public float getPed_desconto() {
        return ped_desconto;
    }

    public void setPed_desconto(float ped_desconto) {
        this.ped_desconto = ped_desconto;
    }

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @ManyToOne
    @JoinColumn(name = "fpg_id")
    private FormaPgto formaPgto;

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }
}
