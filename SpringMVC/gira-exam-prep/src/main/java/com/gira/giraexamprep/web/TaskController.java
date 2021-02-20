package com.gira.giraexamprep.web;

import com.gira.giraexamprep.model.binding.TaskAddBindingModel;
import com.gira.giraexamprep.model.service.UserServiceModel;
import com.gira.giraexamprep.service.TaskService;
import com.gira.giraexamprep.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null){
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("taskAddBindingModel")) {
            model.addAttribute("taskAddBindingModel", new TaskAddBindingModel());
        }
        return "add-task";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("taskAddBindingModel") TaskAddBindingModel taskAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel", bindingResult);
            return "redirect:add";
        }
        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");
        taskAddBindingModel.setUserServiceModel(userServiceModel);
        taskService.add(taskAddBindingModel);

        return "redirect:/";
    }

}
