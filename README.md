
## **Scalable Microservices Application**

This is a Spring Boot-based application designed to help you learn the concepts of microservices architecture. The project uses Spring Boot with an H2 database, JPA for data management, and Maven as the build tool. It includes controllers, repositories, and a test framework for validating functionalities.

---

### **Features**
- **CRUD operations** for managing products.
- Integration with **Spring Boot**, **H2 database**, and **JPA**.
- Basic unit and integration testing with **JUnit** and **MockMvc**.

---

## **Getting Started**

Follow these steps to set up and run the application.

### **Prerequisites**
1. **Java JDK 17** or higher
2. **Maven 3.8+**
3. An IDE like IntelliJ IDEA or VS Code
4. Git

---

### **Project Setup**
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Sairam30042/Scalable_Microservices_Application.git
   cd Scalable_Microservices_Application
   ```

2. **Install Dependencies:**
   ```bash
   mvn clean install
   ```

3. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application:**
   - The application runs on `http://localhost:8080` by default.

---

## **Endpoints**
### **Product API**
1. **Create a Product**
   - **POST** `/products`
   - Example Payload:
     ```json
     {
       "name": "Sample Product",
       "price": 25.5
     }
     ```
    - Try running the below query to add multiple entries at once to DB
      `` for i in {1..50}
do
  curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d "{\"name\": \"Test Product $i\", \"price\": $((RANDOM % 100 + 1)).99}"
done
``

2. **Get All Products**
   - **GET** `/products`

3. **Update a Product**
   - **PUT** `/products/{id}`

4. **Delete a Product**
   - **DELETE** `/products/{id}`

5. **Accessing H2 Console
   - You can access H2 console by visitng http://localhost:8080/h2-console.
   - You can find your URL, Username and password in src/main/resources/application.properties


---

## **Testing**
### **Run Tests**
Execute the following command to run tests:
```bash
mvn test
```

### **Common Errors During Testing**
1. **Error: `Cannot find symbol`**
   - Make sure you import the necessary classes in your test files, such as:
     ```java
     import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
     import org.mockito.Mockito;
     ```
2. **Error: `Failed to load ApplicationContext`**
   - Check your `application.properties` for incorrect database configuration.
   - If you're using an embedded H2 database, ensure it’s not already in use.
3. **Error: `Web server failed to start. Port 8080 was already in use`**
   - Either stop the process using port `8080` or run the application on a different port. Add the following to `application.properties`:
     ```properties
     server.port=8081
     ```

---

## **Troubleshooting Common Issues**

1. **Error: Duplicate H2 Database Declaration**
   - This happens when H2 is declared twice in the `pom.xml`. Ensure only one dependency exists:
     ```xml
     <dependency>
         <groupId>com.h2database</groupId>
         <artifactId>h2</artifactId>
         <scope>runtime</scope>
     </dependency>
     ```

2. **Error: `MockMvc` or `WebMvcTest` Not Found**
   - Ensure you’ve included the following dependency in the `pom.xml`:
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
         <scope>test</scope>
     </dependency>
     ```


3. **Error: `The file is locked (H2 database)`**
   - Ensure no other process is accessing the database. Stop any running instances of the application.
   - Make sure you close the tab in which you are accessing H2 database online before starting to test.
     

---

## **Folder Structure**
```
Scalable_Microservices_Application/
│
├── src/main/java/com/example/ProductService/
│   ├── controller/       # Contains ProductController
│   ├── model/            # Contains Product entity class
│   ├── repository/       # Contains ProductRepository interface
│   └── ProductServiceApplication.java
│
├── src/test/java/com/example/ProductService/
│   ├── controller/       # Contains ProductControllerTest
│   └── ProductServiceApplicationTests.java
│
├── pom.xml               # Maven dependencies and configurations
├── application.properties # Spring Boot configurations
└── README.md
```

---

## **Learnings**
1. **Spring Boot Basics**: Setting up a project and managing dependencies.
2. **RESTful APIs**: Creating endpoints using `@RestController`.
3. **H2 Database**: Using an in-memory database for development and testing.
4. **Testing**: Writing unit tests with `JUnit` and `MockMvc`.
5. **Error Resolution**: Debugging common issues during development and testing.

---

## **Contributing**
1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your message"
   ```
4. Push to the branch:
   ```bash
   git push origin feature/your-feature-name
   ```
5. Create a pull request.

---

## **Acknowledgments**
- This project is intended for educational purposes and beginner-level learning of Spring Boot and microservices architecture.

---

Feel free to share your contributions or suggestions! 😊
