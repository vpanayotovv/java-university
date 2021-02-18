package com.examprep.andeys.web;

import com.examprep.andeys.model.binding.ItemAddBindingModel;
import com.examprep.andeys.model.entity.enums.CategoryName;
import com.examprep.andeys.model.entity.enums.GenderName;
import com.examprep.andeys.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("itemAddBindingModel")){
            model.addAttribute("itemAddBindingModel",new ItemAddBindingModel());
        }
        model.addAttribute("categories", CategoryName.values());
        model.addAttribute("genders", GenderName.values());
        return "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("itemAddBindingModel") ItemAddBindingModel itemAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("itemAddBindingModel",itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel",bindingResult);
            return "redirect:add";
        }

        itemService.add(itemAddBindingModel);

        return "redirect:/";
    }

}
