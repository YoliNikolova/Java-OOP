package animals;

public class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name,int age,String gender){
        this.name=name;
        this.age=age;
        this.gender=gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    protected String produceSound(){
        return "";
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("\n");
        sb.append(getName() + " " + getAge() + " " +getGender());
        sb.append("\n");
        sb.append(this.produceSound());

        return sb.toString().trim();
    }
}
