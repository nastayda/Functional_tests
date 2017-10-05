import org.testng.annotations.Test;

@Test
class  test1{
    public static void main(){
        WriteReadFromFile readFromFile= new WriteReadFromFile("C:\\test\\notes3.txt");
        System.out.println(readFromFile.readFromFile().get(2));
       // System.out.println(readFromFile.readFromFile().get(0));
    }
}
