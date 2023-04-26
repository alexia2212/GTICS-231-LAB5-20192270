package com.example.laboratorio5.controller;

import com.example.laboratorio5.entity.Employees;
import com.example.laboratorio5.repository.DepartmentsRepository;
import com.example.laboratorio5.repository.EmployeesRepository;
import com.example.laboratorio5.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/")
public class InicioController {

    Date fechaactual = new Date();
    Timestamp fecha = new Timestamp(fechaactual.getTime());
    @Autowired
    JobRepository jobRepository;
    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = "/inicio")
    public String inicio(){
        return "inicio";
    }


    @GetMapping(value = "/reporte")
    public String reportesmenu(){
        return "reportes/dashboard";
    }

    @GetMapping(value = "/tentativaAumento")
    public String tentativaAumento(){
        return "reportes/tentativa";
    }


    @GetMapping(value = "/listaempleados")
    public String listaDeEmpleados(Model model){

        model.addAttribute("listaempleados", employeesRepository.listaenableempleados());

        return "empleados/lista";

    }


    @GetMapping(value = "/listareportes")
    public String listaDeReportes(Model model){

        model.addAttribute("listareportes", jobRepository.obtenerSalariosPorPuesto());

        return "reportes/lista";

    }


    @GetMapping("/new")
    public String nuevoingreso(Model model) {
        model.addAttribute("listaDepart", departmentsRepository.findAll());
        model.addAttribute("listaPuestos", jobRepository.findAll());
        model.addAttribute("listaJefes", employeesRepository.findAll());
        return "empleados/newFrm";
    }

    @PostMapping("/save")
    public String guardarempleado(Employees employees, RedirectAttributes attr) {
        employees.setHireDate(fecha);
        employees.setEnabled(1);
        System.out.println("empleado qlo" + employees.getHireDate());
        if(employees.getEmployeeId()==null){
            attr.addFlashAttribute("msg", "Empleado creado exitosamente");


        }else{
            attr.addFlashAttribute("msg", "Transportista actualizado exitosamente");
        }

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
            return "redirect:/listaempleados";
        }
    }

    @GetMapping("/delete")
    public String borrarEmployee(@RequestParam("id") int id, RedirectAttributes attr) {


        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            employeesRepository.eliminarmanager(id);
            employeesRepository.actualizardepartamento(id);
            employeesRepository.eliminarempleado(id);
            attr.addFlashAttribute("msg" ,"Empleado borrado");
        }

        return "redirect:/listaempleados";

    }

    @PostMapping("/BuscarEmpleados")
    public  String buscarEmpleado(@RequestParam("searchField")String searchField, Model model) {

        System.out.println("asda" + searchField);

        List<Employees> listaempleados = employeesRepository.listafiltrada(searchField);
        model.addAttribute("listaempleados", listaempleados);

        return "empleados/lista";
    }


}
