/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Denis
 */

@Entity
@Table(name = "itens_pedido")
public class ItensPedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipe_id")
    private int ipe_id;
    @Column(name = "ipe_qtde")
    private float ipe_qtde;
    @Column(name = "ipe_valorunit")
    private float ipe_valorUnit;
    @Column(name = "ipe_subtotal")
    private float ipe_subtotal;

    public int getIpe_id() {
        return ipe_id;
    }

    public void setIpe_id(int ipe_id) {
        this.ipe_id = ipe_id;
    }

    public float getIpe_qtde() {
        return ipe_qtde;
    }

    public void setIpe_qtde(float ipe_qtde) {
        this.ipe_qtde = ipe_qtde;
    }

    public float getIpe_valorUnit() {
        return ipe_valorUnit;
    }

    public void setIpe_valorUnit(float ipe_valorUnit) {
        this.ipe_valorUnit = ipe_valorUnit;
    }

    public float getIpe_subtotal() {
        return ipe_subtotal;
    }

    public void setIpe_subtotal(float ipe_subtotal) {
        this.ipe_subtotal = ipe_subtotal;
    }

    @ManyToOne
    @JoinColumn(name = "ped_id")
    private Pedidos ped = new Pedidos();

    public Pedidos getPed() {
        return ped;
    }

    public void setPed(Pedidos ped) {
        this.ped = ped;
    }

    @ManyToOne
    @JoinColumn(name = "pro_id")
    Produto prod = new Produto();

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

}
