package beans;

import javax.persistence.*;

@Entity
@Table(name = "cidades")
public class Cidades {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int cid_id;
	@Column(name = "nome", length = 120, nullable = true)
	private String cid_nome;

	public void setCid_id(int cid_id) {
		this.cid_id = cid_id;
	}

	public String getCid_nome() {
		return cid_nome;
	}

	public void setCid_nome(String cid_nome) {
		this.cid_nome = cid_nome;
	}

	public int getCid_id() {
		return cid_id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cid_nome;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_id", nullable = false)
	private Estados estados;

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

}
