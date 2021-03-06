package com.example.ficha8.model;


import java.util.List;

import javax.persistence.*;

import com.example.ficha8.dto.SimpleResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Andar")
public class Andar {
	
	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "centro_comercial_id", nullable = false)
    private CentroComercial centro_comercial;
    
    @OneToMany(mappedBy = "andar")
	private List<Loja> lojas;
    
	private int numero_andar;
	private int numero_max_lojas;
	
	public Long getId() {
		return id;
	}
	
	public int getNumero_andar() {
		return numero_andar;
	}

	public void setNumero_andar(int numero_andar) {
		this.numero_andar = numero_andar;
	}

	public int getNumero_max_lojas() {
		return numero_max_lojas;
	}

	public void setNumero_max_lojas(int numero_max_lojas) {
		this.numero_max_lojas = numero_max_lojas;
	}

	public CentroComercial getCentro_comercial() {
		return centro_comercial;
	}

	public void setCentro_comercial(CentroComercial centro_comercial) {
		this.centro_comercial = centro_comercial;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	@Override
	public String toString() {
		return "Andar [id=" + id + ", numero_andar=" + numero_andar + ", numero_max_lojas=" + numero_max_lojas + "]";
	}

    public void addLoja(Andar andar, Loja loja){
    	SimpleResponse sr = new SimpleResponse();
        if(lojas.size() == andar.getNumero_max_lojas()) {
        	sr.setAsError("Não é possível acrescentar mais lojas. O número máximo de lojas para este andar foi antigido.");
        }else{
            lojas.add(loja);
        }
    }

}