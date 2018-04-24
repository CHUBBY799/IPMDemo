package com.demo.IPMDemo.demo.Service;

import com.demo.IPMDemo.demo.Dao.ProjectDao;
import com.demo.IPMDemo.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;
    public List<Project> SelectProjectByStatus(int status){
        return projectDao.selectProjectByStatus(status);
    }
}
