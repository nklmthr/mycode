package org.nklmthr.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table
public class Institution {

    @JsonProperty
    @Id    
    private String institutionId;


    @JsonProperty
    private String name;

    public Institution() {
    }

    public Institution(String institution) {
        this.name=institution;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	@Override
    public String toString() {
        return "Institution{" +
                "institutionId=" + institutionId +
                ", name='" + name + '\'' +
                '}';
    }
}
