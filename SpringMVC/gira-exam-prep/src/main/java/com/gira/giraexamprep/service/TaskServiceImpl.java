package com.gira.giraexamprep.service;

import com.gira.giraexamprep.model.binding.TaskAddBindingModel;
import com.gira.giraexamprep.model.entity.TaskEntity;
import com.gira.giraexamprep.model.entity.enums.ProgressName;
import com.gira.giraexamprep.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final ClassificationService classificationService;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, ClassificationService classificationService, UserService userService) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.classificationService = classificationService;
        this.userService = userService;
    }

    @Override
    public void add(TaskAddBindingModel taskAddBindingModel) {
        TaskEntity taskEntity = modelMapper.map(taskAddBindingModel, TaskEntity.class);
        taskEntity.setClassification(classificationService.getByName(taskAddBindingModel.getClassification()));
        taskEntity.setProgressName(ProgressName.OPEN);
        taskRepository.saveAndFlush(taskEntity);
    }
}
