package model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	private final ArrayList<Usuario> listaDeUsuarios;
	
	public UsuarioDAO() {
		this.listaDeUsuarios = new ArrayList<>();
	}
	
	public void addUsuario (Usuario usuario) {
		if (usuario != null) {
			this.listaDeUsuarios.add(usuario);
		}
	}
	
	public List<Usuario> listarTodos(Usuario usuario){
		return new ArrayList<>(this.listaDeUsuarios);
	}
	
	public Usuario buscarPorCpf(String cpf) {
		for (Usuario usuario : this.listaDeUsuarios) {
			if (usuario.getCpf().equalsIgnoreCase(cpf)) {
				return usuario;
			}
		}
		return null;
	}

}
