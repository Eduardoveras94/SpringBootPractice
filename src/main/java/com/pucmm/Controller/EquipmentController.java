package com.pucmm.Controller;

import com.pucmm.Entiy.Equipment;
import com.pucmm.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Eduardo veras on 06-Oct-16.
 */
@Controller
public class EquipmentController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/Equipments")
    public ModelAndView index(Model model) {
        model.addAttribute("inventory", inventoryService.findAllEquipments().size() + 1);
        model.addAttribute("equipments", inventoryService.findAllEquipments());
        model.addAttribute("subfamilies", inventoryService.findAllSubFamilies());
        return new ModelAndView("registerEquipment");
    }


    @Secured("ADMIN")
    @PostMapping("/addNewEquipment")
    public String newEquipment(@RequestParam("name") String name, @RequestParam("subfamily") String subFamily, @RequestParam("stock") Integer stock, @RequestParam("file") MultipartFile file){

        Equipment equipment = inventoryService.registerNewEquipment(name,inventoryService.findSubFamilyBySubFamilyName(subFamily).getSubFamilyKey(),stock);

        try {
            equipment.setImage(procesImageFile(file.getBytes()));

            inventoryService.editEquipment(equipment);
        } catch (IOException exp) {
            System.out.println("Error while Uploading image");
        } catch (NullPointerException exp) {
            System.out.println("Image data is null");
        }

        return "redirect:/Equipments";
    }

    @PostMapping("/editEquipment")
    public String addImagetoEquipment(@RequestParam("name") String equipmentName, @RequestParam("file") MultipartFile file){

        Equipment equipment = inventoryService.findEquipmentByName(equipmentName);

        try {
            equipment.setImage(procesImageFile(file.getBytes()));

            inventoryService.editEquipment(equipment);
        } catch (IOException exp) {
            System.out.println("Error while Uploading image");
        } catch (NullPointerException exp) {
            System.out.println("Image data is null");
        }

        return "redirect:/Equipments";
    }

    @PostMapping("/restockEquipment")
    public String restockEquipment(@RequestParam("equipment") String equipmentName, @RequestParam("amount") Integer amount){

        inventoryService.restockEquipment(inventoryService.findEquipmentByName(equipmentName).getEquipmentId(), amount);

        return "redirect:/Equipments";
    }

    //Auxiliary Functions
    private Byte[] procesImageFile(byte[] buffer) {
        Byte[] bytes = new Byte[buffer.length];
        int i = 0;

        for (byte b :
                buffer)
            bytes[i++] = b; // Autoboxing

        return bytes;
    }
}
