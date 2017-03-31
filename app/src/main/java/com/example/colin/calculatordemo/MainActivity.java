package com.example.colin.calculatordemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_mlu;
    private Button btn_dvi;
    private Button btn_dot;
    private Button btn_equ;
    private Button btn_del;
    private Button btn_clr;
    private EditText editText;
    private boolean clr_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0=(Button) findViewById(R.id.btn_0);
        btn1=(Button) findViewById(R.id.btn_1);
        btn2=(Button) findViewById(R.id.btn_2);
        btn3=(Button) findViewById(R.id.btn_3);
        btn4=(Button) findViewById(R.id.btn_4);
        btn5=(Button) findViewById(R.id.btn_5);
        btn6=(Button) findViewById(R.id.btn_6);
        btn7=(Button) findViewById(R.id.btn_7);
        btn8=(Button) findViewById(R.id.btn_8);
        btn9=(Button) findViewById(R.id.btn_9);
        btn_add=(Button) findViewById(R.id.btu_add);
        btn_sub=(Button) findViewById(R.id.sub);
        btn_mlu=(Button) findViewById(R.id.mul);
        btn_dvi=(Button) findViewById(R.id.dvi);
        btn_dot=(Button) findViewById(R.id.btn_dot);
        btn_equ=(Button) findViewById(R.id.btu_equ);
        btn_del=(Button) findViewById(R.id.del);
        btn_clr=(Button) findViewById(R.id.clear);
        editText=(EditText) findViewById(R.id.et_input); //实例化按钮和输入框
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mlu.setOnClickListener(this);
        btn_dvi.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_equ.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=editText.getText().toString();
        switch(v.getId()){
            case R.id.btn_0:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dot:
                if(clr_flag)
                {
                    clr_flag=false;
                    editText.setText("");
                    str="";
                }
                editText.setText(str+((Button)v).getText());
                break;
            case R.id.btu_add:
            case R.id.sub:
            case R.id.mul:
            case R.id.dvi:
                if(clr_flag)
                {
                    clr_flag=false;
                    editText.setText("");
                    str="";
                }
                editText.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.del:
                if(clr_flag)
                {
                    clr_flag=false;
                    editText.setText("");
                    str="";
                }
                else if(str!=null&&!str.equals(""))
                    editText.setText(str.substring(0,str.length()-1));
                break;
            case R.id.clear:
                clr_flag=false;
                editText.setText("");
                break;
            case R.id.btu_equ: getresult();
            default:
                break;
        }
    }

    //运算结果
    private void getresult()
    {
        String exp=editText.getText().toString();
        double result;
        if(clr_flag)
        {
            clr_flag=false;
            return;
        }
        clr_flag=true;
        if(exp==null||exp.equals(""))
            return;
        if(!exp.contains(" "))
        {
            return;
        }
        String s1=exp.substring(0,exp.indexOf(" "));
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        String s2=exp.substring(exp.indexOf(" ")+3);
        if(!s1.equals("")&&!s2.equals(""))
        {
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+"))
            {
                result=d1+d2;
            }
            else if(op.equals("-"))
            {
                result=d1-d2;
            }else if(op.equals("*"))
            {
                result=d1*d2;
            }
            else
            {
                if(d2==0)
                    result=0;
                else
                    result=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("/")) {
                int r = (int) result;
                editText.setText(r+"");
            }else
            {
                editText.setText(result+"");
            }
        }
        else if(!s1.equals("")&&s2.equals(""))
        {
            editText.setText(exp);
            clr_flag=false;
            return;
        }
        else if(s1.equals("")&&!s2.equals(""))
        {
            double d2=Double.parseDouble(s2);
            if(op.equals("+"))
            {
                result=0+d2;
            }
            else if(op.equals("-"))
            {
                result=0-d2;
            }else if(op.equals("*"))
            {
                result=0;
            }
            else
            {
                    result=0;
            }
            if(!s1.contains(".")&&!s2.contains(".")) {
                int r = (int) result;
                editText.setText(r+"");
            }else
            {
                editText.setText(result+"");
            }
        }else
        {
            editText.setText("");
        }
        }

    }
