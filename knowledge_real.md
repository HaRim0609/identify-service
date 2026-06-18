1. Các công nghệ sử dụng trong dự án
- Java17 hoặc Java21 đã cài vào môi trường máy tính
- MySQL đã được cài đặt hoặc được triển khai trên docker

2. Nội dung kiến thức video 1,2,3,4,5
- Các tầng cơ bản cần có: 
    - Entity: 
        - Tầng này như để tạo ra 1 đối tượng. Đối tượng này sẽ chứa các thuộc tính tương đương với các field trong cơ sở dữ liệu.
        - Ở đây bằng cách thêm các Annotiation như @Entity sẽ giúp SpringBoot biết rằng nó sẽ tạo 1 bảng trong cơ sở dữ liệu đằng sau cú pháp @Entity.
        - Ngoài ra hãy đọc thêm file entity/User.java để hiểu thêm code và các note
    
    - Repository: 
        - Tại tầng này cũng sẽ như 1 tầng cuối để giao tiếp với database. Bạn hãy hình dung mỗi lần bạn làm xong mà cần CRUD thì nó sẽ sử dụng để thằng này để save dữ liệu mới hay làm nhưng thao tác CRUD khác với dữ liệu
        - Ngoài ra, nếu bạn để ý trong code responsitory/UserResponsitory sẽ thấy có kế thừa JPA, và đây sẽ có thể validate 1 số dữ liệu, kiểu như tìm kiếm, kiểm tra tồn tại miến là bạn tuân theo 1 số cú pháp của JPA. Link đọc thêm https://chat.deepseek.com/a/chat/s/541cf6af-8caf-4bb1-b198-2668ffc7618c
    
    - DTO: Data Transfer Object
        - Theo đúng nghĩa của nó, nó sẽ như 1 bức tranh mô phỏng lại dữ liệu trong database, nhưng thêm 1 điều nữa là nó có thêm các phần Validate. Thường hay được kết hợp với request để lấy dữ liệu hay object mà người dùng thao tác tạo ra
    
    - Controller:
        - Đây là nơi sẽ chứa các hàm chính được sử dụng và bạn cũng sẽ biết các đường dẫn trên URL. Một nơi để điều hướng ứng dụng và gọi tới các hàm - được viết cụ thể trong Service
    
    - Service:
        - Khi Controller chỉ viết ra tên các hàm thì Service sẽ viết cụ thể ra các hàm đó cũng như gọi tới các DTO, entity, responsitory để tạo ra các hàm và để các tầng làm việc với nhau

- Các lưu ý chung: Bạn cần vào các file của từng tầng để đọc thêm về code hơn vì có thể tôi sẽ viết sót 1 số đoạn
@Autowired : về đơn giản khi bạn muốn tạo 1 Responsitory bạn có thể thêm Annotiation này đằng trước và nó sẽ tự tìm đến tầng đó. Đại ý là nó thay cho cách viets this.

- Validate: 
    - Để có thể validate ta cần thêm :
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
			<!-- <scope>test</scope> -->
		</dependency>
    - Ngoài ra những phần nào muốn validate ta cần thêm @Valid vào trước để có thể validate dữ liệu
    
