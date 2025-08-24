package com.libro.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libro.demo.entity.Usuario;
import com.libro.demo.service.RolService;
import com.libro.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session , RedirectAttributes redirectAttributes) {
	    session.invalidate(); 
	    redirectAttributes.addFlashAttribute("sessionClosed", true); // Activa mensaje en login
	    return "redirect:/"; // Redirige al login
	}

	@GetMapping("/")
    public String login(){
        return "login";
    }
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        Usuario userDto = new Usuario();
        model.addAttribute("usuario",userDto); //model object is used to store data that is entered from form.
        return "register";
    }
	@PostMapping("/register/save")
    public String registration(@ModelAttribute Usuario usuario, BindingResult result,Model model){ //Model attribute is used to extract model object which is a form data.


        Usuario existingUser = usuarioService.buscarByUsuario(usuario.getUsername()); //checking if entered email already exists or not.
         if(usuario.getNombres()==null || usuario.getNombres().isEmpty()){
            result.rejectValue("nombres",null,"Ingresar nombres");
        }
        
         if(usuario.getApellidos()==null || usuario.getApellidos().isEmpty()){
	            result.rejectValue("apellidos",null,"Ingresar apellidos");
	        }
        
         if(usuario.getUsername()==null || usuario.getUsername().isEmpty()){
	            result.rejectValue("username",null,"Ingresar username");
	        }
         
         if(usuario.getClave()==null || usuario.getClave().isEmpty()){
	            result.rejectValue("clave",null,"Ingresar clave");
	        }
         
         
        if(existingUser!=null && existingUser.getUsername()!=null && !existingUser.getUsername().isEmpty()){
            result.rejectValue("username",null,"Ya existe una cuenta con este usuario");
        }

        if(result.hasErrors()){
            model.addAttribute("usuario",usuario);
            return "/register"; // if any form has errors it will be redirected to register page only.
        }

        
        usuario.setRol(rolService.buscarById(2));//
        usuarioService.guardarUsuario(usuario);
        return "redirect:/register?success"; // @Valid from jakarta.validation will enable the validation fields of dto objectsto be enabled.

    }
	
	@PostMapping("/login")
	public String iniciarSesion(Model model,@ModelAttribute Usuario usuario) {//,BindingResult result) {
	
		boolean band=usuarioService.login(usuario);
		System.out.println("bandd--> "+band);
		
		if(band==true) {
			   model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
			   model.addAttribute("rolList", rolService.listarTodosRol());
			
			   return   "usuario/index";
			
			
		} else {
	        // Mensaje de error al login fallido
	        model.addAttribute("loginError", "Usuario o contraseña incorrectos");
	        model.addAttribute("usuario", usuario); // Para mantener lo que escribió el usuario
	        return "login";
	    }
		
		
	}
	
	@GetMapping("/usuario/new")
    public String createUsuarioForm(Model model){
        
        // este objeto usuario almacenara los valores 
    	Usuario usuario = new Usuario();
       
        model.addAttribute("usuario", usuario);
        model.addAttribute("rolList", rolService.listarTodosRol());
       
        return "usuario/create";
     
    }

 
 @PostMapping("/usuario")
    public String saveUsuario(@ModelAttribute Usuario usuario) {
	 usuarioService.guardarUsuario(usuario);
        return "redirect:/usuario";
    }
 

    @GetMapping("/usuario")
    public String listUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
        model.addAttribute("rolList", rolService.listarTodosRol());
       
        
        return "usuario/index";
    }

    

}
