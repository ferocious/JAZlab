package com.example.zad2.domain;

public class AdresData {

	private int id;
	private String typAdresu;
	private String wojewodztwo;
	private String miasto;
	private String kodPocztowy;
	private String ulica;
	private String nrDomuMieszkania;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypAdresu() {
		return typAdresu;
	}

	public void setTypAdresu(String typAdresu) {
		this.typAdresu = typAdresu;
	}

	public String getWojewodztwo() {
		return wojewodztwo;
	}

	public void setWojewodztwo(String wojewodztwo) {
		this.wojewodztwo = wojewodztwo;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getNrDomuMieszkania() {
		return nrDomuMieszkania;
	}

	public void setNrDomuMieszkania(String nrDomuMieszkania) {
		this.nrDomuMieszkania = nrDomuMieszkania;
	}

}
