package com.example.laboratorio5.controller;

import com.example.laboratorio5.entity.Employees;
import com.example.laboratorio5.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/")
public class InicioController {


    @Autowired
    EmployeesRepository employeesRepository;
    @GetMapping(value = "/inicio")
    public String inicio(){
        return "inicio";
    }

    @GetMapping(value = "/listaempleados")
    public String listaDeEmpleados(Model model){

        model.addAttribute("listaempleados", employeesRepository.findAll());
        return "empleados/lista";

    }
    @GetMapping("/new")
    public String nuevoingreso(Model model) {
        model.addAttribute("listaEmpleados", employeesRepository.findAll());
        return "empleados/newFrm";
    }

    @PostMapping("/save")
    public String guardarempleado(Employees employees) {
        employeesRepository.save(employees);
        return "redirect:/listaempleados";
    }
    @GetMapping("/edit")
    public String editarEmployee(Model model,
                                 @RequestParam("id") int id) {

        Optional<Employees> optionalEmployees = employeesRepository.findById(id);

        if (optionalEmployees.isPresent()) {
            Employees employees = optionalEmployees.get();
            model.addAttribute("employees", employees);
            return "empleados/editar";
        } else {
            return "redirect:/empleados/lista";
        }
    }


}
