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
	
	public String toString() {

        return "\nNumero da Conta: " + this.getNumeroConta() +
        		"\nAgencia: "+this.getAgencia()+
                "\nCliente: " + this.pessoa.getNome() +
                "\nCPF: " + this.pessoa.getCpf() +
                "\n" ;
    }
	
	public void depositar(Double valor) {
		
		if(valor > 0) {
			this.saldo = getSaldo() + valor;
			System.out.println("Seu depósito foi realizado com sucesso!");
		}else {
			System.out.println("Não foi possível realizar o depósito.");
		}
	}
	
	public void sacar(Double valor) {
		
		if(valor > 0 && this.getSaldo()>= valor) {
			this.saldo = getSaldo() - valor;
			System.out.println("Saque realizado com sucesso!");
		}else {
			System.out.println("Não foi possivel realizar o Saque!");
		}
		
	}
	
	public void transferir(Conta contaDestinatario, Double valor) {
		
		if(valor > 0 && this.getSaldo() >= valor) {
			this.saldo = this.getSaldo() - valor;
			contaDestinatario.saldo = contaDestinatario.getSaldo() + valor;
			System.out.println("Transferência  realizado com sucesso!");
		}else {
			System.out.println("Não foi possível realizar a tranferência");
		}
		
	}
}
