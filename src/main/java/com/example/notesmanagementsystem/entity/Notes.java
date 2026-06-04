package com.example.notesmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2000)
    private String content;
    private String category;
    private boolean pinned;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static class Builder{
        private String title;
        private String content;
        private String category;
        private boolean pinned;

        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder category(String category){
            this.category = category;
            return this;
        }
        public Builder pinned(boolean pinned){
            this.pinned = pinned;
            return this;
        }

        public Notes build(){
            return new Notes(this);
        }
    }
    public static Builder builder(){
        return new Builder();
    }

    private Notes(Builder builder){
        this.title = builder.title;
        this.content = builder.content;
        this.category = builder.category;
        this.pinned = builder.pinned;
    }
    public Notes(){}

}
