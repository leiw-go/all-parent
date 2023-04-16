package cn.leiwspider;

public class ReflectGo {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("cn.leiwspider.Book");
            Book book = (Book) clazz.newInstance();
            book.printHey();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
