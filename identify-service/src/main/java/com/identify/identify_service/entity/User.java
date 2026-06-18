package com.identify.identify_service.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
// @Table(name = "tbl_user")  // Đặt tên bảng là "tbl_user"
// Có thể dùng Table để đặt tên bảng khác với tên class, nhưng nếu không đặt thì mặc định sẽ lấy tên class làm tên bảng và Hibernate sẽ tạo tên bảng là chữ thường
// Và thêm _ nếu tên có dạng OrderDetail -> order_detail
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Đại ý 2 Annotiation ở đây sẽ biết id là khóa chính và Gen tự tăng ID mỗi lần có dữ liệu
    private long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dab;

    // Getters và Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDab() {
        return dab;
    }

    public void setDab(LocalDate dab) {
        this.dab = dab;
    }
}