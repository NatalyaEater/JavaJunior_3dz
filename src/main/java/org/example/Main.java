package org.example;

import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 * Обеспечьте поддержку сериализации для этого класса. Создайте объект класса Student и инициализируйте его данными.
 * Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла.
 * Выведите все поля объекта, включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.
 * **Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
 */

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("ina",20,8.9);
        serialObj(student,"serial");
        Student student1=(Student) deSerialObj("serial");
        System.out.println(student1);


        System.out.println("Сериализация в Json ");
        serializeJson(student, "ser.json");

        Student deserializedJson = deserializeJson();
        System.out.println("Десериализация из Json");
        System.out.println("name  "+deserializedJson.getName()+ ",  age  "+deserializedJson.getAge()+",  gpa  " + deserializedJson.getGPA());

    }

    public static void serialObj(Object o,String file) throws IOException{
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
        System.out.println("Сериализация  выполнена ");

    }

    public static Object deSerialObj(String file) throws IOException,ClassNotFoundException{
        FileInputStream fileInputStream=new FileInputStream(file);
        ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();

    }

    public static void serializeJson(Student student, String fileName) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName), student);
        System.out.println("Сериализация выполнена ");

    }
    public static Student deserializeJson() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("student.json"), Student.class);

    }

}