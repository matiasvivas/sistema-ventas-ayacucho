package me.parzibyte.sistemaventasspringboot;

import java.text.SimpleDateFormat;
import java.util.Date;
import me.parzibyte.sistemaventasspringboot.model.Role;
import me.parzibyte.sistemaventasspringboot.model.User;
import me.parzibyte.sistemaventasspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/cierrecajapublico")
public class CierreCajaController {

    @Autowired
    private UserService userService;

    @Autowired
    private CierreCajaRepository cierreCajaRepository;

    //Agregar dinero real al cierre de caja
    @GetMapping(value = "/agregar")
    public String agregarBilletes(Model model, @ModelAttribute(value = "cierreCaja") CierreCaja cierreCaja) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());
        model.addAttribute("mensaje","Cierre de caja agregado correctamente");
        model.addAttribute("clase","success");

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date hoy= new Date();
        String timestamp = sdf.format(hoy);
        cierreCaja.setFechaYHora(timestamp);
        cierreCaja.setUsername(user.getUserName());
        cierreCajaRepository.save(cierreCaja);

        return "cierrecaja/cierre_caja_publico";
    }

    //Mostrar ultimo cierre de caja
    @GetMapping(value = "/mostrar")
    public String mostrarCierreCaja(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());
        CierreCaja cierreCaja = null;
        try {
            cierreCaja = cierreCajaRepository.findLastByUsername(user.getUserName());
        }
        catch(Exception e){
            cierreCaja = new CierreCaja();
        }
        if(cierreCaja!=null) {
            cierreCaja.setMonto(new Float(0));
            model.addAttribute("cierreCaja", cierreCaja);
        }
        else{
            cierreCaja = new CierreCaja();
            cierreCaja.setMonto(new Float(0));
            model.addAttribute("cierreCaja", cierreCaja);
        }

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);

        return "cierrecaja/cierre_caja_publico";
    }
}