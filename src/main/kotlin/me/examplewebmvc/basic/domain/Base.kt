package me.examplewebmvc.basic.domain

import me.examplewebmvc.util.DateUtil
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class Base {
    @Column(updatable = false)
    lateinit var createdTime: Timestamp
    lateinit var modifyTime: Timestamp

    @PrePersist
    fun onCreate(){
        this.createdTime = DateUtil.getNow()
    }

    @PreUpdate
    fun onUpdate(){
        this.modifyTime = DateUtil.getNow()
    }
}