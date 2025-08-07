package com.sistema.inventario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

// Ruta para el dashboard de productos
	@GetMapping("/dashboard")
	public String mostrarDashboard(Model model) {

		return "dashboard"; // La página del dashboard
	}

	@GetMapping("/")
	public String redireccionarDashboard() {
		return "redirect:/dashboard"; // redirige automáticamente
	}
}
