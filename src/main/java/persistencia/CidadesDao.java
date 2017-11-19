package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import beans.Cidades;
import beans.Estados;

public class CidadesDao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<Cidades> listagemCidades(String cid_nome) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Cidades");
		if (cid_nome == null) {
			consulta = sessao.createQuery("from Cidades order by nome");
		} else {
			consulta = sessao
					.createQuery("from Cidades where nome like:parametro order by id");
			consulta.setString("parametro", "%" + cid_nome + "%");
		}
		List lscid = consulta.list();
		sessao.close();

		return lscid;
	}

	public static List<Estados> listagemSiglaEstados(String filtro) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Estados");
		if (filtro == null) {
			consulta = sessao.createQuery("from Estados order by sigla");
		} else {
			consulta = sessao
					.createQuery("from Estados where sigla like:parametro order by id");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List lsest = consulta.list();
		sessao.close();

		return lsest;
	}

	public List<Cidades> buscaPorCidade(int estadoId) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Cidades");
		if (estadoId == 0) {
			consulta = sessao.createQuery("from Cidades order by nome");
		} else {
			consulta = sessao
					.createQuery("from Cidades where estado_id = :parametro order by id");
			consulta.setInteger("parametro", estadoId);
		}
		List lscid = consulta.list();
		sessao.close();

		return lscid;
	}

	@SuppressWarnings("unchecked")
	public static List<Estados> listar(String campoOrdenacao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estados.class);
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Estados> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	

	public static Estados est(int est_id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria consulta = sessao.createCriteria(Estados.class);
            if (est_id != 0) {
                consulta.add(Restrictions.eq("est_id", est_id));
            }
            if (consulta.list().size() == 0) {
                return null;
            } else {
            	Estados es = (Estados) consulta.uniqueResult();
                return (Estados) consulta.uniqueResult();
            }
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }

    }
}

