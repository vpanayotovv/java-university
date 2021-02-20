package com.gira.giraexamprep.service;

import com.gira.giraexamprep.model.binding.TaskAddBindingModel;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    void add(TaskAddBindingModel taskAddBindingModel);
}
