package com.bruno.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.projetofinal.dao.UsuarioDAO;
import com.bruno.projetofinal.model.Usuario;

@CrossOrigin("*")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@GetMapping("/usuarios")
	public ArrayList<Usuario> recuperarTodos() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		lista = (ArrayList<Usuario>) dao.findAll();
		return lista;
	}

//	public Usuario logarUsuario(@RequestBody Usuario usarEmailSenha) {
//		Usuario res = dao.findByEmailAndSenha(usarEmailSenha.getEmail(), usarEmailSenha.getSenha());
//		if ( res != null ) {
//			if (res.getSenha().equals(userEmailSenha.getSenha)) {
//				res.setSenha("******************");
//				return ResponseEntity.ok(res);
//			}
//			else { return ResponseEntity.status(401).build(); }
//		} else { return ResponseEntity.status(404).build();	}
//	public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario usarEmailSenha) {
//		Usuario res = dao.findByEmail(usarEmailSenha.getEmail());
//		if (res != null) {
//			if (res.getSenha().equals(usarEmailSenha.getSenha())) {
//				res.setSenha("******************");
//				return ResponseEntity.ok(res);
//			} else {
//				return ResponseEntity.status(401).build();
//			}
//		} else {
//			return ResponseEntity.status(404).build();
//		}
//	}
	@PostMapping("/login")
	public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario usarEmailSenha) {
		Usuario res = dao.findByEmailOrRacf(usarEmailSenha.getEmail(), usarEmailSenha.getEmail());
		//System.out.println("senha criptografada " + res.getSenha() + " " + usarEmailSenha.getSenha().hashCode());
		if (res != null) {
			if (res.getSenha().equals(Integer.toString(usarEmailSenha.getSenha().hashCode()))) {
				res.setSenha("******************");
				return ResponseEntity.ok(res);
			} else {
				return ResponseEntity.status(401).build(); }
		} else { return ResponseEntity.status(404).build(); }
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario recuperarPorId(@PathVariable int id) {
		Usuario user = dao.findById(id).orElse(null);
		return user;
	}

	@PostMapping("/usuarios/email")
	public Usuario recuperarPorEmail(@RequestBody Usuario usarEmail) {
		Usuario email = dao.findByEmail(usarEmail.getEmail());
		return email;
	}

}
