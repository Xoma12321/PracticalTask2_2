import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Практика 2.2, Хомяков Даниил Васильевич, РИБО-05-22");
        System.out.println("Start Program!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String,String> map = new HashMap();
        map.put("name","");
        map.put("group","");
        HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try {
            th.join();
        }catch (InterruptedException ex){

        }finally {
            JSONObject jsonObject = new JSONObject(httpRunnable.getReasponseBody());
            int result = jsonObject.getInt("result_code");
            try {
                System.out.println("Result: " + result);
                System.out.println("Type: " + jsonObject.getString("message_type"));
                System.out.println("Text: " + jsonObject.getString("message_text"));
                switch (result) {
                    case 1:
                        JSONArray jsonArray = jsonObject.getJSONArray("task_list");
                        System.out.println("Task list:");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            System.out.println((i + 1) + ") " + jsonArray.get(i));
                        }
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
            }catch (JSONException ex){
                System.out.println(ex.getMessage());
            }
        }

        



    }
}


//        try {
//        th.join();
//        }catch (InterruptedException ex){
//
//        }finally {
//        try {
//FileWriter fw = new FileWriter("D:\\mirea.html");
//                fw.write(hTTPRunnable.getReasponseBody());
//        fw.close();
//                System.out.println("Success " + server);
//            }catch (IOException ex){
//        System.out.println("ERROR: " + ex.getMessage());
//        }
