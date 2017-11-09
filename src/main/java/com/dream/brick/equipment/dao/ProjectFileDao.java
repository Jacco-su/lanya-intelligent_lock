package com.dream.brick.equipment.dao;

import com.dream.brick.equipment.bean.ProjectFile;

/**
 * 工程文件上传接口
 * Created by taller on 16/2/18.
 */
public interface ProjectFileDao {
    void  addFile(ProjectFile projectFile);
    ProjectFile findByProId(String projectId);
    void  updateFile(ProjectFile projectFile);
}
