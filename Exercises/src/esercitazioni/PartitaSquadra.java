package esercitazioni;

public class PartitaSquadra {
	private String nome;
	private int goal;
	
	public PartitaSquadra(String nome, int goal) {
		setNome(nome);
		setGoal(goal);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	
}
