/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import com.webapp.entity.Users;

/**
 *
 * @author yasin
 */
@Entity
@Table(name = "messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.id = :id"),
    @NamedQuery(name = "Messages.findBySubject", query = "SELECT m FROM Messages m WHERE m.subject = :subject")})
public class Messages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Subject")
    private String subject;
    @Lob
    @Column(name = "Content")
    private String content;
    @JoinColumn(name = "Fk_User_From", referencedColumnName = "Id")
    @ManyToOne
    private Users fkUserFrom;
    @JoinColumn(name = "Fk_User_To", referencedColumnName = "Id")
    @ManyToOne
    private Users fkUserTo;

    public Messages(String subject, String content, Users fkUserFrom, Users fkUserTo) {
        this.subject = subject;
        this.content = content;
        this.fkUserFrom = fkUserFrom;
        this.fkUserTo = fkUserTo;
    }

    
    public Messages() {
    }

    public Messages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getFkUserFrom() {
        return fkUserFrom;
    }

    public void setFkUserFrom(Users fkUserFrom) {
        this.fkUserFrom = fkUserFrom;
    }

    public Users getFkUserTo() {
        return fkUserTo;
    }

    public void setFkUserTo(Users fkUserTo) {
        this.fkUserTo = fkUserTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Messages[ id=" + id + " ]";
    }
    
}
