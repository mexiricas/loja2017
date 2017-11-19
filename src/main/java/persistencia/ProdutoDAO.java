package persistencia;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import beans.Produto;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void inserir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(produto);
		t.commit();
		sessao.close();
		
	}
	
	public static void alterar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(produto);
		t.commit();
		sessao.close();
		
	}
	
	public static void excluir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(produto);
		t.commit();
		sessao.close();
		
	}
	
	public static List<Produto> listagem (String filtro) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		List<Produto> lista = null;
		if (filtro.trim().length() == 0) {
			consulta = sessao.createQuery("from Produto order by pro_nome");
					
		}
		else {
			consulta = sessao.createQuery("from Produto where pro_nome like :parametro order by pro_nome");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static Produto pesqId(int valor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Produto where pro_id = :parametro");
		consulta.setInteger("parametro", valor);
		sessao.close();
		return (Produto) consulta.uniqueResult();
		
	}
}
