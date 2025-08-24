package com.proyecto.gestionLibros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.gestionLibros.entity.Usuario;
import com.proyecto.gestionLibros.Service.RolService;
import com.proyecto.gestionLibros.Service.UsuarioService;

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
        return "redirect:/login"; // apunta al login
    }

    // Nuevo login con manejo de errores de Spring Security
    @GetMapping("/login")
    public String loginPage(Model model, 
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("loginError", "Usuario o contraseña incorrectos");
        }
        if (logout != null) {
            model.addAttribute("sessionClosed", true);
        }
        model.addAttribute("usuario", new Usuario());
        return "login"; // Thymeleaf login.html
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Usuario userDto = new Usuario();
        model.addAttribute("usuario",userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute Usuario usuario, BindingResult result, Model model){

        Usuario existingUser = usuarioService.buscarByUsuario(usuario.getUsername());

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
        if(existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()){
            result.rejectValue("username",null,"Ya existe una cuenta con este usuario");
        }

        if(result.hasErrors()){
            model.addAttribute("usuario",usuario);
            return "/register";
        }

        usuario.setRol(rolService.buscarById(2));
        usuarioService.guardarUsuario(usuario);
        return "redirect:/register?success";
    }
    
    @GetMapping("/usuario/new")
    public String createUsuarioForm(Model model){
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
