package me.parzibyte.sistemaventasspringboot;

import com.fasterxml.jackson.databind.util.JSONPObject;
import me.parzibyte.sistemaventasspringboot.model.Role;
import me.parzibyte.sistemaventasspringboot.model.User;
import me.parzibyte.sistemaventasspringboot.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/ventas")
public class VentasController {
    @Autowired
    VentasRepository ventasRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String mostrarVentas(Model model) {

        List<Venta> todasLasVentas = (List<Venta>) ventasRepository.findAll();
        int totalArray = todasLasVentas.size();
        String[] estadisticasVentas = new String[totalArray];

        for(int i = 0; i<todasLasVentas.size();i++){
            String fecha = todasLasVentas.get(i).getFechaYHora();
            String[] fechaSplit = fecha.split(" ");
            fecha = fechaSplit[0];
            String totalVenta = todasLasVentas.get(i).getTotal().toString();
            String[] totalVentaSplit = totalVenta.split("\\.");
            totalVenta = totalVentaSplit[0];
            estadisticasVentas[i] = fecha+","+totalVenta+";";
        }
        String resultado ="";
        for(int i = 0; i<estadisticasVentas.length;i++){
            resultado=resultado+estadisticasVentas[i].toString();
        }

        //nuevo filtro de estadisticas por dia

        //fin nuevo filtro estadisticas por dia

        model.addAttribute("estadisticasVentas", resultado);

        model.addAttribute("ventas", ventasRepository.findAll());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);

        return "ventas/ver_ventas";
    }
}
