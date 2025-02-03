package com.example.apipracticetest.entity;

import lombok.Getter;
import lombok.Setter;

@Getter // getter 자동 생성
@Setter // setter 자동 생성
public class Todo implements Cloneable {
    private Long id;            // pk
    private String title;       // todo 제목
    private String text;        // todo 상세 내용
    private String deadline;    // todo 마감일
    private boolean checked;    // todo 완료 여부

    @Override
    public Todo clone() {
        try {
            return (Todo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.getStackTrace();
            return null;
        }
    }
}