package fina.padhi.sandeep.wirelesstracker;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    Button L_Click, R_Click, D_Click, Write, Copy, Paste;
    ToggleButton Drag;
    EditText Message;
    List<Integer> Relx = new ArrayList<Integer>();
    List<Integer> Rely = new ArrayList<Integer>();
    List<Integer> timeseries = new ArrayList<Integer>();
    Socket socket = null;
    DataInputStream input = null;
    DataOutputStream out = null;
    String ipaddress="";

    int flag=0;


    int z = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        // starttask();


    }

/*
    private void starttask() {
        new updatechange(this).execute();
    }
*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Calendar rightNow = Calendar.getInstance();

        TextView textView = (TextView) findViewById(R.id.textView);
        //      DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


        int x = (int) event.getX();
        int y = (int) event.getY();

        String mes = "X:" + Integer.toString(x) + " , Y :" + Integer.toString(y);
        //textView.setText(mes);
        //    mDatabase.child("01").child("x").setValue(Integer.toString(x));
        //  mDatabase.child("01").child("y").setValue(Integer.toString(y));
        Relx.add(new Integer(x));
        Rely.add(new Integer(y));
        Long t = rightNow.getTimeInMillis() - Long.parseLong("1532252467513");
        timeseries.add(new Integer(t.intValue()));
        // textView.setText(Integer.toString(Relx.size()));
        //textView.setText( Long.toString((rightNow.getTimeInMillis())));
        if (timeseries.size() > 2 && (timeseries.get(timeseries.size() - 1) - timeseries.get(timeseries.size() - 3)) > 100) {
            int relposx = Relx.get(0) - Relx.get(Relx.size() - 1);
            int relposy = Rely.get(0) - Rely.get(Rely.size() - 1);
            textView.setText("RelposX:" + Integer.toString(relposx) + " , RelposY :" + Integer.toString(relposy));
            //    mDatabase.child("01").child("relx").setValue(Integer.toString(relposx));
            //  mDatabase.child("01").child("rely").setValue(Integer.toString(relposy));
            Relx.clear();
            Rely.clear();
            timeseries.clear();
            ToggleButton Drag = (ToggleButton) findViewById(R.id.Drag);
String message=null;
            if (Drag.isChecked()) {
                message=Integer.toString(x)+","+Integer.toString(y)+","+Integer.toString(relposx)+","+Integer.toString(relposy)+",0,0,0,1,0,0,0,0";

            }
            else{
                message=Integer.toString(x)+","+Integer.toString(y)+","+Integer.toString(relposx)+","+Integer.toString(relposy)+",0,0,0,0,0,0,0,0";

            }
            updatechangenew update=new updatechangenew(message);
            update.execute();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        {


        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:

        }
        return false;
    }

    public void WriteMessage(View view) {
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
       ipaddress="192.168.43.63";
        EditText mg=(EditText)findViewById(R.id.Message);
        ipaddress=mg.getText().toString();

        if(flag==0)
       {

           if(ipaddress.isEmpty())
           {
               Toast.makeText(getApplicationContext(),"First Enter Ip",Toast.LENGTH_LONG).show();
               return ;
           }
           flag=1;
       }
       else {
           TextView textView = (TextView) findViewById(R.id.textView);
           Message = (EditText) findViewById(R.id.Message);
           textView.setText(Message.getText().toString());
           String message = "0,0,0,0,0,0,0,0,0,1," + Message.getText().toString();
           updatechangenew update = new updatechangenew(message);
           update.execute();
       }
        //mDatabase.child("User/01/").child("Message").setValue(Message.getText().toString());
        //mDatabase.child("01").child("Message").setValue(Message.getText().toString());
        //mDatabase.child("01").child("Write").setValue("1");


    }

    public void LClick(View view) {
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        TextView textView = (TextView) findViewById(R.id.textView);
        Message = (EditText) findViewById(R.id.Message);
        textView.setText("Left Click");
        String message="0,0,0,0,1,0,0,0,0,0,0,0";
        updatechangenew update=new updatechangenew(message);
        update.execute();

        //mDatabase.child("01").child("L_Click").setValue("1");

    }

    public void RClick(View view) {
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        TextView textView = (TextView) findViewById(R.id.textView);
        Message = (EditText) findViewById(R.id.Message);
        textView.setText("Right Click");
        String message="0,0,0,0,0,0,1,0,0,0,0,0";
        updatechangenew update=new updatechangenew(message);
        update.execute();

        //mDatabase.child("01").child("R_Click").setValue("1");


    }


    public void DClick(View view) {
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        TextView textView = (TextView) findViewById(R.id.textView);
        Message = (EditText) findViewById(R.id.Message);
        textView.setText("Double Click");
        String message="0,0,0,0,0,1,0,0,0,0,0,0";
        updatechangenew update=new updatechangenew(message);
        update.execute();

        //mDatabase.child("01").child("D_Click").setValue("1");


    }

    public void CCopy(View view) {
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        TextView textView = (TextView) findViewById(R.id.textView);
        Message = (EditText) findViewById(R.id.Message);
        textView.setText("Copy");
        String message="0,0,0,0,0,0,0,0,1,0,0,0";
        updatechangenew update=new updatechangenew(message);
        update.execute();

        //mDatabase.child("01").child("Copy").setValue("1");


    }


    public void PPaste(View view) {
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        TextView textView = (TextView) findViewById(R.id.textView);
        Message = (EditText) findViewById(R.id.Message);
        textView.setText("Paste");
        String message="0,0,0,0,0,0,0,0,0,1,0,0";
        updatechangenew update=new updatechangenew(message);
        update.execute();

        //mDatabase.child("01").child("Paste").setValue("1");


    }

    public void DDrag(View view) {
        ToggleButton Drag = (ToggleButton) findViewById(R.id.Drag);

        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        TextView textView = (TextView) findViewById(R.id.textView);
        Message = (EditText) findViewById(R.id.Message);
        textView.setText("Drag");
        if (Drag.isChecked()) {
            //  mDatabase.child("01").child("Drag").setValue("1");
            textView.setText("Drag_on");
            String message="0,0,0,0,0,0,1,0,0,0,0,0";
            //updatechangenew update=new updatechangenew(message);
            //update.execute();

        } else {
            //mDatabase.child("01").child("Drag").setValue("0");
            textView.setText("Drag_off");
            String message="0,0,0,0,0,0,0,0,0,0,0,0";
          //  updatechangenew update=new updatechangenew(message);
            //update.execute();

        }


    }


    public class updatechangenew extends AsyncTask<String, String, Void> {
        //MainActivity Mainreference
        public String mesg=null;

    public updatechangenew(String values)
    {

        mesg=values;
    }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... aurl) {


// initialize socket and input output streams

            // constructor to put ip address and port

            String address = ipaddress;

            int port = 5000;

            if (address != null) {

                // string to read message from input
                String line = "";

                // keep reading until "Over" is input
                int m = 1;
                while (m <= 1) {

                    // establish a connection
                    try {
                        socket = new Socket(ipaddress, 5000);
                        //System.out.println("Connected");
                        Log.e("MainActivity", "Connected");

                        // takes input from terminal
                        input = new DataInputStream(System.in);

                        // sends output to the socket
                        out = new DataOutputStream(socket.getOutputStream());
                    } catch (UnknownHostException u) {
                        Log.e("MainActivity", "UnknownHostException");
                        System.out.println(u);
                    } catch (IOException i) {
                        Log.e("MainActivity", "IOException");
                        // System.out.println(i);
                    }


                    try {
                        //line = input.readLine();

                        out.writeUTF(mesg);

                    } catch (IOException i) {
                        Log.e("MainActivity", "IOException");
                        continue;

                        //System.out.println(i);
                    }


                    try {
                        input.close();
                        out.close();
                        socket.close();
                    } catch (IOException i) {
                        Log.e("MainActivity", "IOException2");
                        //System.out.println(i);
                    }


                    m = m + 1;
                }

                // close the connection


                return null;

            }

            flag=0;
            return null;


        }

    }


}








