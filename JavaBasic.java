import java.util.zip.CheckedInputStream;

public class JavaBasic {

  public static void removeDublicate(Strign str,int idx,StringBuilder newStr,boolean){
    if(idx==str.length()){
        System.out.println(newStr);
        return 
    }
    char currChar=str.charAt(idx);
    if(map[currChar-"a"]==true){
        removeDublicate(str,idx+1,newStr,map)
    }else{
        map[currChar-"a"]=true;
        removeDuplicate(str,idx+1,newStr.append(currChar), map)
    }

  }

    public static void main(String[] args) {
        
        System.out.println(tileProblem(4));
    }
}
