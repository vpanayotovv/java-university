package com.exam.springexam.web;

import com.exam.springexam.model.binding.AlbumAddBindingModel;
import com.exam.springexam.model.service.UserServiceModel;
import com.exam.springexam.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null){
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("albumAddBindingModel")) {
            model.addAttribute("albumAddBindingModel", new AlbumAddBindingModel());
        }
        return "add-album";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("taskAddBindingModel") AlbumAddBindingModel albumAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);
            return "redirect:add";
        }
        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");
        albumAddBindingModel.setAddedFrom(userServiceModel);
        albumService.add(albumAddBindingModel);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){

        albumService.deleteById(id);

        return "redirect:/";
    }
}
