package com.example.assignmentandroid.XFile;

import android.content.Context;

import com.example.assignmentandroid.model.Account;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Read_Write {
    Context context;

    public Read_Write(Context context) {
        this.context = context;
    }

    // hàm ghi dữ liệu vào file
    public void writeUserData(Context context, String fName, Account user_data) {
        ArrayList<Account> list = new ArrayList<>();
        try{
            FileOutputStream fileOutputStream = context.openFileOutput(fName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            list.add(user_data);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // hàm đọc dữ liệu từ file
    public ArrayList<Account> readUserData(Context context, String fName) {
        ArrayList<Account> list = new ArrayList<>();
        try{
            FileInputStream fileInputStream = context.openFileInput(fName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<Account>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
