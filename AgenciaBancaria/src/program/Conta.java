package program;

public class Conta {

	private static int contadorContas = 1020;
	
	private int numeroConta;
	private String agencia;
	private Pessoa pessoa;
	private Double saldo = 0.0;
	
	public Conta( Pessoa pessoa) {
		this.numeroConta = contadorContas;
		this.pessoa = pessoa;
		contadorContas += 1;
	
	}
	
	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	 public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
}
