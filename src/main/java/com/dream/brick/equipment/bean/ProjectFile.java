package com.dream.brick.equipment.bean;

/**
 * Created by taller on 16/2/18.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 工程文件
 * @author taller
 * @date 2016-02-18
 */
@Entity
@Table(name = "t_project_file")
public class ProjectFile {
    private String id;
    private String projectId;
    private String attachmentId;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "project_id")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Column(name = "attachment_id")
    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
