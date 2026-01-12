package person;

class Person {
    private String name;
    private int age;
    private String address;
    //問① 上記変数に従って、getterとsetterを設定して下さい。
    
    // nameのgetter/setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    // ageのgetter/setter
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    // addressのgetter/setter
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
