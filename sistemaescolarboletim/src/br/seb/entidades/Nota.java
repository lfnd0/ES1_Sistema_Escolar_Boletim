package br.seb.entidades;

public class Nota {

	private double bimestre1;
	private double bimestre2;
	private double bimestre3;
	private double bimestre4;
	private double reavaliacao1;
	private double reavaliacao2;
	
	public Nota() {}
	
	public Nota(double bimestre1, double bimestre2, double bimestre3, double bimestre4, double reavaliacao1, double reavaliacao2) {
		this.bimestre1 = bimestre1;
 		this.bimestre2 = bimestre2;
		this.bimestre3 = bimestre3;
		this.bimestre4 = bimestre4;
		this.reavaliacao1 = reavaliacao1;
		this.reavaliacao2 = reavaliacao2;
	}

	public double getBimestre1() {
		return bimestre1;
	}

	public void setBimestre1(double bimestre1) {
		this.bimestre1 = bimestre1;
	}

	public double getBimestre2() {
		return bimestre2;
	}

	public void setBimestre2(double bimestre2) {
		this.bimestre2 = bimestre2;
	}

	public double getBimestre3() {
		return bimestre3;
	}

	public void setBimestre3(double bimestre3) {
		this.bimestre3 = bimestre3;
	}

	public double getBimestre4() {
		return bimestre4;
	}

	public void setBimestre4(double bimestre4) {
		this.bimestre4 = bimestre4;
	}

	public double getReavaliacao1() {
		return reavaliacao1;
	}

	public void setReavaliacao1(double reavaliacao1) {
		this.reavaliacao1 = reavaliacao1;
	}

	public double getReavaliacao2() {
		return reavaliacao2;
	}

	public void setReavaliacao2(double reavaliacao2) {
		this.reavaliacao2 = reavaliacao2;
	}

	@Override
	public String toString() {
		return "Bimestre 1: " + this.bimestre1 + ", Bimestre 2: " + this.bimestre2 + ", Bimestre 3: " + this.bimestre3 + ", Bimestre 4: "
				+ this.bimestre4 + ", Reavaliacao 1: " + this.reavaliacao1 + ", Reavaliacao 2: " + this.reavaliacao2 + "\n";
	}
	
	
}