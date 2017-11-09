package com.dream.brick.equipment.dao.impl;

/**
 * Created by taller on 16/2/18.
 */

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.brick.equipment.bean.ProjectFile;
import com.dream.brick.equipment.dao.ProjectFileDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户数据库访问实现类
 *
 * @author taller
 *
 */
@Transactional
@Repository
public class ProjectFileDaoImpl extends BaseDaoImpl implements ProjectFileDao{
    @Override
    public void addFile(ProjectFile projectFile) {
      save(projectFile);
    }

    @Override
    public ProjectFile findByProId(String projectId) {
        List<ProjectFile> projectFileList=findList("from ProjectFile where projectId='"+projectId+"'");
        if (projectFileList != null&&projectFileList.size()>0) {
            ProjectFile projectFile=projectFileList.get(0);
            System.out.println(projectFile);
            return projectFile;
        }
        return null;
    }

    @Override
    public void updateFile(ProjectFile projectFile) {
        update(projectFile);
    }
}
