/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import beans.ItensPedidos;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Denis
 */
public class ItensPedidosDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(ItensPedidos iten) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(iten);
        t.commit();
        sessao.close();
    }
}
