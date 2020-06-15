package com.mapp.problemssolutions.problemsActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.util.StringUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mapp.problemssolutions.R;

public class RotateArrayActivity extends AppCompatActivity {

    public EditText arrayEdit;
    public EditText valueKEdit;
    public TextView solution;

    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_array);

        arrayEdit=(EditText)findViewById(R.id.arrayList);
        valueKEdit=(EditText)findViewById(R.id.valueK);
        solution=(TextView)findViewById(R.id.solution);
        btnSend=(Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(arrayEdit.getText().toString()!="" && valueKEdit.getText().toString()!=""){
                    final int k = Integer.parseInt(valueKEdit.getText().toString());

                    final String[] array = arrayEdit.getText().toString().split(",");

                    final int[] numbers = new int[array.length];
                    for (int i = 0; i < array.length; i++) {
                        numbers[i] = Integer.parseInt(array[i]);
                    }

                    rotate(numbers, k);
                }else{
                    Toast.makeText(getApplicationContext(),"Fill the textbox",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    public void rotate(int[] nums, int k) {

        if(nums.length>1 && nums.length>=k) {
            int[] temp = new int[k];
            int aux = 0;

            for (int i = nums.length - k; i < nums.length; i++) {
                temp[aux] = nums[i];
                aux++;
            }

            for (int j = nums.length - 1; j >= k; j--) {
                nums[j] = nums[j - k];
            }


            for (int m = 0; m < temp.length; m++) {
                nums[m] = temp[m];
            }
            //System.out.println(nums);
            String str="";
            for(int l=0;l<nums.length;l++){
                str=str+nums[l]+",";
            }

            solution.setText(str);

        }
    }


}
