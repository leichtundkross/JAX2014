package com.exxeta.jax.speakerapp.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = Speaker.QUERY_ALL_SPEAKERS, query = "SELECT s FROM Speaker s"),
		@NamedQuery(name = Speaker.QUERY_SPEAKER_BY_NAME, query = "SELECT s FROM Speaker s where s.name = :name") })
public class Speaker {

	public static final String QUERY_ALL_SPEAKERS = "Speaker.all";

	public static final String QUERY_SPEAKER_BY_NAME = "Speaker.byName";

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String company;

	public Speaker() {
		super();
	}

	public Speaker(String name, String company) {
		this.name = name;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speaker other = (Speaker) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Speaker [name=" + name + ", company=" + company + "]";
	}
}
