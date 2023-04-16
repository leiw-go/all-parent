package cn.leiwspider;

public class Book {
    private String name;

    private void printOk() {
        System.out.println("its Ok!");
    }
    public void printHey() {
        System.out.println("its Hey!");
    }
    private Integer bookNum = 0;

    public  void addNum(){
        bookNum++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }
}
