package com.identify.identify_service.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
// @Table(name = "tbl_user")  // Đặt tên bảng là "tbl_user"
// Có thể dùng Table để đặt tên bảng khác với tên class, nhưng nếu không đặt thì mặc định sẽ lấy tên class làm tên bảng và Hibernate sẽ tạo tên bảng là chữ thường
// Và thêm _ nếu tên có dạng OrderDetail -> order_detail
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Đại ý 2 Annotiation ở đây sẽ biết id là khóa chính và Gen tự tăng ID mỗi lần có dữ liệu
    long id;

    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dab;
    Set<String> roles;

  
    
}