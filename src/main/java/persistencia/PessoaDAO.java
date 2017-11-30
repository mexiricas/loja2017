package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Pessoa;

public class PessoaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(Pessoa pessoa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(pessoa);
        t.commit();
        sessao.close();

    }

    public static void alterar(Pessoa pessoa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.update(pessoa);
        t.commit();
        sessao.close();

    }

    public static void excluir(Pessoa pessoa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.delete(pessoa);
        t.commit();
        sessao.close();

    }

    public static List<Pessoa> listagem(String filtro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        List<Pessoa> lista = null;
        if (filtro.trim().length() == 0) {
            consulta = sessao.createQuery("from Pessoa order by pes_id");

        } else {
            consulta = sessao.createQuery("from Pessoa "
                    + "where pes_nome like :parametro order by pes_id");
            consulta.setString("parametro", "%" + filtro + "%");
        }
        lista = consulta.list();
        sessao.close();
        return lista;
    }

    public static Pessoa pesqId(int valor) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta = sessao.createQuery("from Pessoa where pes_id = :parametro");
        consulta.setInteger("parametro", valor);
        sessao.close();
        return (Pessoa) consulta.uniqueResult();
    }

    public static Pessoa pesqEmail(String filtro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta = sessao.createQuery("from Pessoa where pes_email = :parametro");
        consulta.setString("parametro", filtro);
        return (Pessoa) consulta.uniqueResult();

    }
}
