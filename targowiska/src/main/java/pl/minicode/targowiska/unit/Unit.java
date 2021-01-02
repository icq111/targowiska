package pl.minicode.targowiska.unit;

import javax.persistence.Entity;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
public class Unit extends BasicEntity {

	private String name;
	private String shortName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
